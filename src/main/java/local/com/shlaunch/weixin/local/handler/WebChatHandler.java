/**
 * @(#)WebChatHandler.java 14-4-16
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

import java.util.Map;

/**
 * @author xuzh
 * @author (14-4-16 modification by xuzh)
 * @version ${Revision} 14-4-16
 * @since 1.0
 */
public interface WebChatHandler {
    public static final String TOUSERNAME = "ToUserName";
    public static final String FROMUSERNAME = "FromUserName";
    public static final String CREATETIME = "CreateTime";
    public static final String MSGTYPE = "MsgType";
    public static final String CONTENT = "Content";
    public static final String FUNCFLAG = "FuncFlag";

    public static final String PICURL = "PicUrl";//图片地址
    public static final String MEDIAID = "MediaId";//多媒体
    public static final String LOCATION_X = "Location_X";//x
    public static final String LOCATION_Y = "Location_Y";//y
    public static final String SCALE = "Scale";//缩放级别
    public static final String LABEL = "Label";//地址
    public static final String TITLE = "Title";//标题
    public static final String DESCRIPTION = "Description";//内容
    public static final String URL = "Url";//链接地址


    public abstract String process(Map<String, Object> map);

    public abstract boolean isSupported(String type);
}
