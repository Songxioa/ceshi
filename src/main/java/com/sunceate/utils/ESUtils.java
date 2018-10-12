package com.sunceate.utils;



import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * ES工具类
 * @author zhanghairong
 *
 */
public class ESUtils {
    private static TransportClient client = null;  
    //private static Properties properties = new Properties();
//	private static final Logger logger = LoggerFactory.getLogger(ESUtils.class);
	static {
		InputStream inputStream = ESUtils.class.getClassLoader().getResourceAsStream("es.properties");
	/*	try {
		//	properties.load(inputStream);
		} catch (IOException e) {
		//	logger.warn("同步日志配置文件读取失败", e);
		}*/
	}
	//获取配置信息
	private static String CLUSTERNAME = "ygces";
	private static String IPADDRESS = "172.16.91.33|172.16.91.34|172.16.91.36";
/*	private static String PAGESIZE = properties.getProperty("es.pageSize");*/

    private ESUtils() {
    	
    }
    
/**
 * 关闭对应client
 * @param client
 */
    public static void close(TransportClient client) {
    	 if (client != null) {
             try {
              client.close();
             } catch (Exception e) {
             }
             client = null;
         }
    }

/**
 * 根据默认系统默认配置初始化库,如果已经有连接则使用该连接
 * @return
 */
    public static TransportClient getClient() {

    	if(client!=null) {
            return client;
        }
    	client = newClient();
        return client;

    }

/**
 * 初始化并连接elasticsearch集群
 * 
 */
   private static TransportClient newClient() {
    	 try {
	        /**
			 * 1:设置集群名称
			 */
	    	Settings settings = Settings.builder()
					.put("client.transport.ping_timeout",60, TimeUnit.SECONDS)
					.put("cluster.name", CLUSTERNAME).build();
	        
	        /**
			 * 2：创建客户端
			 * 通过setting来创建，若不指定则默认链接的集群名为elasticsearch
			 * 链接使用tcp协议即9300
			 * 创建集群client并添加集群节点地址
			*/
			String[] hosts = StringUtils.split(IPADDRESS,"|");
			 client = new PreBuiltTransportClient(settings);
			//多节点
			for(String host : hosts){
				 client.addTransportAddress(new InetSocketTransportAddress(
							InetAddress.getByName(host.trim()), 9300));
			}
		} catch (UnknownHostException e) {
			//logger.error("es客户初始化失败！",e);
		}
		
		return client;
    }
   



public static void main(String args[]){

   	 ESUtils.getClient();
}
}
