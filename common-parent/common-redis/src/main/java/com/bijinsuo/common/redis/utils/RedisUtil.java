package com.bijinsuo.common.redis.utils;

import cn.hutool.core.util.ArrayUtil;
import com.bijinsuo.common.utils.SpringBeanUtils;
import com.google.common.collect.Sets;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Doctor
 * @desc ${DESCRIPTION}
 * @date 2022-07-08 10:53:50
 */
public class RedisUtil {

    private static final Log logger = LogFactory.getLog(RedisUtil.class);

    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 默认过期时长为24小时，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24L;
    /**
     * 过期时长为1天，单位：秒
     */
    public final static long DAY_ONE_EXPIRE = 60 * 60 * 24L;
    /**
     * 过期时长为5分钟，单位：秒
     */
    public final static long MIN_5_EXPIRE = 5 * 60L;
    /**
     * 过期时长为5分钟，单位：秒
     */
    public final static long MIN_10_EXPIRE = 10 * 60L;
    /**
     * 过期时长为1分钟，单位：秒
     */
    public final static long MIN_1_EXPIRE = 60L;
    /**
     * 过期时长为1小时，单位：秒
     */
    public final static long HOUR_ONE_EXPIRE = 60 * 60 * 1L;
    /**
     * 过期时长为6小时，单位：秒
     */
    public final static long HOUR_SIX_EXPIRE = 60 * 60 * 6L;
    /**
     * 过期时长为2个月，单位：秒
     */
    public final static long TWO_MONTH_EXPIRE = 60 * 60 * 24 * 60L;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1L;

    @Value("${redis.model:single}")
    private String model;

    private static RedisUtil redisUtil;

    public static RedisUtil instance() {
        if (redisUtil == null) {
            redisUtil = SpringBeanUtils.getBean(RedisUtil.class);
        }

        return redisUtil;
    }

    public static void setRedisUtil(RedisUtil redisUtil) {
        RedisUtil.redisUtil = redisUtil;
    }

    /**
     * 批量获取key
     *
     * @param pattern
     */
    public Set<Serializable> keys(final String pattern) {
        return redisTemplate.keys(pattern);

    }

    /**
     * 获取key的过期时间（秒），key不存在：返回-2  key永不过期：-1
     *
     * @param key
     * @return
     */
    public Long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 延长有效期
     *
     * @param key
     * @param minutes
     */
    public boolean makeMoreTime(final String key, int minutes) {
        return redisTemplate.boundValueOps(key).expire(minutes, TimeUnit.SECONDS);
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * @描述 redis取出数据并排序
     * @创建人 yangminglei
     * @参数 [key, from, limit, SortOrder]
     * @返回值 java.util.List<java.lang.Object>
     * @创建时间 2018/12/13
     * @修改人和其它信息 多人就是多个
     */
    public List<Map<Object, Object>> sort(String key) {
        List<Map<Object, Object>> maps = new ArrayList<Map<Object, Object>>();
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        List<Map.Entry<Object, Object>> list = new ArrayList<Map.Entry<Object, Object>>(entries.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Object, Object>>() {
            //升序排序
            public int compare(Map.Entry<Object, Object> o1,
                               Map.Entry<Object, Object> o2) {
                BigDecimal value1 = (BigDecimal) o1.getValue();
                BigDecimal value2 = (BigDecimal) o2.getValue();
                return value1.compareTo(value2);
            }

        });
        for (Map.Entry<Object, Object> objectObjectEntry : list) {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("id", objectObjectEntry.getKey());
            map.put("risk", objectObjectEntry.getValue());
            maps.add(map);
        }
        return maps;
    }

    public Long execute(RedisScript<Long> script, List<String> keys, String... args) {
        List<String> argList = CollectionUtils.arrayToList(args);
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                String scriptStr = script.getScriptAsString();
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(scriptStr, keys, argList);
                }

                // 单机模式
                else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(scriptStr, keys, argList);
                }

