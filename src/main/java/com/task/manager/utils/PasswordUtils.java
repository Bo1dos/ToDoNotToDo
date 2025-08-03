package com.task.manager.utils;

import java.security.SecureRandom;

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
            byte[] hash = skf.generateSecret(spec).getEncoded();

            if(isEquals(hash, passwordHash.getBytes())){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка хеширования пароля", e);
        }

        return false;
    }
}
