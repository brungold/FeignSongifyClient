package com.feignsongifyclient;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .client(new OkHttpClient())
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL);
    }
}