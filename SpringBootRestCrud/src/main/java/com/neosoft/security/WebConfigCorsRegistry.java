package com.neosoft.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfigCorsRegistry implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
        .addMapping("/**")
        .allowedMethods("*")
        .allowedHeaders("*")
        .allowedOrigins("*")
        .allowCredentials(true);
	}

}
