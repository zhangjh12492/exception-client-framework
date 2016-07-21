package com.wfj.exception;


import com.wfj.exception.client.handler.EcMapperHandler;
import com.wfj.exception.client.handler.MyExceptionHandler;
import com.wfj.exception.client.util.HttpClientUtilPost;
import com.wfj.exception.client.util.PropertiesLoad;
import com.wfj.exception.client.util.SystemBootstrap;

import java.util.HashMap;
import java.util.Map;

public class Test {
//	private static String getMessages(Throwable e) {
//		StringBuffer sb = new StringBuffer(e.getClass().getName() + ":" + e.getMessage() + "\n");
//		StackTraceElement[] stacks = e.getStackTrace();
//		for (int j = 0; j < stacks.length; j++) {
//			sb.append(stacks[j].toString() + "\n");
//		}
//		return sb.toString();
//	}

	private static void c() {
		int i = 1 / 0;
		int j = 1 / 0;
	}

	public static void main(String [] args){
		addtMes("10");
	}

	public static void addtMes(String sysCode) {
		System.out.println("123456".substring(2,4));

		Map<String,String> bussMap = new HashMap<String,String>();
		Map<String,String> errMap = new HashMap<String,String>();
		bussMap.put("011", "ceshi_订单拆分");
		bussMap.put("002", "ceshi_订单支付");
		bussMap.put("003", "ceshi_订单库存");
		errMap.put("001", "ceshi_商品信息不全");
		errMap.put("002", "ceshi_物流地址不符合要求");
		errMap.put("003", "ceshi_库存有误");
		EcMapperHandler.getInstanceEcMap().init(bussMap, errMap);
		SystemBootstrap.afterPropertiesSet();
//		PropertiesLoad.putProperties("IpAddre", "http://127.0.0.1:8089/");
//		PropertiesLoad.putProperties("mq_server", "http://172.16.3.202:8088/hbase/dataProcess/josnInboundFromMq.do");
//		PropertiesLoad.putProperties("mq_server", "http://10.6.2.59:8080/MQInput/itgService/inbound.do");
//		PropertiesLoad.putProperties("mq_server", "http://192.168.6.244:8090/MQInput/itgService/inbound.do");
//		PropertiesLoad.putProperties("mq_server", "http://172.16.3.202:6090/ExceptionServer/dataProcess/josnInboundFromMq.do");
		PropertiesLoad.putProperties("mq_dest_url", "http://10.6.2.59:6080/ExceptionServer/dataProcess/josnInboundFromMq.do");
//		PropertiesLoad.putProperties("mq_server", "http://192.168.6.140:6080/ExceptionServer/dataProcess/josnInboundFromMq.do");
//		PropertiesLoad.putProperties("mq_dest_url", "http://192.168.6.140:6080/ExceptionServer/dataProcess/josnInboundFromMq.do");
//		PropertiesLoad.putProperties("mq_dest_url", "http://172.16.3.202:6080/hbase/mqResbondData/josnInbound.do");

		PropertiesLoad.putProperties("client.exception.sys_code", sysCode);
//		System.out.println(PropertiesLoad.getProperties("IpAddre"));
		try {
			c();
		} catch (Exception e) {
			MyExceptionHandler myE = new MyExceptionHandler();
			myE.putException("011",  "001", e);
		}
//		try {
//			c();
//		} catch (Exception e) {
//			MyExceptionHandler myE = new MyExceptionHandler();
//			myE.putException("002",  "001", e);
//		}
//		try {
//			c();
//		} catch (Exception e) {
//			MyExceptionHandler myE = new MyExceptionHandler();
//			myE.putException("003",  "003", e);
//		}
//
//		try {
//			c();
//		} catch (Exception e) {
//			MyExceptionHandler myE = new MyExceptionHandler();
//			myE.putException("003",  "003", e);
//		}
//
//		try {
//			String [] a=new String[]{"aa","bb"};
//			System.out.println(a[3]);
//		} catch (Exception e) {
//			MyExceptionHandler myE = new MyExceptionHandler();
//			myE.putException("003",  "001", e);
//		}
//		try {
//			try {
//				String j = null;
//				String k=new String(j);
//				
//			} catch (Exception e) {
//				throw new MyExceptionHandler("002",   "001", e);
//			}
//		} catch (MyExceptionHandler e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			try {
//				String j = null;
//				String k=new String(j);
//
//			} catch (Exception e) {
//				throw new MyExceptionHandler("003",   "002", e);
//			}
//		} catch (MyExceptionHandler e) {
//			System.out.println(e.getMessage());
//		}

//		t
	}
	@org.junit.Test
	public void testJosnInboundFromMq(){
		String str = "{'busiCode':'100','busiDesc':'公共','createDate':'2015-10-16 11:47:13','errCode':'007','errDesc':'账号信息存在异常','errId':'101110000720020151016114713119','errLevel':'1','flag':'0','processStatus':'0','sysCode':'11','sysErrCode':'200','throwableDesc':'com.wangfj.core.framework.exception.BleException:账号信息存在异常'}";
//		Map map =JSONObject.fromObject(str);
		try {
			String result= HttpClientUtilPost.postBody("http://localhost:6090/ExceptionServer/dataProcess/josnInboundFromMq.do", str);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
