package com.jdaalba.config;

import com.jdaalba.converters.EncryptConverter;
import com.jdaalba.encryption.DataEncrypter;
import com.jdaalba.encryption.impl.TDesEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
class MappingConfig {

  @Bean
  DataEncrypter dataEncrypter(@Value("${app.encryption.secret}") String key) {
    return new TDesEncrypter(key);
  }

  @Autowired
  @Lazy
  void setDataEncrypter(DataEncrypter dataEncrypter) {
    EncryptConverter.setDataEncrypter(dataEncrypter);
  }
}
