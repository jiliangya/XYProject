package com.chong.xinyuproject_new.utils;

import java.util.List;

/**
 * 分页返回的结果
 */
public class PageResponse<T> {
    private int pageNum; //当前的页数
    private int pageSize; //每页显示的数量
    private int size; //本页返回是数量
    private long total; //总数量
    private List<T> oList; //本页的所有数据


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getoList() {
        return oList;
    }

    public void setoList(List<T> oList) {
        this.oList = oList;
    }

    @Override
    public String toString() {
        return "PageResponse{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", size=" + size +
                ", total=" + total +
                ", oList=" + oList +
                '}';
    }
}
