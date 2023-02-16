package com.bjs.contract.controller;

import com.alibaba.nacos.api.utils.StringUtils;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.result.ApiResultType;
import com.bijinsuo.common.utils.MD5Util;
import com.bjs.contract.entity.OpenApiToken;
import com.bjs.contract.service.OpenApiTokenService;
import org.apache.commons.net.util.SubnetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

@Controller
@Scope("prototype")
public class BaseOpenApiController {

	protected static final Logger logger = LoggerFactory.getLogger(BaseOpenApiController.class);
	//token生成固定串定义
	public static final String MD5_TOKEN_STRING = "chainupsanwei@2017";

	public static final String MD5_SECRET_KEY = "gfhe*4tr(Asxxf@rtHF";
	//请求过期时间30秒/30000毫秒
	private static final long TIME_EXPIRED = 60000;

	@Autowired
	private OpenApiTokenService openApiTokenService;

	/**
	 * api_key，time,sign 校验
	 * @param request
	 * @param requestMap
	 * @return
	 */
	public ApiResultType commonCheck(HttpServletRequest request,Map<String, String> requestMap) {
		requestMap.put("api_key", request.getHeader("X-CH-APIKEY"));
		requestMap.put("time", request.getHeader("X-CH-TS"));
		requestMap.put("sign", request.getHeader("X-CH-SIGN"));
		return this.check(request, requestMap);
	}

	/**
	 * 通过token 获取用户信息
	 *
	 * @param token
	 * @return
	 */
	public Long getUserById(String token) {
		OpenApiToken tokenObject = openApiTokenService.findByOpenApiToken(token);
		if (tokenObject == null) {
			return null;
		}
		return Long.valueOf(tokenObject.getUid());
	}

	/**
	 * 产生token
	 * @return
	 */
	public static String generateToken(){
		return MD5Util.getMD5(MD5_TOKEN_STRING+System.currentTimeMillis()+Math.random());
	}

	public static String generateSecretKey() {
		return MD5Util.getMD5(MD5_SECRET_KEY+System.currentTimeMillis()+Math.random());
	}

	/**
	 * 签名校验
	 * @param params
	 * @return
	 */
	public boolean checkSign(Map<String,String> params){
		String sign = params.get("sign");
		if(sign == null || sign.isEmpty()) {
			return false;
		}
//		logger.info("params = {}", params);
		String secret = openApiTokenService.getSecretKey(params.get("api_key"));
		if(null == secret) {
			logger.info("secret key not existed, params = {}", params);
			return false;
		}
	    // 先将参数以其参数名的字典序升序进行排序
	    Map<String, String> sortedParams = new TreeMap<String, String>(params);
	    Set<Entry<String, String>> entrys = sortedParams.entrySet();

	    // 遍历排序后的字典，将所有参数按"keyvalue"格式拼接在一起
	    StringBuilder basestring = new StringBuilder();
	    for (Entry<String, String> param : entrys) {
	    	if(param.getKey().equals("sign")){//去掉签名字段
	    		continue;
	    	}

	        if(!StringUtils.isBlank(param.getValue())) {
	        	basestring.append(param.getKey());
	        	basestring.append(param.getValue().toString());
	        }
	    }
	    basestring.append(secret);

	    // 使用MD5对待签名串求签
	    String curSign = MD5Util.getMD5(basestring.toString());
//		logger.debug("==============sign={}, serverSign={}, string={}", sign, curSign, basestring);
		boolean returnFlag = curSign.equals(sign);
		if (!returnFlag) {
			logger.debug("open api sign error==============sign={}, serverSign={}, string={}", sign, curSign, basestring);
		}
	    return returnFlag;
	}


