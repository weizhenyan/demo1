package com.zy.blog.common.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class LongJsonSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String text = (value==null?null:String.valueOf(value));
        if(text!=null) {
            System.out.println("转换了========================2");
            jsonGenerator.writeString(text);
        }
    }
}
