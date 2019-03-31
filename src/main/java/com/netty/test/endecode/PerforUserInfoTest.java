package com.netty.test.endecode;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * author liuwenqing
 * createTime 2019-03-03 18:25
 * 测试传统的二进制编码 和 java自带的序列化码流大小
 */
public class PerforUserInfoTest {

    public static void main(String[] args) throws Exception{

       PerforUserInfo userInfo = new PerforUserInfo();
       userInfo.setUserName("刘文清");
       userInfo.setUserId(28);

       int loop = 10000;
       ByteArrayOutputStream baos = null;
       ObjectOutputStream oos = null;
       long jdkStartMillis = System.currentTimeMillis();
       for (int index = 0; index < loop; index ++){
          baos = new ByteArrayOutputStream();
          oos = new ObjectOutputStream(baos);
          oos.writeObject(userInfo);
          oos.flush();
          oos.close();
          byte[] bytes = baos.toByteArray();
          baos.close();
       }
       long jdkEndMillis = System.currentTimeMillis();
       System.out.println("the jdk serializable cost time is : " + (jdkEndMillis - jdkStartMillis));

       ByteBuffer buffer = ByteBuffer.allocate(1024);
       jdkStartMillis = System.currentTimeMillis();
       for (int index = 0;index < loop; index ++){
          userInfo.codec(buffer);
       }
       jdkEndMillis = System.currentTimeMillis();
       System.out.println("the byte array serializable cost time is : " + (jdkEndMillis - jdkStartMillis));

    }

}