	/**
	 * 签名校验 外部传入私钥
	 */
	public boolean checkSignBySign(Map<String,String> params, String secret){
		String sign = params.get("sign");
		if(sign == null || sign.isEmpty()) {
			return false;
		}
//		logger.info("params = {}", params);
		if(null == secret) {
			logger.info("secret key not existed, params = {}", params);
			return false;
		}
		// 先将参数以其参数名的字典序升序进行排序
		Map<String, String> sortedParams = new TreeMap<String, String>(params);
		Set<Entry<String, String>> entrys = sortedParams.entrySet();

		// 遍历排序后的字典，将所有参数按"keyvalue"格式拼接在一起
		StringBuilder basestring = new StringBuilder();
		for (Entry<String, String> param : entrys) {
			if(param.getKey().equals("sign")){//去掉签名字段
				continue;
			}

			if(!StringUtils.isBlank(param.getValue())) {
				basestring.append(param.getKey());
				basestring.append(param.getValue().toString());
			}
		}
		basestring.append(secret);

		// 使用MD5对待签名串求签
		String curSign = MD5Util.getMD5(basestring.toString());
//		logger.debug("==============sign={}, serverSign={}, string={}", sign, curSign, basestring);
		boolean returnFlag = curSign.equals(sign);
		if (!returnFlag) {
			logger.debug("open api sign error==============sign={}, serverSign={}, string={}", sign, curSign, basestring);
		}
		return returnFlag;
	}



