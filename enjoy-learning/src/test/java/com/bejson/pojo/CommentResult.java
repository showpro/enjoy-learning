package com.bejson.pojo;

import java.util.List;

/**
 * @author zhanzhan
 * @date 2021/6/12 17:16
 */
public class CommentResult {

    private Comment comment;

    private List<Reply> replies ;

    public void setReplies(List<Reply> replies){
        this.replies = replies;
    }
    public List<Reply> getReplies(){
        return this.replies;
    }
    public void setComment(Comment comment){
        this.comment = comment;
    }
    public Comment getComment(){
        return this.comment;
    }
}
