package com.harllan.loanfirm.config;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String BASE_PACKAGE = "com.harllan.loanfirm.controller";
	private static final String API_TITLE = "Solicitação de empréstimo - API";
	private static final String API_DESCRIPTION = "REST API para solicitações de empréstimo.";
	private static final String CONTACT_NAME = "Harllan Andryê";
	private static final String CONTACT_GITHUB = "https://gtihub.com/harllanandrye";
	private static final String CONTACT_EMAIL = "...@mail.com";

	public static final String TAG_CLIENT = "Clientes";
	public static final String TAG_LOAN = "Empréstimos";
	public static final String TAG_USER = "Usuário";

	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).securityContexts(Arrays.asList(securityContext()))
			.securitySchemes(Arrays.asList(apiKey()))
			.select()
			.apis(basePackage(BASE_PACKAGE))
			.paths(PathSelectors.any()).build()
			.tags(new Tag(TAG_CLIENT, "Endpoints para o gerenciamento de clientes."))
			.tags(new Tag(TAG_LOAN, "Endpoints para o gerenciamento de empréstimos."))
			.tags(new Tag(TAG_USER, "Endpoints para o gerenciamento de usuários."))
			.apiInfo(buildApiInfo());
	}

	private ApiKey apiKey() {
		return new ApiKey(AUTHORIZATION_HEADER, "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder().title(API_TITLE).description(API_DESCRIPTION).version("0.0.1")
				.contact(new Contact(CONTACT_NAME, CONTACT_GITHUB, CONTACT_EMAIL)).build();
	}

}
