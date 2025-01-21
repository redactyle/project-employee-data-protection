package com.example.dataprotection.implementationapp.serviceImplementation;

import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.builder.FormatPreservingEncryptionBuilder;
import com.privacylogistics.FF3Cipher;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class ProtectionService {

    private static final int KEY_LENGTH = 256;
    private static final int ITERATION_COUNT = 65536;

    byte[] aesKey256 = new byte[32];

    FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
            .ff1Implementation()
            .withDefaultDomain()
            .withDefaultPseudoRandomFunction(aesKey256)
            .withDefaultLengthRange()
            .build();

    FF3Cipher ff3Cipher = new FF3Cipher("2DE79D232DF5585D68CE47882AE256D6", "CBD09280979564");

    public static String encrypt(String strToEncrypt, String secretKey, String salt) {

        try {

            SecureRandom secureRandom = new SecureRandom();
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);


            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = secretKeyFactory.generateSecret(keySpec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] cipherText  = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
            byte[] encryptedData = new byte[iv.length + cipherText.length];
            System.arraycopy(iv, 0, encryptedData, 0, iv.length);
            System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String strToDecrypt, String secretKey, String salt) {

        try {

            byte[] encryptedData = Base64.getDecoder().decode(strToDecrypt);
            byte[] iv = new byte[16];
            System.arraycopy(encryptedData, 0, iv, 0, iv.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = secretKeyFactory.generateSecret(keySpec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] cipherText = new byte[encryptedData.length - 16];
            System.arraycopy(encryptedData, 16, cipherText, 0, cipherText.length);

            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encryptSalaryFPE(String plainText) throws IllegalBlockSizeException, BadPaddingException {
        /*byte[] tweak = new byte[16];  // 16 bytes = 128 bits
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(tweak);
        return formatPreservingEncryption.encrypt(plainText, tweak);*/

        return ff3Cipher.encrypt(plainText);
    }

    public String decryptSalaryFPE(String cipherText) throws IllegalBlockSizeException, BadPaddingException {

        return ff3Cipher.decrypt(cipherText);
    }
}
