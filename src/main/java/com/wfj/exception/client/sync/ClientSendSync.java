package com.wfj.exception.client.sync;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.client.http.httpclient.HttpClientHandler;
import com.wfj.exception.client.http.vo.ClientExceptionReq;
import com.wfj.exception.client.util.StringUtils;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientSendSync {
	private static Logger log = Logger.getLogger(ClientSendSync.class);

	public void clientSendMsg(final ClientExceptionReq req) {
		log.info("开始启动线程...");
		log.info("开始调用HttpClient发送数据的方法,\n参数   :  " + JSONObject.toJSONString(req));
		try {
			checkClientReq(req);
			new Thread(){
				@Override
				public void run() {
					HttpClientHandler.sendJson(req);
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Boolean checkClientReq(ClientExceptionReq req) throws Exception{
		if(StringUtils.isBlank(req.getSysCode())){
			throw new Exception("系统code不能为空!");
		}
		if(StringUtils.isBlank(req.getBusiCode())){
			throw new Exception("业务code不能为空!");
		}
		if(StringUtils.isBlank(req.getErrCode())){
			throw new Exception("用户自定义code不能为空!");
		}
		if(!checkSysCode(req.getSysCode())){
			throw new Exception("系统码与要求不符!");
		}
		if(!checkBusiCode(req.getBusiCode())){
			throw new Exception("业务码与要求不符!");
		}
		if(!checkErrCode(req.getErrCode())){
			throw new Exception("系统自定义码与要求不符!");
		}
		if(!checkSysErrCode(req.getSysErrCode())){
			throw new Exception("系统内部码解析出错!");
		}
		if(StringUtils.isBlank(req.getBusiDesc())){
			throw new Exception("业务描述不能为空!");
		}
		if(StringUtils.isBlank(req.getErrDesc())){
			throw new Exception("系统自定义异常描述不能为空!");
		}
		return true;
	}



	public Boolean checkSysCode(String sysCode) throws Exception {
		Pattern pattern=Pattern.compile("[0-9]{2}");
		Matcher matcher=pattern.matcher(sysCode);
		return matcher.matches();
	}

	public Boolean checkBusiCode(String busiCode) throws Exception{
		Pattern pattern=Pattern.compile("[0-9]{3}");
		Matcher matcher=pattern.matcher(busiCode);
		return matcher.matches();
	}

	public Boolean checkErrCode(String errCode) throws Exception{
		Pattern pattern=Pattern.compile("[0-9]{3}");
		Matcher matcher=pattern.matcher(errCode);
		return matcher.matches();
	}

	public Boolean checkSysErrCode(String sysErrCode) throws Exception{
		Pattern pattern=Pattern.compile("[0-9]{3}");
		Matcher matcher=pattern.matcher(sysErrCode);
		return matcher.matches();
	}

}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.sync.ClientSendSync
 * JD-Core Version:    0.6.0
 */