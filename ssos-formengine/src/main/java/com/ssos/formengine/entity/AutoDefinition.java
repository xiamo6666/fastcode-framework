package com.ssos.formengine.entity;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: AutoDefinition
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-10 17:59
 * @Vsersion: 1.0
 */
@Data
public class AutoDefinition implements Serializable {

  private Long id;
  private String autoTableName;
  private String mark;
  private Long parentId;
  private LocalDateTime createTime;
}
