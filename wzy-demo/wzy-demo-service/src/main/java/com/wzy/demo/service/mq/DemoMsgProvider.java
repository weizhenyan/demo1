//package com.wzy.demo.service.mq;
//
//import com.wzy.demo.service.pojo.es.DemoES;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
///**
// * DemoMsgProvider
// *
// * @author 飞燕
// * @date   2022/8/8 20:11
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class DemoMsgProvider {
//
//    private static final String REGISTER_MQ_TOPIC = "wzy_register_message_topic_dev";
//    private static final String REGISTER_MQ_GROUP = "GID_wzy_register_message_topic_dev_consumer";
//
//    private final RocketMQTemplate rocketMQTemplate;
//
//    public SendResult register(DemoES demo) {
//        return rocketMQTemplate.syncSend(REGISTER_MQ_TOPIC, MessageBuilder.withPayload(demo).build());
//    }
//}
