package com.ssos.formengine.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DataAddDTO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-23 11:28
 * @Vsersion: 1.0
 */
@Data
public class DataAddDTO {
    @ApiModelProperty("主表")
    private DataInfos infos;
    @ApiModelProperty("子表")
    private List<DataInfos> sonInfos;

    @Data
    public static class DataInfos {
        @ApiModelProperty("表名")
        private String name;

        @ApiModelProperty("字段和值")
        private List<Map<String, String>> value;
    }
}
