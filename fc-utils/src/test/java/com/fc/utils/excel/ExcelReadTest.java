package com.fc.utils.excel;

import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.read.listener.PageReadListener;
import com.fc.utils.excel.listener.CustomReadListener;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

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
        String fileName = "D:\\data\\test.xlsx";
        List<ExcelTestDto> arrayList = new ArrayList<>();
      EasyExcel.read(fileName, new CustomReadListener<>()).sheet().doReadSync();
//        System.out.println(objects.size());
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        System.out.println();
    }

}
