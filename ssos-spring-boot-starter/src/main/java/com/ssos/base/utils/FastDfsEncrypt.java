package com.ssos.base.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: FastDfsEncrypt
 * @Description: fastDfs基于token加密访问静态资源
 * @Author: xwl
 * @Date: 2019-07-17 10:40
 * @Vsersion: 1.0
 */
public class FastDfsEncrypt {

    /**
     *
     * @param remote_filename 远程文件名称
     * @param ts  当前时间戳
     * @param secret_key 远程key
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static String getToken(String remote_filename, int ts, String secret_key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bsFilename = remote_filename.getBytes("UTF-8");
        byte[] bsKey = secret_key.getBytes("UTF-8");
        byte[] bsTimestamp = (new Integer(ts)).toString().getBytes("UTF-8");

        byte[] buff = new byte[bsFilename.length + bsKey.length + bsTimestamp.length];
        System.arraycopy(bsFilename, 0, buff, 0, bsFilename.length);
        System.arraycopy(bsKey, 0, buff, bsFilename.length, bsKey.length);
        System.arraycopy(bsTimestamp, 0, buff, bsFilename.length + bsKey.length, bsTimestamp.length);

        return md5(buff);
    }

    public static String md5(byte[] source) throws NoSuchAlgorithmException {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(source);
        byte[] tmp = md.digest();
        char[] str = new char[32];
        int k = 0;
        for (int i = 0; i < 16; i++) {
            str[k++] = hexDigits[tmp[i] >>> 4 & 0xf];
            str[k++] = hexDigits[tmp[i] & 0xf];
        }
        return new String(str);
    }
}
