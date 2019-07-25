package com.ssos.fastdfs.utils;

import com.ssos.exception.BaseException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

/**
 * @ClassName: FastDfsEncrypt
 * @Description: fastDfs基于token加密访问静态资源
 * fastDfs加密方式
 * token=9ee6673d7cc5d8141a135c11bc3ec383&
 * ts=1563873509
 * @Author: xwl
 * @Date: 2019-07-17 10:40
 * @Vsersion: 1.0
 */
public class FastDfsEncrypt {

    /**
     * 根据文件名称加密得到token
     *
     * @param remote_filename
     * @param secret_key
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private static String getToken(String remote_filename, String secret_key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bsFilename = remote_filename.getBytes("UTF-8");
        byte[] bsKey = secret_key.getBytes("UTF-8");
        byte[] bsTimestamp = (new Integer(getInstant())).toString().getBytes("UTF-8");

        byte[] buff = new byte[bsFilename.length + bsKey.length + bsTimestamp.length];
        System.arraycopy(bsFilename, 0, buff, 0, bsFilename.length);
        System.arraycopy(bsKey, 0, buff, bsFilename.length, bsKey.length);
        System.arraycopy(bsTimestamp, 0, buff, bsFilename.length + bsKey.length, bsTimestamp.length);

        return md5(buff);
    }

    /**
     * md5加密
     *
     * @param source
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String md5(byte[] source) throws NoSuchAlgorithmException {
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

    /**
     * 获取 1970-01-01T00:00:00Z 到现在的时间戳
     *
     * @return
     */
    private static int getInstant() {
        return (int) Instant.now().getEpochSecond();
    }

    /**
     *
     * @param picName
     * @return
     */
    public static String getPicNncryptSuffix(String picName) {
        String remoteName = picName.substring(7);
        try {
            String token = FastDfsEncrypt.getToken(remoteName, "FastDFS1234567890");
            return "?token=" + token + "&ts=" + getInstant();
        } catch (Exception e) {
            throw new BaseException("获取静态资源失败");
        }
    }
}
