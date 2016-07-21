package com.wfj.exception.netty.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.netty.client.NettySendErrDispose;
import com.wfj.exception.netty.vo.ClientExceptionReq;
import com.wfj.exception.netty.vo.ServerExceptionResp;
import com.wfj.exception.util.HttpClientUtil;
import com.wfj.exception.util.PropertiesLoad;
import com.wfj.exception.util.StringUtils;
import java.util.List;
import org.apache.log4j.Logger;

public class HttpClientHandler {
	private static final Logger log = Logger.getLogger(HttpClientHandler.class);

	public static void sendJson(ClientExceptionReq clientReq) {
		String sendResult = null;
		log.info("开始发送数据至netty服务器,入参:" + JSONObject.toJSONString(clientReq));
		try {
			sendResult = HttpClientUtil.postJSON(PropertiesLoad.getProperties("IpAddre"), JSONObject.toJSONString(clientReq));
			log.info("发送数据至netty服务器结束,出参:" + sendResult);
			if ((StringUtils.isNotBlank(sendResult)) && (sendResult.equals("false"))) {
				log.info("发送数据至netty服务器出错,开始写入文件...");

				new NettySendErrDispose().writeIn(clientReq);
			} else {
				ServerExceptionResp resp = (ServerExceptionResp) JSONObject.parseObject(sendResult, ServerExceptionResp.class);
				if (resp.isSuccess()) {
					NettySendErrDispose errDis = new NettySendErrDispose();

					List<ClientExceptionReq> reqs = errDis.readErrFiles();
					List<ClientExceptionReq> remoReqs = errDis.readErrFiles();
					for (int i = 0; i < reqs.size(); i++) {
						sendResult = HttpClientUtil.postJSON(PropertiesLoad.getProperties("IpAddre"), JSONObject.toJSONString(reqs.get(i)));
						log.info("发送异常文件中数据至netty服务器结束,出参:" + sendResult);
						if ((StringUtils.isNotBlank(sendResult)) && (sendResult.equals("false"))) {
							log.info("发送异常文件中数据至netty服务器出错...");
						} else {
							resp = (ServerExceptionResp) JSONObject.parseObject(sendResult, ServerExceptionResp.class);
							if (resp.isSuccess()) {
								remoReqs.add(reqs.get(i));
							}
						}
					}
					reqs.removeAll(remoReqs);
					new NettySendErrDispose().writeIn(reqs);
				} else {
					log.info("netty服务器对接收的数据解析出错,开始写入文件...");

					new NettySendErrDispose().writeIn(clientReq);
				}
			}
		} catch (Exception e) {
			log.error("系统异常," + e.getMessage());
			e.printStackTrace();
		}
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.netty.httpclient.HttpClientHandler
 * JD-Core Version:    0.6.0
 */