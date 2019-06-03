package com.ssos.formenginv2.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: FieldRelationVo
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-06-03 10:08
 * @Vsersion: 1.0
 */
@Getter
@Setter
public class FieldRelationVo {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("表名")
    private String tableName;
    @ApiModelProperty("描述")
    private String tableMark;
}
