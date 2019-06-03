package com.ssos.formenginv2.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName: FormFieldAddDto
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-06-03 11:35
 * @Vsersion: 1.0
 */
@Setter
@Getter
public class FormFieldAddDto {
    @ApiModelProperty("表单id")
    public Long formId;
    @ApiModelProperty("字段id集合")
    public List<Long> fieldId;
}