	/**
	 * 检查参数
	 * @param params
	 * @return
	 */
	public Boolean checkParams(Map<String, String> params) {
		for(Entry<String, String> param : params.entrySet()) {
			if(StringUtils.isBlank(param.getValue())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查参数
	 * @param params
	 * @return
	 */
	public Boolean checkParams(Map<String, String> params, List<String> notCheckParams) {
		for(Entry<String, String> param : params.entrySet()) {
			if(notCheckParams.indexOf(param.getKey())>=0) {
				continue;
			}
			if(StringUtils.isBlank(param.getValue())) {
//				logger.info(param.getValue());
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验用户是否有权限
	 * @param token
	 * @return
	 */
	public Boolean checkAuthority(String token) {
		if(StringUtils.isBlank(token)) {
			return false;
		}
		OpenApiToken tokenObject = openApiTokenService.findByOpenApiToken(token);
		if(null != tokenObject && tokenObject.getIsSuperUser()==1) {
			return true;
		}
		return false;

	}

	/**
	 * 校验代理人后台用户是否有权限
	 * @param token
	 * @return
	 */
	public Boolean checkAgentAuthority(String token) {
		if(StringUtils.isBlank(token)) {
			return false;
		}
		OpenApiToken tokenObject = openApiTokenService.findByOpenApiToken(token);
		if(null != tokenObject) {
			return true;
		}
		return false;

	}

	/**
	 * 检查ip是否在白名单中
	 * @param request
	 * @param token
	 * @return
	 */
	public Boolean checkIp(HttpServletRequest request, String token) {
		String ip = this.getRemoteAddr(request);
		OpenApiToken tokenObject = openApiTokenService.findByOpenApiToken(token);
		if(null == tokenObject) {
			logger.info("checkIp not found token; token={}", token);
			return false;
		}
		String ipString = tokenObject.getBelieveIps();

		String[] believeIpArr = ipString.split(",");
		for (String believeIp : believeIpArr) {
			if(isInRange(ip, believeIp)) return true;
		}
		logger.info("checkIp ip not believe; token={}, reqIp={}, believeIp={}", token, ip, ipString);
		return false;

	}
	public static boolean isInRange(String ip, String network) {
		if(ip.equals(network) ){
			return true;
		}
		try {
			SubnetUtils.SubnetInfo subnetInfo = new SubnetUtils(network).getInfo();
			return subnetInfo.isInRange(ip);
		}catch (Exception e){
			return false;
		}
	}

	/**
	 * open api 统一检查入口
	 * @param request
	 * @param params
	 * @return
	 */
	public ApiResultType check(HttpServletRequest request, Map<String, String> params) {
		if(!checkParams(params) || (StringUtils.isBlank(params.get("api_key")))) {
			return ApiResultType.PARAMETER_ILLEGAL;
		}
		//判断请求时间是否过期 20200511
		if(!checkTimeExpired(params.get("time"))) {
			return ApiResultType.REQUEST_TIME_EXPIRED;
		}
		if(!checkSign(params)) {
			return ApiResultType.PARAMETER_SIGN_ILLEGAL;
		}
		if(!checkIp(request, params.get("api_key"))) {
			return ApiResultType.IP_ILLEGAL;
		}
		var user = UserContextHolder.user.get();
		if (user == null) {
			user = new UserContextHolder.User();
			user.setId(getUserById(params.get("api_key")));
			UserContextHolder.set(user);
		}

		return ApiResultType.SUCCESS;
	}

	/**
	 * open api 统一检查入口 - 某些参数不需要检验是否为null
	 * @param request
	 * @param params
	 * @return
	 */
	public ApiResultType check(HttpServletRequest request, Map<String, String> params, List<String> notCheckParamsList) {
		if(!checkParams(params,notCheckParamsList) || (StringUtils.isBlank(params.get("api_key")))) {
			return ApiResultType.PARAMETER_ILLEGAL;
		}
		//判断请求时间是否过期 20200511
		if(!checkTimeExpired(params.get("time"))) {
			return ApiResultType.REQUEST_TIME_EXPIRED;
		}
		if(!checkSign(params)) {
			return ApiResultType.PARAMETER_SIGN_ILLEGAL;
		}
		if(!checkIp(request, params.get("api_key"))) {
			return ApiResultType.IP_ILLEGAL;
		}
		return ApiResultType.SUCCESS;
	}

	private boolean checkTimeExpired(String time) {
		if (!StringUtils.isBlank(time)) {
			long requset_time = 0;
			try {
				requset_time = Long.parseLong(time);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			//当前时间
			long now_time = System.currentTimeMillis();
			//判断请求时间是13位还是10位,做兼容
			if (String.valueOf(now_time).length() > time.length()) {
				requset_time = requset_time * 1000;
			}

			long diff = now_time - requset_time;
			//请求时间与当前时间之差必须在设置时间范围之内,暂定30秒
			if (diff <= TIME_EXPIRED) {
				return true;
			}else{
				logger.error("请求时间{},当前时间{},相差:{}毫秒,打回.",requset_time,now_time,diff);
			}
		}else{
			return true;
		}
		return false;
	}

	/**
	 * open api 统一检查入口 - 某些参数不需要检验是否为null，是否校验用户特殊权限
	 * @param request
	 * @param params
	 * @return
	 */
	public ApiResultType check(HttpServletRequest request, Map<String, String> params, List<String> notCheckParamsList, Boolean isCheckAuthority) {
		if(!checkParams(params,notCheckParamsList) || (StringUtils.isBlank(params.get("api_key")))) {
			return ApiResultType.PARAMETER_ILLEGAL;
		}
		//判断请求时间是否过期 20200511
		if(!checkTimeExpired(params.get("time"))) {
			return ApiResultType.REQUEST_TIME_EXPIRED;
		}
		if(!checkSign(params)) {
			return ApiResultType.PARAMETER_SIGN_ILLEGAL;
		}
		if(!checkIp(request, params.get("api_key"))) {
			return ApiResultType.IP_ILLEGAL;
		}
		if(isCheckAuthority && !checkAuthority(params.get("api_key"))) {
			return ApiResultType.USER_NO_AUTHORITY;
		}
		return ApiResultType.SUCCESS;
	}
    public String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) {
            ip = request.getRemoteAddr();
        } else {
            StringTokenizer tokenizer = new StringTokenizer(ip, ",");
            for (int i = 0; i < tokenizer.countTokens() - 1; i++) {
                tokenizer.nextElement();
            }
            ip = tokenizer.nextToken().trim();
            if (ip.equals("")) {
                ip = null;
            }
        }
        if (ip == null) {
            ip = "0.0.0.0";
        }
        return ip;
    }

}
