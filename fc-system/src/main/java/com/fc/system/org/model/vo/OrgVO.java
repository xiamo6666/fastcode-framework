package com.fc.system.org.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/22 9:31
 */
@Data
public class OrgVO {
    @Schema(description = "机构code")
    private String orgCode;

    @Schema(description = "机构名称")
    private String orgName;

    @Schema(description ="上级机构code")
    private String parentOrgCode;

    @Schema(description = "子机构集合")
    private List<OrgVO> children;
}
