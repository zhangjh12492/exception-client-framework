package com.wfj.exception.client.handler;

import com.wfj.exception.client.common.ErrStatusEnum;
import com.wfj.exception.client.common.ErrorLevel;
import com.wfj.exception.client.common.MessageFlagEnum;
import com.wfj.exception.client.common.SysConstants;
import com.wfj.exception.client.http.vo.ClientExceptionReq;
import com.wfj.exception.client.sync.ClientSendSync;
import com.wfj.exception.client.util.DateUtils;
import com.wfj.exception.client.util.PropertiesLoad;
import com.wfj.exception.client.util.StringUtils;
import org.apache.log4j.Logger;

import javax.xml.crypto.NoSuchMechanismException;
import javax.xml.ws.WebServiceException;
import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class MyExceptionHandler extends Throwable {

    private static final Logger log=Logger.getLogger(MyExceptionHandler.class);
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String detailMessage;
    private String stackTraceMessage;
    private String sysErrCode;

    public MyExceptionHandler() {
    }

    public MyExceptionHandler(String busiCode, String errCode, Throwable e) {
        try {
            ClientExceptionReq req = createExceptionPro(busiCode, errCode, e, ErrorLevel.ERROR.getCode());
            new ClientSendSync().clientSendMsg(req);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public MyExceptionHandler(String busiCode, String busiDesc, String errCode, String errDesc, Throwable e) {
        try {
            ClientExceptionReq req = createExceptionPro(busiCode, busiDesc, errCode, errDesc, e, ErrorLevel.ERROR.getCode());
            new ClientSendSync().clientSendMsg(req);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void putException(String busiCode, String errCode, Throwable e) {
        try {
            ClientExceptionReq req = createExceptionPro(busiCode, errCode, e, ErrorLevel.WARING.getCode());
            new ClientSendSync().clientSendMsg(req);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void putException(String busiCode, String busiDesc, String errCode, String errDesc, Throwable e) {
        try {
            ClientExceptionReq req = createExceptionPro(busiCode, busiDesc, errCode, errDesc, e, ErrorLevel.WARING.getCode());
            new ClientSendSync().clientSendMsg(req);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public MyExceptionHandler(String busiCode, String errCode, Throwable e , String errLever) {
        try {

            ClientExceptionReq req = createExceptionPro(busiCode, errCode, e, StringUtils.isBlank(errLever)?ErrorLevel.ERROR.getCode():errLever);
            new ClientSendSync().clientSendMsg(req);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public MyExceptionHandler(String busiCode, String busiDesc, String errCode, String errDesc, Throwable e, String errLever) {
        try {
            ClientExceptionReq req = createExceptionPro(busiCode, busiDesc, errCode, errDesc, e, StringUtils.isBlank(errLever)?ErrorLevel.ERROR.getCode():errLever);
            new ClientSendSync().clientSendMsg(req);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void putException(String busiCode, String errCode, Throwable e, String errLever) {
        try {
            ClientExceptionReq req = createExceptionPro(busiCode, errCode, e, StringUtils.isBlank(errLever)?ErrorLevel.WARING.getCode():errLever);
            new ClientSendSync().clientSendMsg(req);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void putException(String busiCode, String busiDesc, String errCode, String errDesc, Throwable e, String errLever) {
        try {
            ClientExceptionReq req = createExceptionPro(busiCode, busiDesc, errCode, errDesc, e, StringUtils.isBlank(errLever)?ErrorLevel.WARING.getCode():errLever);
            new ClientSendSync().clientSendMsg(req);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    private ClientExceptionReq createExceptionPro(String busiCode, String errCode, Throwable e, String errLevel) {
        ClientExceptionReq exceReq = getExceptionObj(e);
        exceReq.setBusiCode(busiCode);
        exceReq.setBusiDesc(EcMapperHandler.getInstanceEcMap().getBusiDesc(busiCode));
        exceReq.setErrCode(errCode);
        exceReq.setErrDesc(EcMapperHandler.getInstanceEcMap().getErrDesc(errCode));
        exceReq.setErrLevel(errLevel);
        exceReq.setFlag(MessageFlagEnum.VALID.getCode());
        exceReq.setProcessStatus(ErrStatusEnum.UNDISPOSED.getCode());
        this.detailMessage = exceReq.getSysErrDesc();
        this.sysErrCode = exceReq.getSysErrCode();
        this.stackTraceMessage = exceReq.getThrowableDesc();
        exceReq.setErrId(errLevel + exceReq.getFlag() + exceReq.getSysCode() + exceReq.getBusiCode() + errCode + sysErrCode + SysConstants.ERRID);
        return exceReq;
    }

    private ClientExceptionReq createExceptionPro(String busiCode, String busiDesc, String errCode, String errDesc, Throwable e, String errLevel) {
        ClientExceptionReq exceReq = getExceptionObj(e);
        exceReq.setBusiCode(busiCode);
        exceReq.setBusiDesc(busiDesc);
        exceReq.setErrCode(errCode);
        exceReq.setErrDesc(errDesc);
        exceReq.setErrLevel(errLevel);
        exceReq.setFlag(MessageFlagEnum.VALID.getCode());
        exceReq.setProcessStatus(ErrStatusEnum.UNDISPOSED.getCode());
        this.sysErrCode = exceReq.getSysErrCode();
        this.detailMessage = exceReq.getSysErrDesc();
        this.stackTraceMessage = exceReq.getThrowableDesc();
        exceReq.setErrId(errLevel + exceReq.getFlag() + exceReq.getSysCode() + exceReq.getBusiCode() + errCode + sysErrCode + SysConstants.ERRID);
        return exceReq;
    }

    private static ClientExceptionReq getExceptionObj(Throwable e) {
        ClientExceptionReq req = new ClientExceptionReq();
        if ((e instanceof ArithmeticException)) {
            req.setSysErrCode("001");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof ArrayStoreException)) {
            req.setSysErrCode("002");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof ClassCastException)) {
            req.setSysErrCode("003");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof EmptyStackException)) {
            req.setSysErrCode("004");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof SecurityException)) {
            req.setSysErrCode("005");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof IllegalArgumentException)) {
            req.setSysErrCode("006");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof WebServiceException)) {
            req.setSysErrCode("007");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof NullPointerException)) {
            req.setSysErrCode("008");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof NoSuchMechanismException)) {
            req.setSysErrCode("009");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof NoSuchElementException)) {
            req.setSysErrCode("010");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof ImagingOpException)) {
            req.setSysErrCode("011");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof UnsupportedOperationException)) {
            req.setSysErrCode("012");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof IndexOutOfBoundsException)) {
            req.setSysErrCode("013");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof RuntimeException)) {
            req.setSysErrCode("200");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof IOException)) {
            req.setSysErrCode("210");
            req.setThrowableDesc(getMessages(e));
        } else if ((e instanceof Exception)) {
            req.setSysErrCode("220");
            req.setThrowableDesc(getMessages(e));
        } else {
            req.setSysErrCode("230");
            req.setThrowableDesc(getMessages(e));
        }
        SysConstants.ERRID += 1;

        req.setSysCode(PropertiesLoad.getProperties("client.exception.sys_code"));
        req.setCreateDate(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        req.setSysErrDesc(PropertiesLoad.getProperties(req.getSysErrCode()));
        return req;
    }

    private static String getMessages(Throwable e) {
        StringBuffer sb = new StringBuffer(e.getClass().getName() + ":" + e.getMessage() + "\n");
        StackTraceElement[] stacks = e.getStackTrace();
        for (int j = 0; j < stacks.length; j++) {
            sb.append(stacks[j].toString() + "\n");
        }
        return sb.toString();
    }

    public String getMessage() {
        return this.detailMessage;
    }

    public String getStackTraceMessage() {
        return this.stackTraceMessage;
    }

    public String getSysErrCode() {
        return this.sysErrCode;
    }
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.handler.MyExceptionHandler
 * JD-Core Version:    0.6.0
 */