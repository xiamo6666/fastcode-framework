package com.ssos.fastdfs.client;

import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ssos.fastdfs.model.FileImagePath;
import com.ssos.fastdfs.properties.FastdfsConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * @author xia
 * <p>
 * FastDFS工具类
 */
@Component
@Lazy
public class FastDfsClient {

    @Autowired
    private FastFileStorageClient client;

    @Autowired
    private FastdfsConfigProperties configProperties;

    /**
     * 异步上传文件
     *
     * @param file     文件流
     * @param fileType 文件类型
     * @return Future对象
     */
    @Async
    public Future<String> uploadFile(MultipartFile file, String fileType) throws Exception {
        return new AsyncResult<>(client.uploadFile(file.getInputStream(),
                file.getSize(), fileType, null).getFullPath());
    }

    @Async
    public Future<FileImagePath> uploadImage(MultipartFile file, String fileType) throws IOException {
        FastImageFile build = new FastImageFile.Builder()
                .withFile(file.getInputStream(), file.getSize(),
                        fileType).withThumbImage().build();
        String fullPath = client.uploadImage(build).getFullPath();
        String access = configProperties.getAccess();
        FileImagePath fileImagePath = FileImagePath.builder().fullName(fullPath)
                .accessUrl(access + fullPath)
                .thumbnail(access + build.getThumbImagePath(fullPath))
                .build();
        return new AsyncResult<>(fileImagePath);
    }

}

