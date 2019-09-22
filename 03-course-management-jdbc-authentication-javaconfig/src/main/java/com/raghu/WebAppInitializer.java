package com.raghu;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.raghu.config.ApplicationContextConfig;
import com.raghu.config.SpringAppBeanConfiguration;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationContextConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringAppBeanConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}