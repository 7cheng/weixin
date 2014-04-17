/**
 * @(#)WebChatDispatchHandler.java 14-4-16
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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author xuzh
 * @author (14-4-16 modification by xuzh)
 * @version ${Revision} 14-4-16
 * @since 1.0
 */
public class WebChatDispatchHandler extends AbstractWebChatHandler {

    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String LOCATION = "location";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";
    public static final String LINK = "link";
    public static final String EVENT = "event";
    public static final String DEFAULT = "default";

    private List<WebChatHandler> handlers = new LinkedList<WebChatHandler>();

    private void init() {
        if (handlers == null || handlers.size() == 0) {
            handlers.add(new WebChatTextHandler());
            handlers.add(new WebChatImageHandler());
        }
    }

    public WebChatDispatchHandler() {
        init();
    }

    @Override
    public String process(Map<String, Object> map) {

        String type = map.get(WebChatHandler.MSGTYPE).toString();
        String message = null;
        for (WebChatHandler handler : handlers) {

            if (handler.isSupported(type)) {
                logger.debug(handler.getClass().getName() + " is processing,the message type is : " + type);
                message = handler.process(map);
                break;
            }

        }
        if (null == message) {

            WebChatHandler defaultHandler = new DefaultWebChatHandler();
            logger.debug("haven't found any handlers,the default handler(" + defaultHandler.getClass().getName() + ") is processing ");
            message = defaultHandler.process(map);

        }

        return message;
    }

    @Override
    public boolean isSupported(String type) {
        return false;
    }
}
