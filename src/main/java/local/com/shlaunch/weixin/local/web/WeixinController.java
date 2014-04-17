/**
 * @(#)WeixinController.java 14-4-15
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

import com.shlaunch.weixin.local.handler.WebChatDispatchHandler;
import com.wl.core.util.StringUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author xuzh
 * @author (14-4-15 modification by xuzh)
 * @version ${Revision} 14-4-15
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/weixin")
public class WeixinController extends AbstractController {

    private WebChatDispatchHandler dispatchHandler = new WebChatDispatchHandler();

    @RequestMapping(value = "/service")
    public void send(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {

        echostr = request.getParameter("echostr");
        String responseText = null;

        //验证请求是否来自微信
        if (this.valid(request)) {
            //如果是验证
            if (StringUtil.isNotNull(echostr)) {
                responseText = echostr;
            }
            //如果不是验证，是用户发来的消息
            else {
                responseText = this.process(request);
            }
            logger.debug("responseText:" + responseText);

        } else {
            responseText = "signature is incorrect!";
            logger.debug(responseText);
        }

        response.setContentType("application/xml;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        response.getWriter().print(responseText);
        response.getWriter().flush();
        response.getWriter().close();

    }


    private String process(HttpServletRequest request) {
        Map<String, Object> postObj = null;
        try {
            postObj = this.parseInputStream(request);
        } catch (IOException e) {
            logger.debug(e);
            e.printStackTrace();
        } catch (DocumentException e) {
            logger.debug(e);
            e.printStackTrace();
        }
        String message = "";
        if (null != postObj) {
            message = dispatchHandler.process(postObj);
        } else {
            logger.warn("parse request input stream error! the message will be empty!");
        }

        return message;
    }


    /**
     * 输入流转成字符串
     *
     * @param in
     * @return
     */
    private String readStreamParameter(ServletInputStream in) {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    /**
     * parse输入流,按节点转化为map
     *
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    private Map<String, Object> parseInputStream(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, Object> elementMap = null;
        ServletInputStream inputStream = request.getInputStream();

        if (null != inputStream) {

            String postStr = this.readStreamParameter(inputStream);

            Document document = null;
            try {
                document = DocumentHelper.parseText(postStr);
            } catch (Exception e) {
                logger.debug(e);
                e.printStackTrace();
            }
            if (null != document) {
                elementMap = new HashMap<String, Object>();
                Element rootElm = document.getRootElement();

                for (Iterator<Element> iterator = rootElm.elementIterator(); iterator.hasNext(); ) {
                    Element element = iterator.next();
                    String name = element.getName();
                    Object data = element.getData();
                    elementMap.put(name, data);
                }
            }
        }
        return elementMap;
    }
}


