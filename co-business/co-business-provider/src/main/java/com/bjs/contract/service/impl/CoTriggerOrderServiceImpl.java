package com.bjs.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bijinsuo.common.utils.enums.OrderStatus;
import com.bijinsuo.common.utils.enums.TriggerStatusEnum;
import com.bjs.contract.entity.CoOrder;
import com.bjs.contract.entity.CoTriggerOrder;
import com.bjs.contract.mapper.CoTriggerOrderMapper;
import com.bjs.contract.service.CoTriggerOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bjs code generator
 * @date 2022-11-11 10:46:36
 */
@Service
public class CoTriggerOrderServiceImpl extends ServiceImpl<CoTriggerOrderMapper, CoTriggerOrder> implements CoTriggerOrderService {
    @Resource
    private CoTriggerOrderMapper coTriggerOrderMapper;

    @Override
    public boolean cancelActiveTriggerOrderById(Long orderId, Long uid) {
        CoTriggerOrder coTriggerOrder = super.getById(orderId);
        if (coTriggerOrder == null || !coTriggerOrder.getUid().equals(uid)) {
            return false;
        }
        if (!coTriggerOrder.getStatus().equals(TriggerStatusEnum.ACTIVE.getCode())) {
            return false;
        }
        UpdateChainWrapper<CoTriggerOrder> updateChainWrapper = super.update();
        return updateChainWrapper
                .eq("id", coTriggerOrder.getId())
                .eq("status", coTriggerOrder.getStatus())
                .set("status", TriggerStatusEnum.CANCELED.getCode()).update();
    }

    @Override
    public boolean cancelAllActiveTriggerOrder(Long uid) {
        UpdateChainWrapper<CoTriggerOrder> updateChainWrapper = super.update();
        updateChainWrapper
                .eq("uid", uid)
                .eq("status", TriggerStatusEnum.ACTIVE.getCode())
                .set("status", TriggerStatusEnum.CANCELED.getCode()).update();
        return true;
    }

    @Override
    public int getTriggerOrderCount(long id) {
        LambdaQueryWrapper<CoTriggerOrder> query = new LambdaQueryWrapper<>();
        // 查询订单状态
        List<Integer> coTriggerOrderStatus = new ArrayList<>();
        coTriggerOrderStatus.add(TriggerStatusEnum.ACTIVE.getCode());

        query.eq(CoTriggerOrder::getUid, id);
        query.in(CoTriggerOrder::getStatus,coTriggerOrderStatus);
        return coTriggerOrderMapper.selectCount(query).intValue();
    }
}
