package com.wzy.demo.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzy.demo.service.api.dto.BaseDTO;
import com.wzy.demo.service.api.dto.DataPageDTO;
import com.wzy.demo.service.api.dto.SearchPageDTO;
import com.wzy.demo.service.api.vo.BaseVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * BaseService
 *
 * @author 飞燕
 * @date   2022/8/8 20:04
 */
public interface BaseService<T, V extends BaseVO, D extends BaseDTO> extends IService<T> {

    DataPageDTO<D> searchPage(SearchPageDTO<D> searchPage);

    List<V> getByIds(Collection<? extends Serializable> idList);
}
