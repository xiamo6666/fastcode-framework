package com.ssos.fastdfs.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: FastdfsConfigProperties
 * @Description: dto
 * @Author: xwl
 * @Date: 2019-07-24 11:24
 * @Vsersion: 1.0
 */
@Data
@ConfigurationProperties(prefix = "fdfs.client")
public class FastdfsConfigProperties {
    /**
     * 访问地址
     */
    private String access = "http://t.uavebit.com";
    /**
     * 文件上传超时时间
     */
    private Integer fileUploadTimeout = 5;
}
