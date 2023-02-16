package com.bjs.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjs.contract.entity.CoTriggerOrder;

/**
 * @author bjs code generator
 * @date 2022-11-11 10:46:36
 */
public interface CoTriggerOrderService extends IService<CoTriggerOrder> {

    /**
     * 撤销用户已生效的委托单
     *
     * @param orderId
     * @return
     */
    boolean cancelActiveTriggerOrderById(Long orderId,Long uid);

    /**
     * 撤销用户已生效的委托单
     *
     * @param uid
     * @return
     */
    boolean cancelAllActiveTriggerOrder(Long uid);

    int getTriggerOrderCount(long id);
}
