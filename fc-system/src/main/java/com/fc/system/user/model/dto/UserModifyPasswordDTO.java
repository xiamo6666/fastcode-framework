package com.fc.system.user.model.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:40
 */
@Data
@ScriptAssert.List(
        @ScriptAssert(lang = "javascript", script = "_this.newPassword.equals(_this.confirmPassword)", message = "两次输入的密码不一致", reportOn = "newPassword")
)
public class UserModifyPasswordDTO {
    @Schema(description = "原始密码")
    @Size(min = 6, max = 20, message = "密码长度应该在6-20之间")
    @NotBlank
    private String originalPassword;
    @Schema(description = "新密码")
    @NotBlank
    @Size(min = 6, max = 20, message = "密码长度应该在6-20之间")
    private String newPassword;
    @Schema(description = "确认新密码")
    @NotBlank
    @Size(min = 6, max = 20, message = "密码长度应该在6-20之间")
    private String confirmPassword;
}
