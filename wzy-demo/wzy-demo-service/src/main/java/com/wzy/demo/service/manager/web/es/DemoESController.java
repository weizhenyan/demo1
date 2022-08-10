package com.wzy.demo.service.manager.web.es;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.wzy.demo.service.api.common.Result;
import com.wzy.demo.service.api.dto.DataPageDTO;
import com.wzy.demo.service.api.dto.DemoESDTO;
import com.wzy.demo.service.api.dto.SearchPageDTO;
import com.wzy.demo.service.service.es.DemoESService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * DemoESController
 *
 * @author 飞燕
 * @date   2022/8/8 22:01
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/es/demo")
@Api(tags = "elastic查询服务")
@ApiSort(800)
public class DemoESController {

    private final DemoESService demoESService;

    /**
     * 分页查询
     */
    @PostMapping("/page")
    @ApiOperation(value = "ES分页查询", httpMethod = "POST")
    @ApiOperationSupport(order = 2)
    public @ResponseBody
    Result<DataPageDTO<DemoESDTO>>
    page(@Validated @RequestBody final SearchPageDTO<DemoESDTO> searchPage) {
        try {
            return Result.success(demoESService.searchPage(searchPage));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统错误");
        }
    }
}