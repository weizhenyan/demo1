package com.wzy.demo.service.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DataPageDTO
 *
 * @author 飞燕
 * @date   2022/8/8 19:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页对象")
@Builder
public class DataPageDTO<T extends BaseDTO> implements Serializable {
    /**
     * 总数
     */
    @ApiModelProperty(name = "total", value = "总数")
    private Long total;
    /**
     * list
     */
    @ApiModelProperty(name = "list", value = "数据集合")
    private List<T> list;
}
