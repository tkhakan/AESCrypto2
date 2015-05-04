package com.example.hakan.aesdeneme2;

/**
 * Created by hakan on 10.04.2015.
 */
import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.*;
import javax.crypto.spec.*;

public class Main {

    public SecretKeySpec skeySpec;
    public   byte[] sksbyte;
    public String keystring;
    private Cipher cipher;

    public Main(byte[] keyraw) throws Exception {
        if (keyraw == null) {
            byte[] bytesOfMessage = "".getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(bytesOfMessage);

            skeySpec = new SecretKeySpec(bytes, "AES");
            sksbyte=skeySpec.getEncoded();
            keystring=Base64.encodeToString(sksbyte, Base64.DEFAULT);
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } else {

            skeySpec = new SecretKeySpec(keyraw, "AES");
            sksbyte=skeySpec.getEncoded();
            keystring=Base64.encodeToString(sksbyte, Base64.DEFAULT);
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        }
    }

    public Main(String passphrase) throws Exception {
        byte[] bytesOfMessage = passphrase.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        skeySpec = new SecretKeySpec(thedigest, "AES");

        sksbyte=skeySpec.getEncoded();
        keystring=Base64.encodeToString(sksbyte, Base64.DEFAULT);
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    }

    public Main() throws Exception {
        byte[] bytesOfMessage = "".getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        skeySpec = new SecretKeySpec(thedigest, "AES");

        skeySpec = new SecretKeySpec(new byte[16], "AES");
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        sksbyte=skeySpec.getEncoded();
        keystring=Base64.encodeToString(sksbyte, Base64.DEFAULT);
    }

   

    public byte[] encrypt(byte[] plaintext) throws Exception {
        //returns byte array encrypted with key

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] ciphertext = cipher.doFinal(plaintext);

        return ciphertext;
    }

    public byte[] decrypt(byte[] ciphertext) throws Exception {
        //returns byte array decrypted with key
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] plaintext = cipher.doFinal(ciphertext);

       return plaintext;

    }
}