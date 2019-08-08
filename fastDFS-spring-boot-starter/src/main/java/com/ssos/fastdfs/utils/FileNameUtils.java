package com.ssos.fastdfs.utils;

import com.ssos.fastdfs.model.FileBasic;
import org.springframework.util.Assert;

/**
 * @ClassName: FileNameUtils
 * @Description: dto
 * @Author: xwl
 * @Date: 2019-08-07 18:50
 * @Vsersion: 1.0
 */
public final class FileNameUtils {
    /**
     * 原始文件名称提取
     *
     * @param originalFilename
     * @return
     */
    public static final FileBasic extract(String originalFilename) {
        Assert.notNull(originalFilename,"参数不能为空");
        String type = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        return FileBasic.builder().name(name).type(type).build();
    }
}
