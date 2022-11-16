package com.jdaalba.encryption.impl;

import com.jdaalba.encryption.DataEncrypter;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

public class TDesEncrypter implements DataEncrypter {

  public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

  private final Cipher cipher;

  private final SecretKey key;

  public TDesEncrypter( String secretKey) {
    try {
      byte[] arrayBytes = secretKey.getBytes(StandardCharsets.UTF_8);
      KeySpec ks = new DESedeKeySpec(arrayBytes);
      SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
      cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
      key = skf.generateSecret(ks);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public String encrypt(String unencryptedString) {
    String encryptedString = null;
    try {
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] plainText = unencryptedString.getBytes(StandardCharsets.UTF_8);
      byte[] encryptedText = cipher.doFinal(plainText);
      encryptedString = new String(Base64.encodeBase64(encryptedText));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return encryptedString;
  }

  @Override
  public String decrypt(String encryptedString) {
    String decryptedText = null;
    try {
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] encryptedText = Base64.decodeBase64(encryptedString);
      byte[] plainText = cipher.doFinal(encryptedText);
      decryptedText = new String(plainText);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return decryptedText;
  }
}
