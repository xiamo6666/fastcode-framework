package com.fc.system.utils;

import cn.hutool.crypto.digest.MD5;
import com.fc.common.model.GlobalConstant;

import java.nio.charset.StandardCharsets;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:46
 */
public class MD5Utils {
    public static final MD5 MD5 = new MD5(GlobalConstant.SALT.getBytes(StandardCharsets.UTF_8), 0, 2);
}
