package com.netty.test.endecode;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * author liuwenqing
 * createTime 2019-03-03 18:14
 */
public class PerforUserInfo implements Serializable {
    private static final long serialVersionUID = -2518560523606259361L;

    private String userName;

    private int userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] codec(ByteBuffer buf){
        buf.clear();
        byte[] nameByte = this.userName.getBytes();
        buf.putInt(nameByte.length);
        buf.put(nameByte);
        buf.putInt(this.userId);
        buf.flip();
        byte[] content = new byte[buf.remaining()];
        buf.get(content);
        return content;
    }

}
