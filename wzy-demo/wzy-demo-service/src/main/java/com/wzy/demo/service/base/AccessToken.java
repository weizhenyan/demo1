package com.wzy.demo.service.base;

import cn.hutool.core.date.DateTime;
import lombok.Data;

/**
 * AccessToken
 *
 * @author 飞燕
 * @date   2022/8/8 18:52
 */
@Data
public class AccessToken {
    public String value;

    public DateTime startTime;

}
