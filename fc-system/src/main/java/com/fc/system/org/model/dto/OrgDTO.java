package com.fc.system.org.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:03
 */
@Data
public class OrgDTO {
    @Schema(description ="上级机构code")
    @NotNull
    @Length(max = 20, message = "上级机构code超出长度限制")
    private String parentOrgCode;

    @Schema(description ="机构code")
    @NotBlank
    @Length(max = 5, message = "机构code超出长度限制")
    private String orgCode;

    @Schema(description ="机构名称")
    @NotBlank
    @Length(max = 200, message = "机构名称超出长度限制")
    private String orgName;

    public String getOrgCode() {
        return parentOrgCode + orgCode;
    }
}
