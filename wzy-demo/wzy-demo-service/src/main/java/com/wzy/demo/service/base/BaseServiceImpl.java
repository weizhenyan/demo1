package com.wzy.demo.service.base;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzy.demo.service.api.dto.BaseDTO;
import com.wzy.demo.service.api.dto.DataPageDTO;
import com.wzy.demo.service.api.dto.SearchPageDTO;
import com.wzy.demo.service.api.vo.BaseVO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BaseServiceImpl
 *
 * @author 飞燕
 * @date   2022/8/8 20:05
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T, V extends BaseVO, D extends BaseDTO> extends ServiceImpl<M, T> implements BaseService<T, V, D> {
    /**
     * VO对象类型
     */
    public Class<V> clazz;
    /**
     * DTO对象类型
     */
    public Class<D> clazzz;


    public BaseServiceImpl() {
        final Type superclass = getClass().getGenericSuperclass();
        final ParameterizedType parameterizedType;
        if (superclass instanceof ParameterizedType) {
            parameterizedType = (ParameterizedType) superclass;
            final Type[] typeArray = parameterizedType.getActualTypeArguments();
            if (typeArray != null && typeArray.length > 0) {
                clazz = (Class<V>) typeArray[2];
                clazzz = (Class<D>) typeArray[3];
            }
        }
    }

    @Override
    public DataPageDTO<D> searchPage(final SearchPageDTO<D> searchPage) {
        final IPage<T> page = new Page<>(searchPage.getPageNum(), searchPage.getPageSize());
        if (null != searchPage) {
            baseMapper.selectPage(page, getQuery(searchPage));
        } else {
            baseMapper.selectPage(page, null);
        }
        final List<D> records = page.getRecords().stream()
                .map(f -> BeanUtil.copyProperties(f, clazzz))
                .collect(Collectors.toList());
        return new DataPageDTO<D>(page.getTotal(), records);
    }

    public abstract LambdaQueryWrapper<T> getQuery(SearchPageDTO<D> searchPage);

    @Override
    public List<V> getByIds(final Collection<? extends Serializable> idList) {
        return baseMapper.selectBatchIds(idList)
                .stream().map(f -> BeanUtil.copyProperties(f, clazz))
                .collect(Collectors.toList());
    }
}
