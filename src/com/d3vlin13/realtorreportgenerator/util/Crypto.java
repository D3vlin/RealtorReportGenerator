package com.d3vlin13.realtorreportgenerator.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import javax.crypto.spec.GCMParameterSpec;

public final class Crypto {
	
	private static final SecretKey SECRET_KEY = generateSecretKey();
	
	private static SecretKey generateSecretKey() {
        try {
            // Usar una instancia específica de SecureRandom con la semilla
            String seed = "RealtorReportGenerator";

            // Usar KeyGenerator para generar una clave AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256, new SecureRandom(seed.getBytes()));

            // Generar y devolver la clave
            return keyGenerator.generateKey();
            
        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción, puedes imprimir un mensaje de error o lanzar una RuntimeException
            e.printStackTrace();
            throw new RuntimeException("Error al generar la clave AES", e);
        }
    }
	
	public static String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding/");
        cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);

        byte[] iv = cipher.getIV();
        byte[] cipherText = cipher.doFinal(plainText.getBytes());

        // Combina el IV y el texto cifrado
        byte[] combined = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(combined);
    }
	
	public static String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // Decodifica la cadena base64
        byte[] combined = Base64.getDecoder().decode(encryptedText);

        // Extrae el IV
        byte[] iv = new byte[12];
        System.arraycopy(combined, 0, iv, 0, 12);

        // Extrae el texto cifrado
        byte[] cipherText = new byte[combined.length - 12];
        System.arraycopy(combined, 12, cipherText, 0, cipherText.length);

        cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY, new GCMParameterSpec(128, iv));
        byte[] plainText = cipher.doFinal(cipherText);

        return new String(plainText);
    }
}
