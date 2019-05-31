package com.ssos.formenginv2.service.impl;

import com.ssos.formenginv2.annotation.Formengin;
import com.ssos.formenginv2.dto.PlanDto;
import com.ssos.formenginv2.entity.Plan;
import com.ssos.formenginv2.mapper.PlanMapper;
import com.ssos.formenginv2.service.PlanService;
import com.ssos.formenginv2.vo.PlanVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: PlanServiceImpl
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-24 16:24
 * @Vsersion: 1.0
 */
@Service
@Formengin("plan")
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanMapper planMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(PlanDto dto) {
        Plan plan = new Plan();
        BeanUtils.copyProperties(dto, plan);
        planMapper.insert(plan);
        dto.setId(plan.getId());
    }

    @Override
    public List<PlanVo> select() {
        return planMapper.findPlan();
    }
}
