package com.fc.system.org.mapper;

import com.fc.system.org.model.vo.OrgVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/22 10:19
 */
public interface OrgMapper {
    /**
     * 获取组织机构树
     *
     * @param orgCode orgCode
     * @return
     */
    List<OrgVO> getOrgTree(@Param("orgCode") String orgCode);
}
