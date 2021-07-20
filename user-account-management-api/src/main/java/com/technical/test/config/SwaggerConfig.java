package com.technical.test.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;

import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Data
@NoArgsConstructor
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = "info.app")
public class SwaggerConfig {

    public static final String SCANNED_PACKAGE = "com.technical.test";
    private String name;
    private String description;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(SCANNED_PACKAGE))
                .build().apiInfo(apiEndPointsInfo()).useDefaultResponseMessages(false);

    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title(name)
                .description(description)
                .build();
    }
}