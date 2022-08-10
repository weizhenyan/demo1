package com.wzy.demo.service.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * MemberEsSearchDTO
 *
 * @author 飞燕
 * @date   2022/8/8 19:19
 */
@Data
@ApiModel("MemberEsSearchDTO")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class DemoEsSearchDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键")
    private Long id;

    /**
     * 手机号 来源:yijiayou_user
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

}