package com.ssos.formengine.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: Field
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-10 17:24
 * @Vsersion: 1.0
 */
@Data
public class FieldVO {
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
    @ApiModelProperty("字段长度最小值")
    private Integer fieldMin;
    @ApiModelProperty("字段张长度最大值")
    private Integer fieldMax;
    @ApiModelProperty("该字段是否用作查询")
    private Integer isQuery;
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
