//package com.bjs.contract.listener;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
//import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
//import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
//import org.springframework.messaging.Message;
//import org.springframework.stereotype.Component;
//
///**
// * @author Watson
// */
//
//@Slf4j
//@Component
//@RocketMQTransactionListener(txProducerGroup )
//public class LocalTransactionListener implements RocketMQLocalTransactionListener {
//
//    @Override
//    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
//        return RocketMQLocalTransactionState.COMMIT;
//    }
//
//    @Override
//    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
//        msg.getHeaders()
//        return null;
//    }
//}
