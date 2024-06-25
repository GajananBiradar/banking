package com.houseclay.zebra.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiKey apiKey() {
        return new ApiKey("Token Access","Authorization", SecurityScheme.In.HEADER.name());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScope = {new AuthorizationScope("Unlimited","Full API Access")};
        return Collections.singletonList(new SecurityReference("Token Access",authorizationScope));
    }

    @Bean
    public Docket configureSwagger2() {

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData())
               .securitySchemes(Arrays.asList(apiKey())).
                securityContexts(Arrays.asList(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.houseclay.zebra"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo metaData(){
       ApiInfo apiInfo = new ApiInfo("HouseClay(Zebra) Backend Portal", "Apis for Backend", "1.0.0",
               "Terms of Service", new Contact("HouseClay","http://www.houseclay.com","support@houseclay.com").toString(),"HouseClay Licence","http://www.houseclay.com");
    return apiInfo;
    }

    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("test-app-client-id")
                .clientSecret("test-app-client-secret")
                .realm("test-app-realm")
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .enableCsrfSupport(false)
                .build();
    }
}
