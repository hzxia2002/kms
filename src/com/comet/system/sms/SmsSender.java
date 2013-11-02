package com.comet.system.sms;

import com.comet.core.utils.SpringUtils;
import com.project.entity.request.SubmitReqHttpEntity;
import com.project.entity.response.SubmitRespEntity;
import com.project.http.HttpSmsSendClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-12-2
 * Time: 下午8:38
 * To change this template use File | Settings | File Templates.
 */
public class SmsSender {
    private static Log log = LogFactory.getLog(SmsSender.class);

    private String url;
    private String username;
    private String password;
    private String sprdid;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSprdid() {
        return sprdid;
    }

    public void setSprdid(String sprdid) {
        this.sprdid = sprdid;
    }

    public static SmsSender getInstance() {
        SmsSender sender = (SmsSender) SpringUtils.getBean("smsSender");

        if(sender != null) {
            return sender;
        }

        return null;
    }

    public SmsResult send(String msg, String reciever) throws Exception {
        SmsResult smsResult = new SmsResult();
//        HttpClient client = new HttpClient();
//        PostMethod method = new PostMethod(url);
//
//        client.getParams().setContentCharset("UTF-8");
//
//        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
//
//        NameValuePair[] data = {
//                new NameValuePair("sname", username),
//                new NameValuePair("spwd", password),
//                new NameValuePair("sprdid", sprdid),
//                new NameValuePair("scorpid", ""),
//                new NameValuePair("sdst", reciever),
//                new NameValuePair("smsg", msg),
//        };
//
//        method.setRequestBody(data);
//
//        try {
//            client.executeMethod(method);
//
//            String returnMsg = method.getResponseBodyAsString();
//
//            System.out.println(returnMsg);
//
////            if(StringUtils.isNotEmpty(returnMsg)) {
////                smsResult = XmlUtils.parseResult(returnMsg);
////            }
//        } catch (Exception e) {
//            smsResult = new SmsResult();
//            smsResult.setMsgState("-1");
//            smsResult.setReserve("发送失败，" + ExceptionUtils.getRootCauseMessage(e));
//
//            log.error("error", e);
//            throw e;
//        }

        // 创建接口发送对象
        HttpSmsSendClient sendClient = new HttpSmsSendClient();
        // 设置请求URL
        sendClient.setHttpPath(getUrl());

        // 创建请求对象
        SubmitReqHttpEntity submitReqEntity = new SubmitReqHttpEntity();
        // 设置客户端id 必录
        submitReqEntity.setCid(getUsername());
        // 设置客户端密码 必录
        submitReqEntity.setPwd(getPassword());

        // 发送内容 必录
        submitReqEntity.setContent(msg);

        // 手机号码 必录
        submitReqEntity.setMobile(reciever);

        // messageFormat 默认设置 15 必录
        submitReqEntity.setFormat((byte) 15);

        // 客户端唯一流水号码 必录 用于匹配状态报告
        submitReqEntity.setSsid(System.currentTimeMillis());
        // 扩展字段
        submitReqEntity.setCustom("");

        // 长号码 非必填,默认填写""
        submitReqEntity.setLcode("");
        // 签名非必填,默认填写""
        submitReqEntity.setSign("");

        //发送短信，需进行速度控制，如1秒钟执行提交30条
        SubmitRespEntity submitRespEntity = sendClient.sendSMS(submitReqEntity);

        smsResult.setState(String.valueOf(submitRespEntity.getStatus()));

        return smsResult;
    }
}
