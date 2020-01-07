package com.huaducloud.nginxtest.config.swagger2;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author yance
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Value("${swagger.enabled}")
    private Boolean enabled;

    @Value("${jwt.header}")
    private String tokenHeader;
    @Bean
    public Docket createAdminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台管理")
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.huaducloud.nginxtest"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts()).securitySchemes(securitySchemes());
    }

    /*@Bean
    public Docket createEpgApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("EPG")
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hgys.hgysactsys.modules.epg"))
                .paths(PathSelectors.any())
                .build();
    }*/

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("花都云(huaducloud.cn)")
                .description("花都云-测试平台")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(new ApiKey(tokenHeader, tokenHeader, "header"));
    }

    private List<SecurityContext> securityContexts() {
        SecurityContext context = SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
        return Lists.newArrayList(context);
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference(tokenHeader, authorizationScopes));
    }
}
