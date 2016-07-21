package com.wfj.exception.sync;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.netty.httpclient.HttpClientHandler;
import com.wfj.exception.netty.vo.ClientExceptionReq;

public class ClientSendSync {
	private static Logger log = Logger.getLogger(ClientSendSync.class);

	public void clientSendMsg(final ClientExceptionReq req) {
		log.info("开始启动线程...");
		new Thread(){
			public void run() {
				try {
					log.info("开始调用HttpClient发送数据的方法,\n参数   :  " + JSONObject.toJSONString(req));

					HttpClientHandler.sendJson(req);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.sync.ClientSendSync
 * JD-Core Version:    0.6.0
 */