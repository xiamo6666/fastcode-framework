package com.ssos.formengine.entity;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: FieldAssociate
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-10 17:49
 * @Vsersion: 1.0
 */
@Data
public class FieldAssociate {
  
  private Long id;
  private Long definitionTableId;
  private Long fieldId;
  private LocalDateTime createTime;


  

}
