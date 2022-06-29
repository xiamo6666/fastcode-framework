package com.fc.system.org.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.model.query.PageDTO;
import com.fc.core.exception.ServiceException;
import com.fc.core.utils.PageDtoUtils;
import com.fc.core.utils.UserUtils;
import com.fc.system.auto.entity.Org;
import com.fc.system.auto.service.OrgAutoService;
import com.fc.system.org.mapper.OrgMapper;
import com.fc.system.org.model.dto.OrgDTO;
import com.fc.system.org.model.vo.OrgVO;
import com.fc.system.org.service.OrgService;
import com.fc.utils.recursion.RecursionModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:03
 */
@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OrgAutoService orgAutoService;

    @Autowired
    private OrgMapper orgMapper;

    @Override
    public IPage<Org> pageOrgList(PageDTO pageDto) {
        return orgAutoService.lambdaQuery()
                .page(
                        PageDtoUtils.convert(pageDto)
                );
    }

    @Override
    public void addOrg(OrgDTO orgDto) {
        Org org = new Org();
        BeanUtils.copyProperties(orgDto, org);
        orgAutoService.save(org);
    }

    @Override
    public void deleteOrg(Long orgId) {

        //先判断是否有子集机构
        Org org = orgAutoService.getById(orgId);
        Long count = orgAutoService.lambdaQuery()
                .eq(
                        Org::getParentOrgCode,
                        org.getOrgCode()
                )
                .count();
        if (count > 0) {
            throw new ServiceException("该组织机构下存在子机构,不允许删除");
        }
        //在判断该组织机构是否启用
        if (1 == org.getEnable()) {
            throw new ServiceException("该组织机构已经启用,不允许删除");
        }
        orgAutoService.removeById(orgId);

    }

    @Override
    public void enableOrg(String orgCode) {
        orgAutoService.lambdaUpdate()
                .set(Org::getEnable, 1)
                .eq(Org::getOrgCode, orgCode)
                .update();
    }

    @Override
    public OrgVO getOrgTree() {
        List<OrgVO> orgTree = orgMapper.getOrgTree(UserUtils.getOrgCode());
        //sql递归处理 第一条数据是最上级数据
        if (CollectionUtil.isEmpty(orgTree)) {
            return null;
        }
        OrgVO orgVO = orgTree.get(0);
        orgTree.forEach(orgTrees ->
                orgVO.setChildren(
                        recursion(orgVO, orgTree)
                )
        );
        return orgVO;
    }

    private static List<OrgVO> recursion(OrgVO orgVO, List<OrgVO> list) {
        return list.stream()
                .filter(p -> p.getParentOrgCode().equals(orgVO.getOrgCode()))
                .peek(p -> p.setChildren(recursion(p, list)))
                .collect(Collectors.toList());
    }

}
