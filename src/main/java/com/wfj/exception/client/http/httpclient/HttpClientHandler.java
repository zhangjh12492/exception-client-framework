package com.wfj.exception.client.http.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.wfj.exception.client.common.SysConstants;
import com.wfj.exception.client.http.client.SendErrDispose;
import com.wfj.exception.client.http.vo.ClientExceptionReq;
import com.wfj.exception.client.util.*;
import org.apache.log4j.Logger;

import java.util.List;

public class HttpClientHandler {
    private static final Logger log = Logger.getLogger(HttpClientHandler.class);

    public static void sendJson(ClientExceptionReq clientReq) {
        String mqUrl = PropertiesLoad.getProperties("mq_server");
//        System.out.println("mq_server:"+mqUrl);
        String sendStr = JSONObject.toJSONString(createMessageDto(clientReq));
        String sendResult = null;
        log.info("sendData:" + sendStr);
        log.info("开始发送数据至MQ服务器,入参:" + sendStr);
        try {
            sendResult = HttpClientUtilPost.postBody(mqUrl, sendStr , SysConstants.CONN_TIMEOUT_MS);
            log.info("发送数据至MQ服务器结束,出参:" + sendResult);
            if (StringUtils.isBlank(sendResult) || (sendResult.equals("false"))) {
                log.info("发送数据至MQ服务器出错,开始写入文件...");

                new SendErrDispose().writeIn(clientReq);
            } else {
                MqResboundDto resp = (MqResboundDto) JSONObject.parseObject(sendResult, MqResboundDto.class);
                if (resp.getRespStatus().equals("1")) {
                    SendErrDispose errDis = new SendErrDispose();

                    List<ClientExceptionReq> reqs = errDis.readErrFiles();
                    List<ClientExceptionReq> remoReqs = errDis.readErrFiles();
                    if(reqs!=null&&reqs.size()>0){
                        for (int i = 0; i < reqs.size(); i++) {
                            sendResult =  HttpClientUtilPost.postBody(mqUrl, JSONObject.toJSONString(createMessageDto(reqs.get(i))),SysConstants.CONN_TIMEOUT_MS);
                            log.info("发送异常文件中数据至netty服务器结束,出参:" + sendResult);
                            if ((StringUtils.isNotBlank(sendResult)) && (sendResult.equals("false"))) {
                                log.info("发送异常文件中数据至netty服务器出错...");
                            } else {
                                resp = (MqResboundDto) JSONObject.parseObject(sendResult, MqResboundDto.class);
                                if (resp.getRespStatus().equals("1")) {
                                    remoReqs.add(reqs.get(i));
                                }
                            }
                        }
                        reqs.removeAll(remoReqs);
                        new SendErrDispose().writeIn(reqs);
                    }

                } else {
                    log.info("netty服务器对接收的数据解析出错,开始写入文件...");

                    new SendErrDispose().writeIn(clientReq);
                }
            }
        } catch (Exception e) {
            log.error("系统异常," + e.getMessage());
            e.printStackTrace();
        }
    }

    public static MessageDto createMessageDto(ClientExceptionReq clientReq) {
        MessageDto messageDto = new MessageDto();
        ItgMsgHeaderDto header = new ItgMsgHeaderDto();
        header.setBizType("17");
        header.setCallbackUrl("xx");
        header.setCount("0");
        header.setCreateTime(DateUtils.getCurrentDate());
//		header.setDestUrl("http://192.168.6.140:8088/");
        header.setDestUrl(PropertiesLoad.getProperties("mq_dest_url"));
        header.setPriority("2");
        header.setRouteKey("WCS_1");
        header.setServiceID("P000_01");
        header.setSourceSysID("P000");
        header.setToken("123");
        header.setVersion("0");
        header.setDestCallType(0);
        messageDto.setData(clientReq);
        messageDto.setHeader(header);
        return messageDto;

    }
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.netty.httpclient.HttpClientHandler
 * JD-Core Version:    0.6.0
 */