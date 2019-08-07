package com.ssos.base.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: PageResute
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-04-09 11:27
 * @Vsersion: 1.0
 */
@Data
public class PageResult<T extends Collection> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageNum;
    private int pageSize;
    private long totalCount;
    private long totalPages;
    private T data;

    public PageResult(Integer pageNum, Integer pageSize, Long totalCount, T data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;
        long totalPages = totalCount / pageSize;
        if (totalPages == 0) {
            this.totalPages = 1;
        } else if ((totalPages > 0) && (totalCount % pageSize) != 0) {
            totalPages++;
            this.totalPages = totalPages;
        }
    }


}
