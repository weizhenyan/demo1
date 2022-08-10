package com.wzy.demo.service.base;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.wzy.demo.service.api.dto.BaseDTO;
import com.wzy.demo.service.api.dto.DataPageDTO;
import com.wzy.demo.service.api.dto.SearchPageDTO;
import com.wzy.demo.service.api.eum.YesNoEnum;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.zxp.esclientrhl.auto.intfproxy.ESCRepository;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;
import org.zxp.esclientrhl.repository.Sort;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BaseESServiceImpl
 *
 * @author 飞燕
 * @date   2022/8/9 11:37
 */
@Slf4j
public abstract class BaseESServiceImpl<T extends BaseEsPojo, D extends BaseDTO, I extends Serializable> implements BaseESService<T, D, I> {

    /**
     * DTO对象类型
     */
    public Class<D> clazz;

    public BaseESServiceImpl() {
        final Type superclass = getClass().getGenericSuperclass();
        final ParameterizedType parameterizedType;
        if (superclass instanceof ParameterizedType) {
            parameterizedType = (ParameterizedType) superclass;
            final Type[] typeArray = parameterizedType.getActualTypeArguments();
            if (typeArray != null && typeArray.length > 0) {
                clazz = (Class<D>) typeArray[1];
            }
        }
    }

    @Override
    public DataPageDTO<D> searchPage(SearchPageDTO<D> searchPage) throws Exception {
        PageSortHighLight psh = new PageSortHighLight(searchPage.getPageNum(), searchPage.getPageSize());

        try {
            PageList<T> pageList = getEscRepository().search(getQueryBuilder(searchPage.getDto()), psh);
            List<D> result = pageList.getList()
                    .stream().map(z -> BeanUtil.copyProperties(z, clazz))
                    .collect(Collectors.toList());

            if (StrUtil.isNotEmpty(searchPage.getSortBy())) {
                Sort.Order sort = new Sort.Order(searchPage.getIsAsc() ? SortOrder.ASC : SortOrder.DESC
                        , StrUtil.toUnderlineCase(searchPage.getSortBy()));
                psh.setSort(new Sort(sort));
            }

            return DataPageDTO.<D>builder()
                    .total(pageList.getTotalElements())
                    .list(result)
                    .build();
        } catch (Exception e) {
            log.error("ES分页查询错误,error:{}", e.getMessage());
            throw new Exception("ES分页查询错误");
        }
    }


    @Override
    public D findById(I id) throws Exception {
        try {
            T t = getEscRepository().getById(id);
            if (null != t) {
                return BeanUtil.copyProperties(t, clazz);
            }
            return null;
        } catch (Exception e) {
            log.error("ES查询数据异常,{}", e.getMessage());
            throw new Exception("ES查询数据异常,{}");
        }
    }

    @Override
    public void deleteById(I id) throws Exception {
        try {
            T t = getEscRepository().getById(id);
            t.setState(YesNoEnum.NO.getCode());
            getEscRepository().update(t);
        } catch (Exception e) {
            log.error("ES删除数据异常,{}", e.getMessage());
            throw new Exception("ES删除数据异常,{}");
        }

    }

    @Override
    public void save(T t) throws Exception {
        try {
            getEscRepository().save(t);
        } catch (Exception e) {
            log.error("ES新增数据异常,{}", e.getMessage());
            throw new Exception("ES新增数据异常,{}");
        }
    }

    @Override
    public void save(List<T> t) throws Exception {
        try {
            getEscRepository().save(t);
        } catch (Exception e) {
            log.error("ES新增数据异常,{}", e.getMessage());
            throw new Exception("ES删除数据异常,{}");
        }
    }

    @Override
    public void updateById(T t) throws Exception {
        try {
            getEscRepository().update(t);
        } catch (Exception e) {
            log.error("ES修改数据异常,{}", e.getMessage());
            throw new Exception("ES修改数据异常,{}");
        }
    }

    public abstract QueryBuilder getQueryBuilder(D dto);

    public abstract ESCRepository<T, I> getEscRepository();
}
