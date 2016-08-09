package com.pineone.icbms.so.server.config;

import java.util.Properties;

import com.pineone.icbms.so.iot.servicerunner.ServiceRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.pineone.icbms.so" })
public class WebAppContext extends WebMvcConfigurerAdapter
{

	private static final String	VIEW_RESOLVER_PREFIX	= "/WEB-INF/";
	private static final String	VIEW_RESOLVER_SUFFIX	= ".jsp";


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/static/**")
				.addResourceLocations("/static/");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}

	@Bean
	public SimpleMappingExceptionResolver exceptionResolver()
	{
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

		Properties exceptionMappings = new Properties();

		exceptionMappings.put(
				"net.petrikainulainen.spring.testmvc.todo.exception.TodoNotFoundException",
				"error/404");
		exceptionMappings.put("java.lang.Exception", "error/error");
		exceptionMappings.put("java.lang.RuntimeException", "error/error");

		exceptionResolver.setExceptionMappings(exceptionMappings);

		Properties statusCodes = new Properties();

		statusCodes.put("error/404", "404");
		statusCodes.put("error/error", "500");

		exceptionResolver.setStatusCodes(statusCodes);

		return exceptionResolver;
	}

	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

		return viewResolver;
	}

}
