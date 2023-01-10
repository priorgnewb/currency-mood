package org.faze.currencymood.config;

import feign.Logger;
import feign.Logger.Level;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

  @Bean
  Logger.Level feignLoggerLevel() {
    return Level.BASIC;
  }
}