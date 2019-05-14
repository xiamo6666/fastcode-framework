package com.ssos.formengine.dto;


import lombok.Data;

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

  private String autoTableName;

  private Set<Long>  fieldIds;
}
