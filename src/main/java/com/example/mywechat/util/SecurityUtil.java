package com.example.mywechat.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import lombok.NonNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    private static final Digester md5Digester = new Digester(DigestAlgorithm.MD5);

    public static String sha1(String str) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest digest = MessageDigest.getInstance("sha1");
            // 放入加密字符串
            digest.update(str.getBytes());
            // 进行加密
            byte[] digestMsg = digest.digest();
            // byte转换16进制
            for (byte b : digestMsg) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 以用户名作为salt对密码进行加密
     *
     * @param username 用户名
     * @param password 密码
     * @return 加密后的密码
     */
    public static String encryptionPassword(@NonNull String username, @NonNull String password) {
        md5Digester.setSalt(username.getBytes(StandardCharsets.UTF_8));
        return md5Digester.digestHex(password);
    }
}
