package com.example.apigateway.controller;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@EnableAutoConfiguration
@EnableSwagger2
public class DocumentationController implements SwaggerResourcesProvider {
    @Override
    public List get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("users-api", "/users/v2/api-docs", "2.0"));
        resources.add(swaggerResource("comments-api", "/comments/v2/api-docs", "2.0"));
        resources.add(swaggerResource("posts-api", "/posts/v2/api-docs", "2.0"));
        return resources;
    }
    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}