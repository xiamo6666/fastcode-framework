package com.fc.system.dict.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:51
 */
@Data
public class DictIndexDTO {
    @ApiModelProperty("字典索引名称")
    @NotBlank
    @Size(max = 20)
    private String dictIndexKey;
    @Size(max = 20)
    @ApiModelProperty("字典索引值")
    @NotBlank
    private String dictIndexValue;
}
