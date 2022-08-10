package com.wzy.demo.service.service.impl.es;

import com.wzy.demo.service.api.dto.DemoESDTO;
import com.wzy.demo.service.base.BaseESServiceImpl;
import com.wzy.demo.service.pojo.es.DemoES;
import com.wzy.demo.service.repository.DemoESRepository;
import com.wzy.demo.service.service.es.DemoESService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.zxp.esclientrhl.auto.intfproxy.ESCRepository;

/**
 * DemoESServiceImpl
 *
 * @author 飞燕
 * @date   2022/8/8 19:50
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DemoESServiceImpl
        extends BaseESServiceImpl<DemoES, DemoESDTO, Long>
        implements DemoESService {

    private final DemoESRepository repository;

    private final RedissonClient redissonClient;

    @Override
    public QueryBuilder getQueryBuilder(DemoESDTO dto) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (null != dto.getId() && 0 != dto.getId()) {
            builder.must(QueryBuilders.termQuery("id", dto.getId()));
        }
        return builder;
    }

    @Override
    public ESCRepository<DemoES, Long> getEscRepository() {
        return repository;
    }
}
