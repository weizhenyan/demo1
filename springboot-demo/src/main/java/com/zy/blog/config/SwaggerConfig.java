package com.zy.blog.config;

import cn.hutool.core.collection.CollectionUtil;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

;import java.util.ArrayList;
import java.util.HashSet;

/**
 * @Classname SwaggerConfig
 * @Description TODO
 * @Date 2021/10/24 16:13
 * @Created by 飞燕
 */
@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

//    // 配置swagger的实例
//    @Bean
//    public Docket docket(Environment environment) {
////        ParameterBuilder tokenPar = new ParameterBuilder();
////        List<Parameter> parameters = new ArrayList<>();
////        tokenPar.name("Authorization").description("令牌")
////                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();
////        parameters.add(tokenPar.build());
//        return new Docket(DocumentationType.SWAGGER_2)
//                // 这个可以自定义页面出现的标题，描述等信息
//                .apiInfo(this.apiInfo())
//                // 是否开启swagger2，可以用作环境判断使用
//                .enable(true)
//                .pathMapping("/")
//                // 筛选
//                .select()
//                // 扫描的包
//                .apis(RequestHandlerSelectors.basePackage("com.zy.blog.web.ab"))
//                // 构建项目
//                .paths(PathSelectors.any())
//                .build();
////                .globalOperationParameters(parameters);
//    }
//
//    public ApiInfo apiInfo() {
//        Contact contact = new Contact("wzy", "url", "123@qq.com");
//        return new ApiInfo("博客系统", "博客系统中心接口信息", "v1.0", "urn:tos", contact, "Apache 2.0",
//                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
//    }


    private final OpenApiExtensionResolver openApiExtensionResolver;
    @Autowired
    public SwaggerConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }
        public ApiInfo apiInfo() {
        Contact contact = new Contact("wzy", "url", "123@qq.com");
        return new ApiInfo("博客系统", "博客系统中心接口信息", "v1.0", "urn:tos", contact, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        String groupName="2.X版本";
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .host("https://www.baidu.com")
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zy.blog.web.ab"))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                //此处调用openApiExtensionResolver的方法获取extensions集合
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
//                .securityContexts(CollectionUtil.newArrayList(securityContext())).securitySchemes(CollectionUtil.newArrayList(apiKey()));
        return docket;
    }
}
