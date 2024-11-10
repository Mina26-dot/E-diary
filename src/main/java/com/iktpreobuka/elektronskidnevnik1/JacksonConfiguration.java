package com.iktpreobuka.elektronskidnevnik1;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JacksonConfiguration {

	@Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
                builder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS); 
                builder.featuresToEnable(SerializationFeature.INDENT_OUTPUT); 
                builder.featuresToEnable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED); 
                builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE); 
                builder.featuresToEnable(SerializationFeature.CLOSE_CLOSEABLE); 
                builder.featuresToEnable(SerializationFeature.FLUSH_AFTER_WRITE_VALUE); 
                builder.featuresToEnable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
            }
        };
	
}
}