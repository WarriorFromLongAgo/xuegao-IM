package com.xuegao.xuegaoimcommon.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * SpringContextUtils
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	public static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	public static Object getBean(String name) {
		try {
			return applicationContext.getBean(name);
		} catch (Exception e) {
			return null;
		}
	}

}