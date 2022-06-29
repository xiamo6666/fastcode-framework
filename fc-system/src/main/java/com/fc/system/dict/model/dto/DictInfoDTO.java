package com.fc.system.dict.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:51
 */
@Data
public class DictInfoDTO {
    @Schema(description = "字典索引名称")
    @NotBlank
    @Size(max = 20)
    private String dictIndexKey;

    @Schema(description = "字典键")
    @NotBlank
    @Size(max = 20)
    private String dictKey;

    @Schema(description = "字典值")
    @NotBlank
    @Size(max = 500)
    private String dictValue;

}
