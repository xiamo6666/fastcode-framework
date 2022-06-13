package com.ssos.formengine.controller;

import com.ssos.base.model.DataResult;
import com.ssos.formengine.dto.DataAddDTO;
import com.ssos.formengine.service.AutoDefinitionService;
import com.ssos.formengine.vo.FormAllFieldVO;
import com.ssos.formengine.vo.FormAllShowVO;
import com.ssos.formengine.vo.FormOneShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: DefinitionTableController
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-22 16:43
 * @Vsersion: 1.0
 */
@RestController
@RequestMapping("/definitionTable")
@Api(tags = "动态生成的表操作")
public class DefinitionTableController {
    @Autowired
    private AutoDefinitionService autoDefinitionService;

    @Operation("根据标识查找数据列表")
    @GetMapping("/showTable")
    public DataResult<FormAllShowVO> showTable(@RequestParam String mark) {
        return DataResult.ok(autoDefinitionService.showtable(mark));
    }

    @Operation("查看数据详情")
    @GetMapping("/showOneTable")
    public DataResult<FormOneShowVO> showOneTable(@RequestParam String tableName, @RequestParam Long id) {
        return DataResult.ok(autoDefinitionService.showOnetable(tableName, id));
    }

    @Operation("通过标识加载动态表")
    @GetMapping("/load")
    public DataResult<FormAllFieldVO> load(@RequestParam String mark) {
        return DataResult.ok(autoDefinitionService.loadField(mark));
    }

    @Operation(value = "添加数据",notes = "{\n" +
            "  \"infos\": {\n" +
            "    \"name\": \"这是主表\",\n" +
            "    \"value\": [\n" +
            "      {\n" +
            "        \"key1\": \"value1\", //分别是字段，和值\n" +
            "        \"key2\": \"value1\",\n" +
            "        \"key3\": \"value1\",\n" +
            "        \"key4\": \"value1\",\n" +
            "        \"key5\": \"value1\",\n" +
            "        \"key6\": \"value1\"\n" +
            "      }                  //一行数据的情况下\n" +
            "    ]\n" +
            "  },\n" +
            "  \"sonInfos\": [\n" +
            "    {\n" +
            "      \"name\": \"string\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "        \"key1\": \"value1\", //分别是字段，和值\n" +
            "        \"key2\": \"value1\",\n" +
            "        \"key3\": \"value1\",\n" +
            "        \"key4\": \"value1\",\n" +
            "        \"key5\": \"value1\",\n" +
            "        \"key6\": \"value1\"\n" +
            "        },{\n" +
            "        \"key1\": \"value1\", //分别是字段，和值\n" +
            "        \"key2\": \"value1\",\n" +
            "        \"key3\": \"value1\",\n" +
            "        \"key4\": \"value1\",\n" +
            "        \"key5\": \"value1\",\n" +
            "        \"key6\": \"value1\"\n" +
            "        }                 //多行数据\n" +
            "      ]\n" +
            "    },   {\n" +
            "      \"name\": \"string\",\n" +
            "      \"value\": [\n" +
            "        {\n" +
            "        \"key1\": \"value1\", //分别是字段，和值\n" +
            "        \"key2\": \"value1\",\n" +
            "        \"key3\": \"value1\",\n" +
            "        \"key4\": \"value1\",\n" +
            "        \"key5\": \"value1\",\n" +
            "        \"key6\": \"value1\"\n" +
            "        },{\n" +
            "        \"key1\": \"value1\", //分别是字段，和值\n" +
            "        \"key2\": \"value1\",\n" +
            "        \"key3\": \"value1\",\n" +
            "        \"key4\": \"value1\",\n" +
            "        \"key5\": \"value1\",\n" +
            "        \"key6\": \"value1\"\n" +
            "        }                 \n" +
            "      ]\n" +
            "    }                    //多张子表\n" +
            "  ]\n" +
            "}")
    @PostMapping("/add")
    public DataResult addData(@RequestBody DataAddDTO dto) {
        autoDefinitionService.addData(dto);
        return null;
    }
}
