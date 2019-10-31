package com.demo.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class ThreeDESUtil {

    public static String getEncString(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("gb2312"));
        IvParameterSpec ivs = new IvParameterSpec(iv.getBytes("gb2312"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);
        cipher.init(Cipher.ENCRYPT_MODE, securekey, ivs);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(cipher.doFinal(data.getBytes("GB2312")));
    }

    public static String getDesString(String data, String key, String iv) throws Exception {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] databyte = base64Decoder.decodeBuffer(data);
        Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("gb2312"));
        IvParameterSpec ivs = new IvParameterSpec(iv.getBytes("gb2312"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey securekey = keyFactory.generateSecret(dks);
        cipher.init(Cipher.DECRYPT_MODE, securekey, ivs);
        return new String(cipher.doFinal(databyte), "GB2312");
    }
}
