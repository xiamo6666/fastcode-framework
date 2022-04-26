package com.fc.core.config;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @ClassName: BaseConfig
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/4/25 11:23
 * @Vsersion: 1.0
 */
@Configuration
public class BaseConfig {
    @Autowired
    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;


    /**
     * 在json序列化的时候，由于前端没有long类型，会导致long的精度丢失
     * long----》string
     *
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter MappingJson2HttpMessageConverter() {
        ObjectMapper mapper = jackson2ObjectMapperBuilder.build();
        mapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter(DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR));
        // 序列换成json时,将所有的long变成string,因为js中得数字类型不能包含所有的java long值
        SimpleModule simpleModule = new SimpleModule("LongModule");
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        mapper.registerModule(simpleModule);
        return new MappingJackson2HttpMessageConverter(mapper);
    }


}
