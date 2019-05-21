package com.ssos.formengine.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: SonAutoDefinitionDTO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-20 14:18
 * @Vsersion: 1.0
 */
@Data
public class SonAutoDefinitionDTO {
    private Long parentId;
    private List<AutoDefinitionDTO.SonDefinition> sonDefinitions;
}
