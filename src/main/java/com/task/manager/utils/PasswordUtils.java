package com.task.manager.utils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {
    private static final int SALT_LENGTH = 16; // байт
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256; // бит


    /**
     * Генерирует случайную соль
     * @return {@code byte[]} - соль, размером в {@link PasswordUtils#SALT_LENGTH}
     */
    public static byte[] generateSalt() {
        byte [] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    /**
     * Хеширует пароль с указанной солью
     * @param password
     * @param salt
     * @return {@code byte[]} - хешированный пароль
     */
    public static byte[] hash(char[] password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // немедленно очистим PBEKeySpec
            spec.clearPassword();

            return hash;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка хеширования пароля", e);
        }

    }

    /**
     * Метод сравнения двух массивов байт (для сравнения паролей)
     * @param a
     * @param b
     * @return
     */
    public static boolean isEquals(byte[] a, byte[] b){
        if(a.length != b.length) {return false;}

        for(int i = 0; i < a.length; i++){
            if(a[i] != b[i]){
                return false;
            } 
        }

        return true;
    }


    public static boolean verify(char [] rawPassword, String passwordHash, String salt) {

        try {

            PBEKeySpec spec = new PBEKeySpec(rawPassword, salt.getBytes(), ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] generatedHash = skf.generateSecret(spec).getEncoded();


            // Декодирование оригинального хеша из Base64
            byte[] originalHash = Base64.getDecoder().decode(passwordHash);
        
             // Преобразование в читаемый формат для отладки
        String generatedBase64 = Base64.getEncoder().encodeToString(generatedHash);
        String generatedHex = bytesToHex(generatedHash);
        String originalHex = bytesToHex(originalHash);
        
        // Подробный вывод для отладки
        System.out.println("=== DEBUG: Password Verification ===");
        System.out.println("Salt: " + salt);
        System.out.println("Generated hash (Base64): " + generatedBase64);
        System.out.println("Stored hash (Base64):    " + passwordHash);
        System.out.println("Generated hash (HEX):    " + generatedHex);
        System.out.println("Stored hash (HEX):       " + originalHex);
        System.out.println("Length comparison: " + generatedHash.length + " vs " + originalHash.length);
        System.out.println("Arrays equal: " + Arrays.equals(generatedHash, originalHash));
        System.out.println("===================================");

            // Сравнение массивов байт
            return Arrays.equals(generatedHash, originalHash);

        } catch (Exception e) {
            throw new RuntimeException("Ошибка проверки пароля", e);
        }
    }

    // Вспомогательный метод для преобразования байтов в HEX-строку
private static String bytesToHex(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
        sb.append(String.format("%02x", b));
    }
    return sb.toString();
}

}

