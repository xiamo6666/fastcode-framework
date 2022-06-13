package com.fc.core.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fc.core.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:24
 */
@Slf4j
@Component
public class CustomObjectHandler implements MetaObjectHandler {
    private static final String ORG_NAME = "orgName";
    private static final String ORG_CODE = "orgCode";

    @Override
    public void insertFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            log.debug("start insert fill ....");
        }
        //判斷是否存在
        if (getFieldValByName(ORG_NAME, metaObject) == null) {
            this.strictInsertFill(
                    metaObject,
                    ORG_NAME,
                    String.class,
                    UserUtils.getOrgName()
            );
        }
        if (getFieldValByName(ORG_CODE, metaObject) == null) {
            this.strictInsertFill(
                    metaObject,
                    ORG_CODE,
                    String.class,
                    UserUtils.getOrgCode()
            );
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }

}
