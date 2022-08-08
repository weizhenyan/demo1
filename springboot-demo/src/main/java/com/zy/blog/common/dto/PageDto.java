package com.zy.blog.common.dto;

import lombok.Data;

@Data
public class PageDto {

    /**
     * 每页显示大小
     */
    private long  pageSize;

    /**
     * 当前页码
     */
    private  long currentPage;

    /**
     * 最大页数
     */
    private  long maxCurrent;

    /**
     * 数据总条数
     */
    private  long total;

    public void setTotal(long total) {
        if(pageSize != 0){
            if(total % pageSize != 0){
                maxCurrent = total / pageSize + 1;
            }else {
                maxCurrent = total / pageSize;
            }
        }
    }
}