package com.wzy.demo.service.job;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * DemoJob
 *
 * @author 飞燕
 * @date   2022/8/8 20:06
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
@Api(tags = "demo job")
public class DemoJob {

}
