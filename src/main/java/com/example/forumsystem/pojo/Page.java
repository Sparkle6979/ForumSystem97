package com.example.forumsystem.pojo;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/4/20 18:58
 */
public class Page {

    //当前页码
    private int current=1;
    //显示上限，每页的数据量
    private int limit=10;

    //数据的总数，用于计算总页数 [数据库表的数据]
    private int rows;

    //查询路径，用于复用分页链接
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current >= 1)
            this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >= 0)
            this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLimit(int limit) {
        if (limit>=1&&limit<=100) {
            this.limit = limit;
        }
    }

    public
}
