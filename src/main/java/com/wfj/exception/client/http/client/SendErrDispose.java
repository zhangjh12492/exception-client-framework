package com.wfj.exception.client.http.client;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.client.common.SysConstants;
import com.wfj.exception.client.file.MessageErrFile;
import com.wfj.exception.client.http.vo.ClientExceptionReq;
import com.wfj.exception.client.util.DateUtils;
import com.wfj.exception.client.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendErrDispose {
	public void writeIn(ClientExceptionReq clientReq) {
		String file = SysConstants.ERRMSGFILEPATH + "error" + DateUtils.format(new Date(), "yyyyMMdd") + "." + "log";
		MessageErrFile.createdFile(file);
		if (clientReq != null) {
			String str = JSONObject.toJSONString(clientReq) + "---" + "\n";
			MessageErrFile.writeFile(file, str, true);
		}
	}

	public void writeIn(List<ClientExceptionReq> clientReqs) {
		if ((clientReqs != null) && (clientReqs.size() > 0))
			for (int i = 0; i < clientReqs.size(); i++)
				writeIn((ClientExceptionReq) clientReqs.get(i));
	}

	public List<ClientExceptionReq> readErrFiles() {
		List reqs = new ArrayList();
		String file = SysConstants.ERRMSGFILEPATH;
		MessageErrFile.createdFolder(file);
		List files = MessageErrFile.readAllFiles(file);
		if ((files != null) && (files.size() > 0)) {
			for (int i = 0; i < files.size(); i++) {
				String msg = MessageErrFile.loadFileByChar(file + ((File) files.get(i)).getName());
				MessageErrFile.delFile(file + ((File) files.get(i)).getName());
				String[] msgs = msg.split("---");
				ClientExceptionReq req = null;
				for (int j = 0; j < msgs.length; j++) {
					if (StringUtils.isNotBlank(msgs[j])) {
						req = (ClientExceptionReq) JSONObject.parseObject(msgs[j], ClientExceptionReq.class);
						reqs.add(req);
					}
				}
			}
		}
		return reqs;
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.netty.client.NettySendErrDispose
 * JD-Core Version:    0.6.0
 */