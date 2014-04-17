
/*
* Copyright (c) 2013. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
* Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
* Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
* Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
* Vestibulum commodo. Ut rhoncus gravida arcu.
*/

package com.shlaunch.weixin.core.web.jackson.map;


import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xuzh
 * @author (13-8-22 modification by xuzh)
 * @version ${Revision} 13-8-22
 * @description 解决Date类型返回json格式为自定义格式
 * @since 1.0
 */

public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        CustomSerializerFactory factory = new CustomSerializerFactory();
        factory.addGenericMapping(Date.class, new JsonSerializer<Date>() {
            @Override
            public void serialize(Date value,
                                  JsonGenerator jsonGenerator,
                                  SerializerProvider provider)
                    throws IOException, JsonProcessingException {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jsonGenerator.writeString(simpleDateFormat.format(value));
            }
        });
        this.setSerializerFactory(factory);
    }
}
