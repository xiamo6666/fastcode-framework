package com.ssos.base.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: PageRequest
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-04-09 11:27
 * @Vsersion: 1.0
 */
@Data
public class PageRequest implements Serializable {
    private static final long serialVersionUID = -7742633354158106128L;
    private static final int DEFAULT_PAGE_NUM = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    @ApiParam("当前页面")
    private int pageNum = DEFAULT_PAGE_NUM;
    @ApiParam("默认每页显示10数据")
    private int pageSize = DEFAULT_PAGE_SIZE;

    public PageRequest(int pageNum, int pageSize) {
        this.setPageNum(pageNum);
        this.setPageSize(pageSize);
    }

    public void setPageNum(int pageNum) {
        if (pageNum < 1) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }

    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 10;
        } else {
            this.pageSize = pageSize;
        }

    }
}

