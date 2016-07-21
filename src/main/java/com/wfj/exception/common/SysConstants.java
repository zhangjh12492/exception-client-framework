package com.wfj.exception.common;

import com.wfj.exception.util.DateUtils;

public class SysConstants {
	public static final Integer CONN_TIMEOUT_SECOND = Integer.valueOf(10);

	public static final Integer CONN_TIMEOUT_MS = Integer.valueOf(10000);
	public static final int PORT = 8080;
	public static final String NETTYURL = "IpAddre";
	public static final String SYSCODE = "sysCode";
	public static long ERRID = Long.valueOf(DateUtils.getDateYMDHMSMIS()).longValue();

	public static String ERRMSGFILEPATH = "F:\\errMsg\\";
	public static final String ERRMSGFILENAME = "error";
	public static final String ERRMSGFILETYPE = "log";
	public static final String LOGSPLITSYMBOL = "---";
	public static final String ARITHMETIC = "001";
	public static final String ARRAYSTORE = "002";
	public static final String CLASSCAST = "003";
	public static final String EMPTYSTACK = "004";
	public static final String SECURITY = "005";
	public static final String ILLEGALARGUMENT = "006";
	public static final String WEBSERVICE = "007";
	public static final String NULLPOINTER = "008";
	public static final String NOSUCHMECHANISM = "009";
	public static final String NOSUCHELEMENT = "010";
	public static final String IMAGINGOP = "011";
	public static final String UNSUPPORTEDOPERATION = "012";
	public static final String INDEXOUTOFBOUNDS = "013";
	public static final String RUNTIME = "200";
	public static final String IOException = "210";
	public static final String EXCEPTION = "220";
	public static final String OTHEREXCEPTION = "230";
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.common.SysConstants
 * JD-Core Version:    0.6.0
 */