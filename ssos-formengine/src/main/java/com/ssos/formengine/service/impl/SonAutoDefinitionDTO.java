package com.ssos.formengine.service.impl;

import com.ssos.formengine.dto.AutoDefinitionDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName: SonAutoDefinitionDTO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-20 14:18
 * @Vsersion: 1.0
 */
@Setter
@Getter
@RequiredArgsConstructor(staticName = "ofParentId")
@ToString
@Accessors(chain = true)
public class SonAutoDefinitionDTO {
    @NotNull private Long parentId;
    private List<AutoDefinitionDTO.SonDefinition> sonDefinitions;
}
