package com.fc.system.dict.controller;

import com.fc.common.model.PageResult;
import com.fc.common.model.Result;
import com.fc.common.model.query.PageDTO;
import com.fc.core.utils.PageResultUtils;
import com.fc.system.auto.entity.DictIndex;
import com.fc.system.auto.entity.DictInfo;
import com.fc.system.dict.model.dto.DictIndexDTO;
import com.fc.system.dict.model.dto.DictInfoDTO;
import com.fc.system.dict.service.DictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:48
 */
@RestController
@RequestMapping("/dict")
@Tag(name = "字典操作")
@Validated
public class DictController {

    @Autowired
    private DictService dictService;

    @PostMapping("/addDictIndex")
    @Operation(summary = "添加字典索引")
    public Result<String> addDictIndex(@Validated @RequestBody DictIndexDTO dictIndexDto) {
        dictService.addDictIndex(dictIndexDto);
        return Result.success();
    }


    @GetMapping("/pageDictIndexList")
    @Operation(summary = "分页查询字典索引")
    public Result<PageResult<DictIndex>> pageDictIndexList(PageDTO pageDto) {
        return Result.success(PageResultUtils.convert(dictService.pageDictIndexList(pageDto)));
    }

    @PostMapping("/addDictInfo")
    @Operation(summary = "添加字典值")
    public Result<String> addDictInfo(@Validated @RequestBody DictInfoDTO dictInfoDto) {
        dictService.addDictInfo(dictInfoDto);
        return Result.success();
    }

    @PostMapping("/getDictInfoList")
    @Operation(summary = "获取查询字典值列表")
    public Result<List<DictInfo>> getDictInfoList(@NotEmpty @Size(min = 1) @RequestBody List<String> dictIndexKeys) {
        return Result.success(dictService.getDictInfoList(dictIndexKeys));
    }

    @GetMapping("/getDictInfoByDictInfoKey")
    @Operation(summary = "获取查询字典值")
    public Result<String> getDictInfoByDictInfoKey(String dictIndexKey, String dictKey) {
        return Result.success(dictService.getDictInfoByDictInfoKey(dictIndexKey, dictKey));
    }

    @PostMapping("/deleteDictInfo/{dictInfoId}")
    @Operation(summary = "删除字典值")
    public Result<String> deleteDictInfo(@PathVariable Long dictInfoId) {
        dictService.deleteDictInfo(dictInfoId);
        return Result.success();
    }

    @PostMapping("/deleteDictIndex/{dictIndexId}")
    @Operation(summary = "删除字典索引")
    public Result<String> deleteDictIndex(@PathVariable Long dictIndexId) {
        dictService.deleteDictIndex(dictIndexId);
        return Result.success();
    }


}
