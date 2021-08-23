package com.spring.school.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private String version;

  @Bean
  public Docket apiV1() {
    version = "v1";

    return getDocket(version, "/api/v1//**");
  }

  @Bean
  public Docket apiV2() {
    version = "v2";

    return getDocket(version, "/api/v2/**");
  }

  private Docket getDocket(String version, String apiPath) {
    List<ResponseMessage> responseMessages = new ArrayList<>();
    responseMessages(responseMessages);

    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .groupName(version)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.spring.school"))
        .paths(PathSelectors.ant(apiPath))
        .build()
        .apiInfo(apiInfo(version))
        .globalResponseMessage(RequestMethod.GET, responseMessages)
        .globalResponseMessage(RequestMethod.POST, responseMessages)
        .globalResponseMessage(RequestMethod.PUT, responseMessages)
        .globalResponseMessage(RequestMethod.DELETE, responseMessages);
  }

  private void responseMessages(List<ResponseMessage> responseMessages) {
    extracted(responseMessages, 200, "OK");
    extracted(responseMessages, 201, "Created");
    extracted(responseMessages, 400, "Bad Request");
    extracted(responseMessages, 401, "Unauthorized");
    extracted(responseMessages, 403, "Forbidden");
    extracted(responseMessages, 404, "Not found");
  }

  private void extracted(List<ResponseMessage> responseMessages, int code, String message) {
    responseMessages.add(new ResponseMessageBuilder()
        .code(code)
        .message(message)
        .build());
  }

  private ApiInfo apiInfo(String version) {
    return new ApiInfoBuilder()
        .title("School API")
        .version(version)
        .description("")
        .build();
  }
}
