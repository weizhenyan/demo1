package com.wzy.demo.service.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * BaseDTO
 *
 * @author 飞燕
 * @date   2022/8/8 18:55
 */
@Data
@ApiModel("BaseDTO")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseDTO implements Serializable {

}
