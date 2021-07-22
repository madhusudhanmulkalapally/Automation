package utils;

import org.apache.pdfbox.util.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import static org.apache.commons.codec.binary.Hex.encodeHexString;
import static org.apache.pdfbox.util.Hex.*;

public class CypherEncoder {

    public static final String passphrase = "sdfsdf";
    public static void main(String args[]) {
        System.out.println(new CypherEncoder().decryptToString("dsdfasd"));
    }

    public static String encrypt(byte[] secret, String passphrase) {
        try{
//            Key aesKey = new SecretKeySpec(decodeHex(passphrase.toCharArray()), "AES");
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
//            return org.apache.commons.codec.binary.Hex.encodeHexString(cipher.doFinal(secret));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encrypt(byte[] secret) {
        return encrypt(secret, passphrase);
    }

    public String encrypt(String secret) {
        return encrypt(secret.getBytes());
    }

    public static byte[] decrypt(String cipheredSecretHex) {
        try{
//            Key aesKey = new SecretKeySpec(decodeHex(passphrase.toCharArray()), "AES");
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
//            return cipher.doFinal(decodeHex(cipheredSecretHex.toCharArray()));

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String decryptToString(String cipheredSecretHex) {
        return new String(decrypt(cipheredSecretHex));
    }




}
