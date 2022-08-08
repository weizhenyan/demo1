package com.zy.blog.common.dto;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
public class LongJsonDeserializer extends JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        String value = jsonParser.getText();
        try {
            System.out.println("转换了========================1");
            return  value==null?null:Long.parseLong(value);
        }catch (NumberFormatException e) {
            log.error("解析长整型异常",e);
            return null;
        }
    }

}
