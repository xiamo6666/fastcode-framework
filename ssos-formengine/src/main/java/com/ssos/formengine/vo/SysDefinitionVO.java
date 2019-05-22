package com.ssos.formengine.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName: SysDefinitionVO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-21 14:54
 * @Vsersion: 1.0
 */
@Setter
@Getter
@ToString
public class SysDefinitionVO {
    @ApiModelProperty("字段id")
    private Long id;
    @ApiModelProperty("字段名称")
    private String name;
    @ApiModelProperty("字段标识")
    private String mark;
}
