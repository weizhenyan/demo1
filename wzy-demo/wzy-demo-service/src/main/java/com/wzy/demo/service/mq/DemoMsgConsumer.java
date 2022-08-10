//package com.wzy.demo.service.mq;
//
//import com.wzy.demo.service.pojo.es.DemoES;
//import com.wzy.demo.service.service.es.DemoESService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
///**
// * DemoMsgConsumer
// *
// * @author 飞燕
// * @date   2022/8/8 20:11
// */
//@Slf4j
//@Component
//@RocketMQMessageListener(topic = "wzy_register_message_topic_dev", consumerGroup = "GID_wzy_register_message_topic_dev_consumer")
//@RequiredArgsConstructor
//public class DemoMsgConsumer implements RocketMQListener<DemoES> {
//
//    private final DemoESService demoESService;
//
//    @Override
//    public void onMessage(DemoES demo) {
//        try {
//            demoESService.save(demo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
