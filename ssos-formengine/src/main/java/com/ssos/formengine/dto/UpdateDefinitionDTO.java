package com.ssos.formengine.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * @ClassName: UpdateDefinitionDTO
 * @Description: 修改定义（原则不是修改，而是在原来的基础上进行添加操作）
 * @Author: xwl
 * @Date: 2019-05-20 16:07
 * @Vsersion: 1.0
 */
@Data
public class UpdateDefinitionDTO {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("具体字段id")
    private Set<Long> fieldIds;
}
