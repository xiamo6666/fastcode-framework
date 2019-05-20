package com.ssos.formengine.entity;


import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName: AutoDefinition
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-10 17:59
 * @Vsersion: 1.0
 */
@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class AutoDefinition implements Serializable {
  private Long id;
  @NonNull  private String autoTableName;
  @NonNull  private String name;
  private String mark;
  private Long parentId;
  private LocalDateTime createTime;
}
