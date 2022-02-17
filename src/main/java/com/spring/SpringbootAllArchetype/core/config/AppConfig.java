package com.spring.SpringbootAllArchetype.core.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;

import java.nio.charset.Charset;

@Configuration
public class AppConfig {
    // Thymeleaf dialect(layout) Dependency Injection
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    /*
     * Json UTF-8 converter (한글 나오지 않는 현상 해소)
     * request Serialize 된 데이터 처리
     * CBORFactory required 문제 발생. Constructor 로 받는 cborfactory 를 인식하지 못하거나 버전 문제 등이 있는 것으로 보임. properties로 빼놓음
     * */
    /*
    @Bean
    public MappingJackson2CborHttpMessageConverter mappingJackson2CborHttpMessageConverter() {
        MappingJackson2CborHttpMessageConverter converter = new MappingJackson2CborHttpMessageConverter();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        converter.setObjectMapper(objectMapper);
        converter.setDefaultCharset(Charset.forName("UTF-8"));

        return converter;
    }
    */
}
