package com.ssos.formengine.entity;


import lombok.*;

import java.time.LocalDateTime;

/**
 * @ClassName: FieldAssociate
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-10 17:49
 * @Vsersion: 1.0
 */
@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
public class FieldAssociate {
  
  private Long id;
  @NonNull private Long definitionTableId;
  @NonNull private Long fieldId;
  private LocalDateTime createTime;


  

}
