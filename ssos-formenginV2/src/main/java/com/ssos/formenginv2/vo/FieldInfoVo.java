package com.ssos.formenginv2.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: FieldInfoVo
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-06-03 16:56
 * @Vsersion: 1.0
 */
@Data
public class FieldInfoVo {
    @ApiModelProperty("字段id")
    private Long id;
    @ApiModelProperty("字段名称")
    private String fieldName;
    @ApiModelProperty("字段标识")
    private String fieldMark;
    @ApiModelProperty("字段类型id")
    private Integer fieldTypeId;
    @ApiModelProperty("字段默认值")
    private String fieldDefaultValue;
}
