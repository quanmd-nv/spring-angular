package com.angular.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

	private final Logger log = LoggerFactory.getLogger(WebFluxConfig.class);

	private final ApplicationPropertiesConfig config;

	@Autowired
	private ApplicationContext context;

	public WebFluxConfig(ApplicationPropertiesConfig config) {
		this.config = config;
	}

	@Bean
	public ITemplateResolver thymeleafTemplateResolver() {
		final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(this.context);
		resolver.setPrefix("classpath:static/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCacheable(false);
		resolver.setCheckExistence(false);
		return resolver;
	}

	@Bean
	public CorsWebFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = config.getCors();
		if (corsConfiguration.getAllowedOrigins() != null && !corsConfiguration.getAllowedOrigins().isEmpty()) {
			log.debug("Registering CORS filter");
			source.registerCorsConfiguration("/api/**", corsConfiguration);
			source.registerCorsConfiguration("/test/**", corsConfiguration);
		}
		return new CorsWebFilter(source);
	}

	@Bean
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("i18n/messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(5);
		return messageSource;
	}

}
