package com.ssos.formenginv2.service;

import com.ssos.formenginv2.dto.PlanDto;
import com.ssos.formenginv2.vo.PlanVo;

import java.util.List;

/**
 * @ClassName: PlanService
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-24 16:22
 * @Vsersion: 1.0
 */
public interface PlanService {
    void add(PlanDto dto);
    List<PlanVo> select();
}
