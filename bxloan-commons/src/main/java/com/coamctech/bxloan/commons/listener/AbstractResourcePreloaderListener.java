package com.coamctech.bxloan.commons.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 使子类在Servlet环境初始化之后就可直接获得Spring的IoC容器, 从而可以执行一些预加载资源的工作
 * 
 * @author wh
 * @lastModified 2014-11-14 18:48:31
 */
public abstract class AbstractResourcePreloaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		preload(wac);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// do nothing.
	}
	
	protected abstract void preload(ApplicationContext applicationContext);

}
