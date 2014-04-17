/**
 * @(#)WebChatTextHandler.java 14-4-16
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
package com.shlaunch.weixin.local.handler;

import com.wl.core.util.DateUtil;

import java.util.Map;

/**
 * @author xuzh
 * @author (14-4-16 modification by xuzh)
 * @version ${Revision} 14-4-16
 * @since 1.0
 */
public class WebChatTextHandler extends AbstractWebChatHandler {

    @Override
    public String process(Map<String, Object> map) {
        String toUserName = map.get(TOUSERNAME).toString();
        String fromUserName = map.get(FROMUSERNAME).toString();
        String content = map.get(CONTENT).toString();
        content += " 什么？你说什么不重要，重要的是——龙创是中国首批成立的独立汽车设计公司之一。历经十余年发展，目前已成长为行业著名、受中国主流整车企业普遍欢迎的整车研发专业机构。";
        String replyText = this.generateReplyTextMessage(toUserName, fromUserName, content);
        return replyText;
    }

    protected String generateReplyTextMessage(String toUserName, String fromUserName, String content) {
        long time = DateUtil.getSystemDate().getTime();
        //StringBuilder xml = new StringBuilder();
        //xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        //xml.append("<xml>");
        //xml.append("<ToUserName><![CDATA[" + toUserName + "]]></ToUserName>");
        //xml.append("<FromUserName><![CDATA[" + fromUserName + "]]></FromUserName>");
        //xml.append("<CreateTime>" + time + "</CreateTime>");
        //xml.append("<MsgType><![CDATA[text]]></MsgType>");
        //xml.append("<Content><![CDATA[" + content + "]]></Content>");
        //xml.append("<FuncFlag>0</FuncFlag>");
        //xml.append("</xml>");

        StringBuilder defaultTpl = new StringBuilder();
        defaultTpl.append("<xml>");
        defaultTpl.append("<ToUserName><![CDATA[%1$s]]></ToUserName>");
        defaultTpl.append("<FromUserName><![CDATA[%2$s]]></FromUserName>");
        defaultTpl.append("<CreateTime>%3$s</CreateTime>");
        defaultTpl.append("<MsgType><![CDATA[%4$s]]></MsgType>");
        defaultTpl.append("<Content><![CDATA[%5$s]]></Content>");
        defaultTpl.append("<FuncFlag>0</FuncFlag>");
        defaultTpl.append("</xml>");

        String xml = String.format(defaultTpl.toString(), fromUserName, toUserName, time + "", "text", content);

        return xml;
    }

    @Override
    public boolean isSupported(String type) {
        return MsgType.equals(type);
    }

    private final String MsgType = WebChatDispatchHandler.TEXT;

}
