package test;

import com.wfj.exception.handler.EcMapperHandler;
import com.wfj.exception.handler.MyExceptionHandler;
import com.wfj.exception.util.PropertiesLoad;
import com.wfj.exception.util.SystemBootstrap;
import java.io.PrintStream;
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

	public static void main(String[] args) {
		Map<String,String> bussMap = new HashMap<String,String>();
		Map<String,String> errMap = new HashMap<String,String>();
		bussMap.put("001", "订单拆分");
		bussMap.put("002", "订单支付");
		errMap.put("001", "商品信息不全");
		errMap.put("002", "物流地址不符合要求");
		EcMapperHandler.getInstanceEcMap().init(bussMap, errMap);
		SystemBootstrap.afterPropertiesSet();
		PropertiesLoad.putProperties("IpAddre", "http://127.0.0.1:8089/");
		PropertiesLoad.putProperties("sysCode", "03");
		System.out.println(PropertiesLoad.getProperties("IpAddre"));
		try {
			c();
		} catch (Exception e) {
			MyExceptionHandler myE = new MyExceptionHandler();
			myE.putException("001",  "002", e);
		}
		
		try {
			c();
		} catch (Exception e) {
			MyExceptionHandler myE = new MyExceptionHandler();
			myE.putException("001",  "002", e);
		}
		
		try {
			try {
				String j = null;
				String k=new String(j);
				
			} catch (Exception e) {
				throw new MyExceptionHandler("002",   "001", e);
			}
		} catch (MyExceptionHandler e) {
			System.out.println(e.getMessage());
		}
		
		try {
			try {
				String j = null;
				String k=new String(j);
				
			} catch (Exception e) {
				throw new MyExceptionHandler("002",   "001", e);
			}
		} catch (MyExceptionHandler e) {
			System.out.println(e.getMessage());
		}
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     test.Test
 * JD-Core Version:    0.6.0
 */