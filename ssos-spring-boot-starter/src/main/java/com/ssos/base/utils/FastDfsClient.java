package com.ssos.base.utils;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author xia
 *
 * FastDFS工具类
 */
@Component
@Lazy
public class FastDfsClient {

    @Autowired
    private FastFileStorageClient client;

    /**
     * 异步上传文件
     *
     * @param bytes 文件字节数组
     * @param fileType 文件类型
     * @return Future对象
     */
    @Async
    public Future<StorePath> uploadFile(byte[] bytes, String fileType) {
        return new AsyncResult<>(client.uploadFile(bytes, fileType));
    }

}

