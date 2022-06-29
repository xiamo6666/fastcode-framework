package com.fc.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/27 14:21
 */
@Configuration
@Slf4j
public class RedisTemplateConfig {


    @Bean(name = "objectRedisTemplate")
    public RedisTemplate<String, Object> receiveModelRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> receiveModelRedisTemplate = new RedisTemplate<String, Object>();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        om.registerModule(simpleModule);

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        receiveModelRedisTemplate.setKeySerializer(new StringRedisSerializer());
        receiveModelRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        receiveModelRedisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        receiveModelRedisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        receiveModelRedisTemplate.setConnectionFactory(redisConnectionFactory);
        receiveModelRedisTemplate.afterPropertiesSet();
        return receiveModelRedisTemplate;
    }


}
