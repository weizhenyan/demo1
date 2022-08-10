package com.wzy.demo.service.base;


import com.wzy.demo.service.api.dto.BaseDTO;
import com.wzy.demo.service.api.dto.DataPageDTO;
import com.wzy.demo.service.api.dto.SearchPageDTO;

import java.io.Serializable;
import java.util.List;

/**
 * BaseESService
 *
 * @author 飞燕
 * @date   2022/8/8 20:04
 */
public interface BaseESService<T extends BaseEsPojo, D extends BaseDTO, I extends Serializable> {

    DataPageDTO<D> searchPage(SearchPageDTO<D> searchPage) throws Exception;

    D findById(I id) throws Exception;

    void deleteById(I id) throws Exception;

    void save(T t) throws Exception;

    void save(List<T> t) throws Exception;

    void updateById(T t) throws Exception;
}
