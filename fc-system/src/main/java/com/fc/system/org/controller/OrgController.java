package com.fc.system.org.controller;

import com.fc.common.model.PageResult;
import com.fc.common.model.Result;
import com.fc.common.model.query.PageDTO;
import com.fc.core.utils.PageResultUtils;
import com.fc.system.auto.entity.Org;
import com.fc.system.org.model.dto.OrgDTO;
import com.fc.system.org.service.OrgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:02
 */
@RestController
@Tag(name = "组织机构类")
@RequestMapping("/org")
public class OrgController {
    @Autowired
    private OrgService orgService;


    @GetMapping("/pageOrg")
    @Operation(summary ="分页组织机构")
    public Result<PageResult<Org>> pageOrg(PageDTO dto) {
        return Result.success(PageResultUtils.convert(orgService.pageOrgList(dto)));
    }

    @PostMapping("/addOrg")
    @Operation(summary ="添加组织机构")
    public Result<String> addOrg(@RequestBody @Valid OrgDTO dto) {
        orgService.addOrg(dto);
        return Result.success();
    }

    @PostMapping("/deleteOrg")
    @Operation(summary ="刪除组织机构")
    public Result<String> deleteOrg(@PathVariable Long orgId) {
        orgService.deleteOrg(orgId);
        return Result.success();

    }


}
