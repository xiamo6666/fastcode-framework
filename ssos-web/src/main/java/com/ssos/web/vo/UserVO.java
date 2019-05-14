package com.ssos.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserVO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-04-28 15:31
 * @Vsersion: 1.0
 */
@Data
public class UserVO {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别，0-男，1-女")
    private Integer sex;

    @ApiModelProperty("状态0-正常，1-启用")
    private Integer state;

    @ApiModelProperty("备注")
    private String remake;
    @ApiModelProperty("机构代码")
    private Long agencyCode;
    @ApiModelProperty("机构")
    private String agency;
}
