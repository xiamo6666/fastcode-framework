package com.ssos.fastdfs.controller;

import com.ssos.fastdfs.client.FastDfsClient;
import com.ssos.fastdfs.model.FileBasic;
import com.ssos.fastdfs.model.FileImagePath;
import com.ssos.fastdfs.properties.FastdfsConfigProperties;
import com.ssos.fastdfs.utils.FileNameUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: UploadFileController
 * @Description: dto
 * @Author: xwl
 * @Date: 2019/8/30 2:48 下午
 * @Vsersion: 1.0
 */
@RestController
@Api(tags = "文件上传接口")
@RequestMapping("/fdfsFile")
public class UploadFileController {

    @Autowired
    private FastDfsClient fastDfsClient;

    @Autowired
    private FastdfsConfigProperties configProperties;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public String uploadFile(@ApiParam("文件") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        FileBasic extract = FileNameUtils.extract(originalFilename);
        try {
            String url = configProperties.getAccess() + fastDfsClient.uploadFile(file, extract.getType())
                    .get(1, TimeUnit.MINUTES);
            return url;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/uploadImage")
    @ApiOperation("图片上传")
    public FileImagePath uploadImage(@ApiParam("文件") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        FileBasic extract = FileNameUtils.extract(originalFilename);
        try {
            FileImagePath fileImagePath = fastDfsClient
                    .uploadImage(file, extract.getType())
                    .get(1, TimeUnit.MINUTES);
            return fileImagePath;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
