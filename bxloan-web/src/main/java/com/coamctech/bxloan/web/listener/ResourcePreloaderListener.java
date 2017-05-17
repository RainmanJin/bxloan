package com.coamctech.bxloan.web.listener;


import org.springframework.context.ApplicationContext;

import com.coamctech.bxloan.commons.listener.AbstractResourcePreloaderListener;
import com.coamctech.bxloan.commons.webservices.WebServices;
import com.coamctech.bxloan.service.DataDictLoader;

/**
 * 资源预加载
 * 
 * @author wh
 * @lastModified 2014-11-14 18:25:21
 */
public class ResourcePreloaderListener extends AbstractResourcePreloaderListener {

	@Override
	protected void preload(ApplicationContext applicationContext) {
		initDataDict(applicationContext);
	}

	private void initDataDict(ApplicationContext applicationContext) {
		DataDictLoader loader = applicationContext.getBean(DataDictLoader.class);
		loader.preLoad();
	}

}
