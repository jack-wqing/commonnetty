package com.netty.test.endecode;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * author liuwenqing
 * createTime 2019-03-03 18:25
 * 测试传统的二进制编码 和 java自带的序列化码流大小
 */
public class UserInfoTest {

    public static void main(String[] args) throws Exception{

       UserInfo userInfo = new UserInfo();
       userInfo.setUserName("刘文清");
       userInfo.setUserId(28);

       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       ObjectOutputStream oos = new ObjectOutputStream(baos);
       oos.writeObject(userInfo);
       oos.flush();
       oos.close();
       byte[] bytes = baos.toByteArray();
       System.out.println("jdk serializable lenth is : " + bytes.length);
       baos.close();
       System.out.println("the byte array serializable length is : " + userInfo.codec().length);

    }

}
