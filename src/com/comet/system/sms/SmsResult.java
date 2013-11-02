package com.comet.system.sms;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-12-2
 * Time: 下午9:38
 * To change this template use File | Settings | File Templates.
 */
public class SmsResult implements Serializable {
    private String State;
    private String MsgID;
    private String MsgState;
    private String Reserve;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        this.State = state;
    }

    public String getMsgID() {
        return MsgID;
    }

    public void setMsgID(String msgID) {
        this.MsgID = msgID;
    }

    public String getMsgState() {
        return MsgState;
    }

    public void setMsgState(String msgState) {
        this.MsgState = msgState;
    }

    public String getReserve() {
        return Reserve;
    }

    public void setReserve(String reserve) {
        this.Reserve = reserve;
    }
}