                return null;
            }
        });
//		return redisTemplate.execute(script, keys, args);
    }

    /**
     * 获取一个hash
     *
     * @param cacheKey
     * @return
     */
    public Map<String, Object> getCacheMap(String cacheKey) {
        BoundHashOperations<Serializable, String, Object> bound = redisTemplate.boundHashOps(cacheKey);
        return bound.entries();
    }

    /**
     * 从hash里获取一个值
     *
     * @param cacheKey
     * @param key
     * @return
     */
    public Object getDataFromCacheMap(String cacheKey, Object key) {
        BoundHashOperations<Serializable, Object, Object> bound = redisTemplate.boundHashOps(cacheKey);
        return bound.get(key);
    }

    /**
     * 向hash放进一个键值
     *
     * @param cacheKey
     * @param key
     * @param value
     */
    public void setDataFromCacheMap(String cacheKey, Object key, Object value) {
        BoundHashOperations<Serializable, Object, Object> bound = redisTemplate.boundHashOps(cacheKey);
        bound.put(key, value);
    }

    /**
     * 删除hash一个键值
     *
     * @param cacheKey
     * @param key
     */
    public void removeDataFromCacheMap(String cacheKey, Object key) {
        BoundHashOperations<Serializable, Object, Object> bound = redisTemplate.boundHashOps(cacheKey);
        bound.delete(key);
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("set cache error", e);
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Integer expireTime) {
        return set(key, value, expireTime.longValue());
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);

            result = true;
        } catch (Exception e) {
            logger.error("set cache error", e);
        }
        return result;
    }

    /**
     * HashSet方式写入缓存
     *
     * @param key
     * @param map
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * HashSet方式写入缓存
     *
     * @return
     */
    public boolean hmput(String key, String field, Object v) {
        try {
            redisTemplate.opsForHash().put(key, field, v);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * HashSet 获取所有KEY
     *
     * @return
     */
    public Set<String> hmgetAllKeys(String key) {
        try {
            Set<Object> keys = redisTemplate.opsForHash().keys(key);
            return keys != null? keys.stream().map(String ::valueOf).collect(Collectors.toSet()) : Sets.newHashSet();
        } catch (Exception e) {
           logger.error("hmgetAllKeys",e);
           return Sets.newHashSet();
        }
    }

    public <T> T hmget(String key, String field) {
        try {
            return (T) redisTemplate.opsForHash().get(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param key   map的key
     * @param field 项名称
     * @return
     * @Author Doctor
     * @Date 15:47 2022/3/14
     * @Description 删除map中指定的field项
     **/
    public boolean hmdel(String key, String field) {
        try {
            return redisTemplate.opsForHash().delete(key, field) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param key    map的key
     * @param fields 项列表
     * @return
     * @Author Doctor
     * @Date 15:47 2022/3/14
     * @Description 删除map中指定的field项
     **/
    public long hmdelAll(String key, String... fields) {
        try {
            return redisTemplate.opsForHash().delete(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * HashSet方式写入缓存
     *
     * @param key
     * @return
     */
    public Long hincrby(String key, String field, Long increment) {
        try {
            return redisTemplate.opsForHash().increment(key, field, increment);
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    /**
     * HashSet方式写入缓存
     *
     * @param key
     * @return
     */
    public Double hincrby(String key, String field, Double increment) {
        try {
            return redisTemplate.opsForHash().increment(key, field, increment);
        } catch (Exception e) {
            e.printStackTrace();
            return -1D;
        }
    }

    /**
     * 获取map的value
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object getMapValue(String key, String hashKey) {
        try {
            return redisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取map的value并转String
     *
     * @param key
     * @param hashKey
     * @return
     */
    public String getMapValueAsStr(String key, String hashKey) {
        Object mapValue = getMapValue(key, hashKey);
        if (mapValue == null) {
            return null;
        }
        return mapValue.toString();
    }


    /**
     * 添加list
     *
     * @param listKey
     * @param list
     */
    public void addList(String listKey, List<Object> list) {
        ListOperations<Serializable, Object> vo = redisTemplate.opsForList();
        vo.rightPushAll(listKey, list);
    }

    /**
     * 放入list
     *
     * @param listKey
     * @param object
     */
    public void addListRight(String listKey, Object object) {
        ListOperations<Serializable, Object> vo = redisTemplate.opsForList();

        vo.rightPush(listKey, object);
    }

    /**
     * 删除list
     *
     * @param listKey
     */
    public void listDeleteAll(String listKey) {
        ListOperations<Serializable, Object> vo = redisTemplate.opsForList();
        vo.trim(listKey, 0, 0);
        vo.leftPop(listKey);
    }

    /**
     * 从list中获取
     *
     * @param listKey
     * @return
     */
    public Object getListLeft(String listKey) {
        ListOperations<Serializable, Object> vo = redisTemplate.opsForList();
        return vo.leftPop(listKey);
    }

    /**
     * 获取指定key中链表的所有数据
     *
     * @param
     */
    public List<Object> getListValue(String key) {
        ListOperations<Serializable, Object> vo = redisTemplate.opsForList();
        return vo.range(key, 0, -1);

    }

    /**
     * 获取list长度
     *
     * @param listKey
     * @return
     */
    public long getLength(String listKey) {
        ListOperations<Serializable, Object> vo = redisTemplate.opsForList();
        return vo.size(listKey);
    }


    public long increment(final String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }


    public double increment(final String key, double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> getSet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean getSetHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long addSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 是否已经存在
     *
     * @param key
     * @param values
     * @return
     */
    public boolean isSetExit(String key, Object values) {
        try {
            SetOperations<Serializable, Object> operations = redisTemplate.opsForSet();
            return operations.isMember(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long addSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long getSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long removeSet(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void leftPushs(String key, Object... vs) {
        redisTemplate.opsForList().leftPushAll(key, vs);
    }

    public void leftPush(String key, Object object) {
        redisTemplate.opsForList().leftPush(key, object);
    }

    public void rightPush(String key, Object object) {
        redisTemplate.opsForList().rightPush(key, object);
    }

    public <T> T leftPop(String key) {
        Object o = redisTemplate.opsForList().leftPop(key);
        if (o == null) {
            return null;
        }
        return (T) o;
    }

    private final static int MAX_LIST = 10000;

    public <T> List<T> getListAndTrim(final String key) {

        List<Object> range = this.redisTemplate.opsForList().range(key, 0, MAX_LIST);

        if (range == null || range.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        this.redisTemplate.opsForList().trim(key, range.size(), -1);

        return (List<T>) range;
    }


    public <T> List<T> leftPops(final String key, final int count) {
        if ("single".equals(model)) {
            return singleLeftPops(key, count);
        } else {
            return clusterLeftPops(key, count);
        }
    }

    private <T> List<T> clusterLeftPops(final String key, final int count) {
        List<T> rs = new ArrayList<>();
        T entity = leftPop(key);
        int i = 0;
        while (entity != null) {
            rs.add(entity);

            entity = leftPop(key);
            if (++i >= count) break;
        }

        return rs;
    }

    private <T> List<T> singleLeftPops(final String key, final int count) {
        final List<Object> txResults = this.redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(final RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForList().range(key, 0, count - 1);
                operations.opsForList().trim(key, count, -1);
                return operations.exec();
            }
        });
        if (CollectionUtils.isEmpty(txResults)) {
            return Collections.emptyList();
        }
        final Object object = txResults.get(0);
        if (object instanceof ArrayList) {
            //返回最终结果
            return (List<T>) object;
        }
        return Collections.emptyList();
    }

    public <T> List<T> leftPopsRightPushs(final String key, final int count, final String pushKey) {
        final List<Object> txResults = this.redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(final RedisOperations operations) throws DataAccessException {
                operations.multi();
                List range = operations.opsForList().range(key, 0, count - 1);
                operations.opsForList().rightPushAll(key, ArrayUtil.toArray(range, Object.class));
                operations.opsForList().trim(key, count, -1);
                return operations.exec();
            }
        });
        if (CollectionUtils.isEmpty(txResults)) {
            return Collections.emptyList();
        }
        final Object object = txResults.get(0);
        if (object instanceof ArrayList) {
            //返回最终结果
            return (List<T>) object;
        }
        return Collections.emptyList();
    }

    public Object rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

//    public Object rightPop(String key, long second) {
//        return redisTemplate.opsForList().rightPop(key, second, TimeUnit.SECONDS);
//    }

    public Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public Object getCacheValue(String key) {
        return redisTemplate.boundValueOps(key);
    }


    public void sortZadd(String redisKey, double score, Object obj) {
        redisTemplate.opsForZSet().add(redisKey, obj, score);
    }

    public void sortZremove(String redisKey, Object... obj) {
        redisTemplate.opsForZSet().remove(redisKey, obj);
    }

    public Set<Object> sortRangeWithScore(String redisKey, double scoreMin, double scoreMax, int start, int count) {
        Set<Object> t = redisTemplate.opsForZSet().rangeByScore(redisKey, scoreMin, scoreMax, start, count);
        if (t == null || t.isEmpty()) {
            return null;
        }
        redisTemplate.opsForZSet().removeRangeByScore(redisKey, scoreMin, scoreMax);
        return t;
    }

    public Set<Object> sortRangeWithScoreNotDel(String redisKey, double scoreMin, double scoreMax, int start, int count) {
        Set<Object> t = redisTemplate.opsForZSet().rangeByScore(redisKey, scoreMin, scoreMax, start, count);
        if (t == null || t.isEmpty()) {
            return null;
        }
        return t;
    }

    public Double zScore(String redisKey, Object value) {
        return redisTemplate.opsForZSet().score(redisKey, value);
    }

    public Set<Object> sortRange(String redisKey, long start, long end) {
        Set<Object> t = redisTemplate.opsForZSet().range(redisKey, start, end);
        if (t == null || t.isEmpty()) {
            return null;
        }
        return t;
    }


    /**
     * 发布数据
     *
     * @param channel
     * @param value
     * @return
     */
    public boolean convertAndSend(final String channel, Object value) {
        boolean result = false;
        try {
            redisTemplate.convertAndSend(channel, value);
            result = true;
        } catch (Exception e) {
            logger.error("set cache error", e);
        }
        return result;
    }

    /**
     * 发布数据
     *
     * @param channel
     * @param value
     * @return
     */
    public boolean publish(final String channel, String value) {
        boolean result = false;
        try {
            byte[] rawChannel = channel.getBytes(StandardCharsets.UTF_8);
            byte[] rawMessage = value.getBytes(StandardCharsets.UTF_8);
            Object execute = redisTemplate.execute(connection -> {
                connection.publish(rawChannel, rawMessage);
                return null;
            }, true);
            result = execute != null;
        } catch (Exception e) {
            logger.error("set cache error", e);
        }


        return result;
    }

    public Set<ZSetOperations.TypedTuple<Object>> zRangeWithScores(String redisKey, long start, long end) {
        final var typedTuples = redisTemplate.opsForZSet().rangeWithScores(redisKey, start, end);
        if (CollectionUtils.isEmpty(typedTuples)) {
            return Collections.emptySet();
        }
        return typedTuples;
    }

    public Set<Object> zRangeByScore(String redisKey, double min, double max) {
        final var set = redisTemplate.opsForZSet().rangeByScore(redisKey, min, max);
        if (CollectionUtils.isEmpty(set)) {
            return Collections.emptySet();
        }
        return set;
    }
}
