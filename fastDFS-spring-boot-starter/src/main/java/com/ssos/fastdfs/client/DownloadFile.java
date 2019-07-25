package com.ssos.fastdfs.client;

import com.luhuiguo.fastdfs.service.GenerateStorageClient;
import com.ssos.fastdfs.properties.FastdfsConfigProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @ClassName: DownloadFile
 * @Description: dto
 * @Author: xwl
 * @Date: 2019-07-16 16:41
 * @Vsersion: 1.0
 */
@Component
@Slf4j
@EnableConfigurationProperties(FastdfsConfigProperties.class)
public class DownloadFile {
    @Autowired
    private GenerateStorageClient storageClient;

    @Autowired
    private FastdfsConfigProperties configProperties;


    /**
     * Base64 目前只针对图片
     *
     * @param fullName
     * @return
     */
    public String decryptBase64(String fullName) {
        PicInfo picInfo = getPicInfo(fullName);
        if (!FileCache.isCache(picInfo.getPath())) {
            byte[] bytes = storageClient.downloadFile(picInfo.getGroupName(), picInfo.getPath());
            FileCache.addCache(picInfo.getPath(), bytes);
        }
        byte[] bytes = FileCache.getCache(picInfo.getPath());
        return caseStream(bytes, picInfo.getType());
    }

    /**
     * 获取InputStream
     *
     * @param fullName
     * @return
     */
    public InputStream decryptInputStream(String fullName) {
        PicInfo picInfo = getPicInfo(fullName);
        if (!FileCache.isCache(picInfo.getPath())) {
            byte[] bytes = storageClient.downloadFile(picInfo.getGroupName(), picInfo.getPath());
            log.debug("[{}]文件下载成功", fullName);
            FileCache.addCache(picInfo.getPath(), bytes);
        }
        byte[] bytes = FileCache.getCache(picInfo.getPath());
        return new ByteArrayInputStream(bytes);

    }

    /**
     * 提供url访问连接
     *
     * @param fullName
     * @return
     */
    public String decryptUrl(String fullName) {

        return configProperties.getAccess() + fullName;
    }

    /**
     * 将图片InputStream转化成Base64
     *
     * @param bytes   流
     * @param picType 文件类型
     * @return
     */
    private String caseStream(byte[] bytes, String picType) {
        StringBuilder picBase64 = new StringBuilder();
        picBase64.append("data:image/");
        picBase64.append(picType);
        picBase64.append(";base64,");
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return picBase64.append(base64).toString();
    }

    /**
     * 获取图片地址详情
     *
     * @param fullName
     * @return
     */
    private PicInfo getPicInfo(String fullName) {
        int index = fullName.indexOf("/");
        String groupName = fullName.substring(0, index);
        String path = fullName.substring(index + 1);
        int index2 = fullName.lastIndexOf(".");
        String type = fullName.substring(index2 + 1);
        return PicInfo.builder().groupName(groupName).type(type).path(path).build();
    }

    @Data
    @Builder
    private static class PicInfo {
        /**
         * 所属组别
         */
        private String groupName;
        /**
         * 文件类型
         */
        private String type;
        /**
         * 文件全路径
         */
        private String path;
    }

    /**
     * @ClassName: DownloadFile
     * @Description: 文件缓存
     * @Author: xwl
     * @Date: 2019-07-17 14:37
     * @Vsersion: 1.0
     */
    private static final class FileCache {
        private static Map<String, String> cacheMap = new WeakHashMap<>();
        private static Base64.Decoder decoder = Base64.getDecoder();
        private static Base64.Encoder encoder = Base64.getEncoder();

        private static boolean isCache(String key) {
            return cacheMap.containsKey(key);
        }

        private static void addCache(String key, byte[] bytes) {
            cacheMap.put(key, encoder.encodeToString(bytes));
        }

        private static byte[] getCache(String key) {
            return decoder.decode(cacheMap.get(key));
        }
    }
}
