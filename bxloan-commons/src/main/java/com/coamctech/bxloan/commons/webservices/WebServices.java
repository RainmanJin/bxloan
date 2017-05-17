package com.coamctech.bxloan.commons.webservices;



import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.stereotype.Component;

import com.coamctech.bxloan.commons.utils.Utils;
import com.coamctech.bxloan.commons.GlobalConstants;


@Component
public class WebServices {
	
private static final String WS_CONFIG = "webservices.properties";
	
	private static Properties config = Utils.loadPropertiesFileFromClassPath(WS_CONFIG);
	
	private static JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
	
	private Map<String, Client> clients = new HashMap<String, Client>();
	
	/**
	 * 一次性初始化所有WebServices，对于需要调用的服务属于强依赖
	 * 若需要调用的WebServices有一个未启动，则导致后续WebServices都不能正常初始化
	 */
	@Deprecated
	public void initClients() {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Properties config = Utils.loadPropertiesFileFromClassPath(WS_CONFIG);
		for (String serviceName : config.stringPropertyNames()) {
			String wsdlUrl = config.getProperty(serviceName);
			Client client = dcf.createClient(wsdlUrl);
			HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
			HTTPClientPolicy policy = new HTTPClientPolicy();
//			policy.setConnectionTimeout(1); //连接超时时间
			policy.setReceiveTimeout(5000); //请求超时时间
			httpConduit.setClient(policy);
			clients.put(serviceName, client);
		}
	}
	
	private Client initClient(String serviceName) {
		Client client = clients.get(serviceName);
		if (client == null) {
			String wsdlUrl = config.getProperty(serviceName);
			client = dcf.createClient(wsdlUrl);
			HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
			HTTPClientPolicy policy = new HTTPClientPolicy();
//			policy.setConnectionTimeout(1); //连接超时时间
			policy.setReceiveTimeout(GlobalConstants.CONN_RISK_TIME); //请求超时时间
			httpConduit.setClient(policy);
			clients.put(serviceName, client);
		}
		return client;
	}
	/**
	 * 调用WebServices方法
	 * @param serviceName WebServices服务名(对应ws.properties里的key)
	 * @param methodName 调用的方法名
	 * @param params 参数
	 * @return json字符串
	 * @throws Exception 
	 */
	public String invoke(String serviceName, String methodName, Object... params) throws Exception {
		Client client = initClient(serviceName);
		return (String) client.invoke(methodName, params)[0];
	}
}
