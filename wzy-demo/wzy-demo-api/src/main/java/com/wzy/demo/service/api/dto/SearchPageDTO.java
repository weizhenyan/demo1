package com.wzy.demo.service.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * SearchPageDTO
 *
 * @author 飞燕
 * @date   2022/8/8 19:59
 */
@Data
@NoArgsConstructor
@ApiModel("分页查询通用VO")
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class SearchPageDTO<D extends BaseDTO> implements Serializable {
    /**
     * 当前页
     */
    @ApiModelProperty(name = "pageNum", value = "当前页")
    private int pageNum = 0;
    /**
     * 当前每页显示数
     */
    @ApiModelProperty(name = "pageSize", value = "当前每页显示数")
    private int pageSize = 10;
    /**
     * 查询dto
     */
    @ApiModelProperty(name = "searchVo", value = "查询dto")
    private D dto;

    @ApiModelProperty(name = "sortBy", value = "排序字段")
    private String sortBy;

    @ApiModelProperty(name = "isAsc", value = "是否正序")
    private Boolean isAsc;

    public int getLimit() {
        return (pageNum - 1) * pageSize;
    }
}
