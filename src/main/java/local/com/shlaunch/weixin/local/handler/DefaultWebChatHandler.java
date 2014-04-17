/**
 * @(#)DefaultWebChatHandler.java 14-4-16
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
public class DefaultWebChatHandler extends AbstractWebChatHandler {
    @Override
    public String process(Map<String, Object> map) {
        return "";
    }

    @Override
    public boolean isSupported(String type) {
        return true;
    }
}
