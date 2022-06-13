package com.fc.utils.uuid;

import java.util.UUID;

/**
 * @ClassName: UuidUtils
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/5/27 17:34
 * @Vsersion: 1.0
 */
public class UuidUtils {
    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String shortUUID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static void main(String[] args) {
        System.out.println(shortUUID());
    }
}
