package com.wzy.demo.service.constant;

/**
 * RedissonConstants
 *
 * @author 飞燕
 * @date   2022/8/9 11:35
 */
public interface RedissonConstants {
    String PREFIX = "wzy_demo";
    /**
     * 当前所有用户的手机号
     */
    String CURRENT_DEMO_PHONE = PREFIX + "_wzy_current_member_phone";
}
