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
     * 加入缓存
     */
    private boolean isCache = false;
}
