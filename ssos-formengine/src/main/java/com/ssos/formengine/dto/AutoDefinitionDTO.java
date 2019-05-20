package com.ssos.formengine.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: AutoDefinition
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-10 17:59
 * @Vsersion: 1.0
 */
@Data
public class AutoDefinitionDTO {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("具体字段id")
    private Set<Long> fieldIds;

    private List<SonDefinition> sonDefinitions;


    @Data
    public static class SonDefinition {
        @ApiModelProperty("名称")
        private String name;
        @ApiModelProperty("具体字段id")
        private Set<Long> fieldIds;
    }
}
