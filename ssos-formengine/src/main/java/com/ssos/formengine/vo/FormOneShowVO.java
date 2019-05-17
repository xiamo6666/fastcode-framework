package com.ssos.formengine.vo;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FormOneShowVO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-15 17:49
 * @Vsersion: 1.0
 */
@Data
public class FormOneShowVO {
    private FormAllShowVO formAllShowVO;
    private List<FormAllShowVO> allShowVOS;
}
