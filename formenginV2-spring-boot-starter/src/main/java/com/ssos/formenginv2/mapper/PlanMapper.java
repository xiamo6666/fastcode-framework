package com.ssos.formenginv2.mapper;

import com.ssos.formenginv2.entity.Plan;
import com.ssos.formenginv2.vo.PlanVo;
import com.ssos.mybatilspro.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PlanMapper extends BaseMapper<Plan> {
    @Select("select * from plan")
    List<PlanVo> findPlan();
}
