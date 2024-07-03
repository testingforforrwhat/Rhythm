package com.test.utils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * 生成一个随机的256位密钥，并以 Base64 编码输出
 *
 */
public class GenerateSecretKey {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 256 bits
        secureRandom.nextBytes(key);
        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println("Generated Secret Key: " + base64Key);
    }
}