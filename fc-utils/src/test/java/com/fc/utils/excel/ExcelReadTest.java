package com.fc.utils.excel;


import cn.hutool.extra.validation.ValidationUtil;
import com.alibaba.excel.EasyExcel;
import com.fc.utils.excel.listener.CustomReadListener;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CustomReadListenerTest
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/5/6 13:16
 * @Vsersion: 1.0
 */
public class ExcelReadTest {
    @Test
    public void readTest() {
//        String fileName = "D:\\data\\test.xlsx";
//        List<ExcelTestDto> arrayList = new ArrayList<>();
//        EasyExcel.read(fileName, ExcelTestDto.class, new CustomReadListener<>((list) -> {
//            System.out.println(list.size());
//        })).sheet().doRead();
        Validator validator = ValidationUtil.getValidator();

    }

}
