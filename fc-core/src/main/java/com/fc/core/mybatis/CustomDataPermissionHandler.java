package com.fc.core.mybatis;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import com.fc.core.exception.ServiceException;
import com.fc.core.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.schema.Column;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:23
 */
@Slf4j
public class CustomDataPermissionHandler implements DataPermissionHandler {
    /**
     * 数据权限表字段
     */
    private final static String PERMISSION_FIELD = "org_code";

    private final static String MAPPER_NAME = "AutoMapper";
    private final static Map<String, Boolean> CACHE_FIELDS = new ConcurrentHashMap<>();

    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {

        int lastIndexOf = mappedStatementId.lastIndexOf(".");
        String substring = mappedStatementId.substring(0, lastIndexOf);
        String className = substring.replace(MAPPER_NAME, "").replace(".mapper.", ".entity.");
        //采用缓存机制，避免每次进行反射操作消耗性能
        if (!CACHE_FIELDS.containsKey(className)) {
            try {
                Class<?> aClass = Class.forName(className);
                aClass.getDeclaredField(StringUtils.underlineToCamel(PERMISSION_FIELD));
                CACHE_FIELDS.put(className, true);
            } catch (Exception e) {
                CACHE_FIELDS.put(className, false);
                if (log.isDebugEnabled()) {
                    log.debug("class:[{}] 无需进行数据权限操作,当前缓存池:[{}]", className, CACHE_FIELDS);
                }
            }
        }
        //判断当前sql是否需要进行数据权限验证
        if (CACHE_FIELDS.get(className)) {
            //设置 colum like value%
            LikeExpression likeExpression = new LikeExpression();
            likeExpression.setLeftExpression(new Column(PERMISSION_FIELD));
            try {
                likeExpression.setRightExpression(new StringValue(UserUtils.getOrgCode() + "%"));
            } catch (ServiceException e) {
                return where;
            }
            return ObjectUtils.isEmpty(where) ? likeExpression : new AndExpression(likeExpression, where);
        } else {
            return where;
        }

    }
}
