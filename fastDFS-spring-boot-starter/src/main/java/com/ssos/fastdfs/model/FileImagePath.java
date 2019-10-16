package com.ssos.fastdfs.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName: FileImagePath
 * @Description: dto
 * @Author: xwl
 * @Date: 2019/9/10 12:24 下午
 * @Vsersion: 1.0
 */
@Data
@Builder
public class FileImagePath {
    @ApiModelProperty("缩略图")
    private String thumbnail;
    @ApiModelProperty("原图")
    private String accessUrl;
    @ApiModelProperty("原图路径")
    private String fullName;
}
