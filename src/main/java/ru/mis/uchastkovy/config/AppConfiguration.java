package ru.mis.uchastkovy.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AppConfiguration {

//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource
//                = new ReloadableResourceBundleMessageSource();
//
//        messageSource.setBasename("classpath:messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }


//    @Bean
//    @Primary
//    public ObjectMapper serializeObjectMapper() {
////        JavaTimeModule module = new JavaTimeModule();
////        module.addSerializer(LOCAL_DATETIME_SERIALIZER);
//        return new ObjectMapper()
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
////                .registerModule(module);
//    }



//    public String convertObjToJson(Object obj) throws JsonProcessingException {
//        return new ObjectMapper().writeValueAsString(obj);
//    }
//
//    public Object convertJsonToObj(String jsonBody, Class clazz) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            return objectMapper.readValue(jsonBody, clazz);
//        } catch (IOException e) {
//            return null;
//        }
//    }


}
