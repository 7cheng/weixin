/**
 * @(#)AddSHA1.java 14-4-15
 *
 * Copyright (c) 2012-2014 www.52weixue.com
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 52weixue.com.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with 52weixue.com.
 *
 * Distributable under GNU LGPL license by gnu.org
 */


import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xuzh
 * @author (14-4-15 modification by xuzh)
 * @version ${Revision} 14-4-15
 * @since 1.0
 */
public class AddSHA1 {
    public static String SHA1(String inStr) {
        MessageDigest md = null;
        String outStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");     //选择SHA-1，也可以选择MD5
            byte[] digest = md.digest(inStr.getBytes());       //返回的是byet[]，要转化为String存储比较方便
            outStr = bytetoString(digest);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return outStr;
    }


    public static String bytetoString(byte[] digest) {
        String str = "";
        String tempStr = "";

        for (int i = 1; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            } else {
                str = str + tempStr;
            }
        }
        return str.toLowerCase();
    }

    public static void main(String args[]) {
        //1d5e6de6782a0406b4f49690d2849cf3c8365156
        //System.out.println(DigestUtils.shaHex("1397553651725919662weixin"));
        //signature:1d5e6de6782a0406b4f49690d2849cf3c8365156,timestamp:1397612997,nonce:57092655,echostr:3405473746965751253
        byte[] digest = DigestUtils.sha("1397553651725919662weixin");
        String outStr = bytetoString(digest);
        System.out.println(outStr);
        System.out.println(DigestUtils.shaHex("1397553651725919662weixin"));
        System.out.println(SHA1("1397553651725919662weixin"));
    }
}
