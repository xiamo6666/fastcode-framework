package com.fc.utils.header;

import cn.hutool.poi.excel.ExcelUtil;
import com.fc.utils.file.FileExt;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: ResposeHeadUtils
 * @Description: dto
 * @Author: xwl
 * @Date: 2019-08-14 11:48
 * @Vsersion: 1.0
 */
@Slf4j
public class ResponseHeadUtils {
    /**
     * excel 下载head设置
     *
     * @param response
     */

    public static void downloadExcel(HttpServletResponse response, String name) {

        response.setContentType(ExcelUtil.XLSX_CONTENT_TYPE);
        globalSetting(response,name,FileExt.EXCEL_XLSX);

    }

    /**
     * word 下载head设置
     *
     * @param response
     */
    public static void downloadWord(HttpServletResponse response, String name) {
        response.setContentType("application/vnd.ms-word");
        globalSetting(response,name, FileExt.WORD_DOCX);

    }


    private static void globalSetting(HttpServletResponse response,String name,String extName) {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Expose-Headers", "Content-disposition");
        response.setHeader("Content-disposition", "attachment;filename=" +
                URLEncoder.encode(name, StandardCharsets.UTF_8).replaceAll("%23", "#") + extName);
    }

}
