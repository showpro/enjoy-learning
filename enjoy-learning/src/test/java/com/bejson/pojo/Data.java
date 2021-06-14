package com.bejson.pojo;

import java.util.List;

/**
 * @author zhanzhan
 * @date 2021/6/12 17:15
 */
public class Data {
    private int totalCnt;

    private List<CommentResult> comments ;

    private int totalPage;

    private int pageSize;

    public void setTotalCnt(int totalCnt){
        this.totalCnt = totalCnt;
    }
    public int getTotalCnt(){
        return this.totalCnt;
    }
    public void setComments(List<CommentResult> comments){
        this.comments = comments;
    }
    public List<CommentResult> getComments(){
        return this.comments;
    }
    public void setTotalPage(int totalPage){
        this.totalPage = totalPage;
    }
    public int getTotalPage(){
        return this.totalPage;
    }
    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }
    public int getPageSize(){
        return this.pageSize;
    }
}
