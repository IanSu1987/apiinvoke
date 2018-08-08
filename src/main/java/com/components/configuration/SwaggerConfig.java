package com.components.configuration;

/**
 * @author Ian.Su
 * @version $Id: SwaggerConfig.java, v 0.1 2017/7/19 8:44 Ian.Su Exp $
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Ian.Su on 2016/12/29.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了 （访问页面就可以看到效果了）
     *
     */
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.components.web.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("公共第三方接口调用项目")
                .description("集成hibernate、错误日志入库、日志查询、提醒、自检等功能")
                .termsOfServiceUrl("")
                .contact("苏印")
                .version("1.0")
                .build();
    }
}