/**
 * @(#)AbstractController.java 14-4-15
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
package com.shlaunch.weixin.local.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author xuzh
 * @author (14-4-15 modification by xuzh)
 * @version ${Revision} 14-4-15
 * @since 1.0
 */
public abstract class AbstractController {

    protected final Log logger = LogFactory.getLog(getClass());

    private static String TOKEN = "weixin";

    // 微信加密签名
    protected String signature = null;
    // 时间戳
    protected String timestamp = null;
    // 随机数
    protected String nonce = null;
    // 随机字符串
    protected String echostr = null;

    protected boolean valid(HttpServletRequest request) {
        // 微信加密签名
        signature = request.getParameter("signature");
        // 时间戳
        timestamp = request.getParameter("timestamp");
        // 随机数
        nonce = request.getParameter("nonce");
        // 随机字符串
        echostr = request.getParameter("echostr");

        logger.debug("signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr);

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (null == signature || null == timestamp || null == nonce) {
            return false;
        }

        return this.checkSignature(signature, timestamp, nonce);
    }

    protected boolean checkSignature(String signature, String timestamp, String nonce) {
        String token = TOKEN;
        String[] str = {token, timestamp, nonce};
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        String sha = DigestUtils.shaHex(bigStr);
        logger.debug("bigStr:" + bigStr + ",sha:" + sha);
        if (signature.equals(sha)) {
            return true;
        } else {
            return false;
        }
    }
}
