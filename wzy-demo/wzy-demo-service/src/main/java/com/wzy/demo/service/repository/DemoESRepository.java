package com.wzy.demo.service.repository;

import com.wzy.demo.service.pojo.es.DemoES;
import org.springframework.stereotype.Repository;
import org.zxp.esclientrhl.auto.intfproxy.ESCRepository;

/**
 * DemoESRepository
 *
 * @author 飞燕
 * @date   2022/8/8 19:24
 */
@Repository
public interface DemoESRepository extends ESCRepository<DemoES, Long> {
}
