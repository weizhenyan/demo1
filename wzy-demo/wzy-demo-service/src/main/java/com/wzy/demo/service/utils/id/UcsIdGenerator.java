package com.wzy.demo.service.utils.id;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.wzy.demo.service.pojo.Demo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * UcsIdGenerator
 *
 * @author 飞燕
 * @date   2022/8/9 11:33
 */
@Slf4j
@Component
public class UcsIdGenerator implements IdentifierGenerator {

    private final Map<String, String> redisKeys;

    private final RedissonClient redissonClient;

    private final Long defaultId = 50000000L;

    private final String AUTO_REDIS_KEY = "AUTO_REDIS_KEY";

    public UcsIdGenerator(RedissonClient redissonClient) {
        redisKeys = new HashMap<String, String>() {{
            put(Demo.class.getName(), StrUtil.toUnderlineCase(Demo.class.getName()) + AUTO_REDIS_KEY);
        }};
        this.redissonClient = redissonClient;
    }

    @Override
    public Number nextId(Object entity) {
        return redisKeys.entrySet().stream().map(s -> {
            if (s.getKey().equals(entity.getClass().getName())) {
                RAtomicLong idGen = redissonClient.getAtomicLong(s.getValue());
                if (!idGen.isExists()) {
                    idGen.set(defaultId);
                }
                return idGen.incrementAndGet();
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList()).get(0);
    }
}
