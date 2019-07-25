package com.ssos.fastdfs.utils;

import com.ssos.exception.BaseException;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 图片处理类
 */
public class PictureUtils {

    /**
     * 生成缩略图比例
     */
    private static final double THUMBNAIL_PROPORTION = 0.25;

    /**
     * 根据缺陷图片生成缩略图
     *
     * @return 缩略图输入流
     */
    public static byte[] generatorThumbnailPicture(byte[] bytes) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream(512 * 1024)) {
            Thumbnails.of(inputStream).scale(THUMBNAIL_PROPORTION).toOutputStream(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new BaseException("生成缩略图失败、请检查图片是否符合规范");
        }
    }
}