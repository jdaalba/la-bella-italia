package com.jdaalba.converters;

import static java.util.Objects.isNull;

import com.jdaalba.encryption.DataEncrypter;
import org.springframework.data.mongodb.core.convert.MongoConversionContext;
import org.springframework.data.mongodb.core.convert.MongoValueConverter;
import org.springframework.stereotype.Component;

@Component
public class EncryptConverter implements MongoValueConverter<String, String> {

  private static DataEncrypter dataEncrypter;

  public static void setDataEncrypter(DataEncrypter dataEncrypter) {
    assert isNull(EncryptConverter.dataEncrypter);
    EncryptConverter.dataEncrypter = dataEncrypter;
  }

  @Override
  public String read(String value, MongoConversionContext context) {
    return dataEncrypter.decrypt(value);
  }

  @Override
  public String write(String value, MongoConversionContext context) {
    return dataEncrypter.encrypt(value);
  }
}
