package tech.buildrun.crypto.service;

import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class CryptoService {

    private static final StrongTextEncryptor encryptor;

    static {
        try {
            var privateKey = new DefaultResourceLoader()
                    .getResource("classpath:password.txt")
                    .getContentAsString(StandardCharsets.UTF_8);

            encryptor = new StrongTextEncryptor();
            encryptor.setPassword(privateKey);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String plainText) {
        return encryptor.encrypt(plainText);
    }

    public static String decrypt(String encryptedText) {
        return encryptor.decrypt(encryptedText);
    }

}
