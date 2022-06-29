package com.fc.system.org.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.model.query.PageDTO;
import com.fc.system.auto.entity.Org;
import com.fc.system.org.model.dto.OrgDTO;
import com.fc.system.org.model.vo.OrgVO;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:03
 */
public interface OrgService {
    /**
     * 分页获取组织机构数据
     *
     * @param pageDto 分页dto
     * @return IPage
     */
    IPage<Org> pageOrgList(PageDTO pageDto);

    /**
     * 添加组织机构
     *
     * @param orgDto orgDTO
     */
    void addOrg(OrgDTO orgDto);

    /**
     * 删除组织机构
     *
     * @param orgId orgId
     */
    void deleteOrg(Long orgId);

    /**
     * 启动组织机构
     *
     * @param orgCode orgCode
     */
    void enableOrg(String orgCode);

    /**
     * 获取组织机构结构树
     *
     * @return   List<OrgVO>
     */
    OrgVO getOrgTree();


}
