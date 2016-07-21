package com.wfj.exception.client.handler;

import com.wfj.exception.client.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class EcMapperHandler {
	private Map<String, String> busiParams;
	private Map<String, String> errParams;
	private static EcMapperHandler ecmap = new EcMapperHandler();

	private EcMapperHandler() {
		if (!isNotEmptyMap(this.busiParams)) {
			this.busiParams = new HashMap<String, String>();
		}
		if (!isNotEmptyMap(this.errParams))
			this.errParams = new HashMap<String, String>();
	}

	public static EcMapperHandler getInstanceEcMap() {
		if (ecmap == null) {
			ecmap = new EcMapperHandler();
		}
		return ecmap;
	}

	public void init(Map<String, String> busiParams, Map<String, String> errParams) {
		for (Entry<String,String> entry : busiParams.entrySet()) {
			if (StringUtils.isNotBlank((CharSequence) busiParams.get(entry.getKey()))) {
				this.busiParams.put(entry.getKey(), entry.getValue());
			}
		}
		for (Entry<String,String> entry : errParams.entrySet())
			if (StringUtils.isNotBlank((CharSequence) errParams.get(entry.getKey())))
				this.errParams.put(entry.getKey(), entry.getValue());
	}

	public void destory() {
		this.busiParams = null;
		this.errParams = null;
	}

	public void putBusiCode(String key, String value) {
		this.busiParams.put(key, value);
	}

	public void putErrCode(String key, String value) {
		this.errParams.put(key, value);
	}

	public void removeBusiCode(String key) {
		try {
			this.busiParams.remove(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeErrCode(String key) {
		try {
			this.errParams.remove(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBusiDesc(String key) {
		return this.busiParams.get(key);
	}

	public String getErrDesc(String key) {
		return  this.errParams.get(key);
	}

	private boolean isNotEmptyMap(Map<?, ?> map) {
		return (map != null) && (map.size() > 0);
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.handler.EcMapperHandler
 * JD-Core Version:    0.6.0
 */