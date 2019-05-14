package com.ssos.web.base.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PageResute
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-04-09 11:27
 * @Vsersion: 1.0
 */
@Data
public class PageResute<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageNum;
    private int pageSize;
    private long totalCount;
    private List<T> data;

    public PageResute(Integer pageNum, Integer pageSize, Integer totalCount, List data){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;
    }


}
