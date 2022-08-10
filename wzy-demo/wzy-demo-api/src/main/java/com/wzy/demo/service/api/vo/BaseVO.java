package com.wzy.demo.service.api.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * BaseVO
 *
 * @author 飞燕
 * @date   2022/8/8 19:58
 */
@Data
@ApiModel("BaseVo")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseVO implements Serializable {

}
