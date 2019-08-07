package com.ssos.fastdfs.model;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName: FileBasic
 * @Description: dto
 * @Author: xwl
 * @Date: 2019-08-07 18:53
 * @Vsersion: 1.0
 */
@Data
@Builder
public class FileBasic {
    private String name;
    private String type;
}
