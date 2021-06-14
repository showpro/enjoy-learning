package com.bejson.pojo;

/**
 * 评论回复实体类，
 *
 * 比如，
 * 1、快销网发了一篇种草的帖子；
 * 2、张三对发的帖子进行了一条评论；（会生成评论id）
 * 3、快销网对张三的帖子做了回复；（会生成一条回复id）
 *
 * @author zhanzhan
 * @date 2021/6/12 17:24
 */
public class Reply {
    //本条回复的id(主键)
    private int id;
    //回复者userId
    private int authorId;
    //回复者姓名【如果是楼主，可以打上楼主标签】
    private String authorName;
    //回复者等级
    private int honorLevel;
    //热度
    private int hotValue;
    //记录回复指向的人,即回复目标用户 (只会发生在回复中 判断target_id==0，目标为0，说明未回复)
    private int toUserId;
    //回复内容："😂😂是这样的"
    private String content;
    //回复内容："{\"pureText\":\"😂😂是这样的\"}"
    private String contentV2;
    //平台：康有利、快销网，PC、手机、PAD
    private int platform;
    //评论具体的目标，->  zhogncaoId/commentId 对应的帖子id,或者评论id
    //实体id,这里为评论id
    private int entryId;
    //评论的目标的类别 -> zhongcao/comment  1：帖子(种草) 2: 评论 支持回复评论
    //实体类型：2 评论（回复其实也是评论）
    private int entryType;
    //回复哪条评论id、回复id，【这里如果对评论进行回复，所以是评论的id，如果是对评论的回复进行回复，就是评论的回复id】
    private int toCommentId;
    //该条回复的点赞数
    private int likes;
    //该条回复的回踩数
    private int dislikes;
    //回复的目标对象头像地址
    private String headImg;
    //回复实体归属评论用户id【该回复属于哪个评论作者】
    private int entryOwnerId;
    //回复的对象姓名
    private String toUserName;
    //是否点赞
    private boolean isLiked;

    private boolean isDisLiked;
    //回复的对象的等级
    private int toUserHonorLevel;
    //回复时间
    private int createTime;
    //更新时间
    private int updateTime;

    private boolean isAccepted;

    private boolean isLitigant;

    private boolean isWonderful;

    private boolean edited;

    private String toUserEducationInfo;

    private boolean hostComment;

    private String thumbNail;

    private String educationInfo;

    private String headDecorateUrl;

    public void setHonorLevel(int honorLevel){
        this.honorLevel = honorLevel;
    }
    public int getHonorLevel(){
        return this.honorLevel;
    }
    public void setHotValue(int hotValue){
        this.hotValue = hotValue;
    }
    public int getHotValue(){
        return this.hotValue;
    }
    public void setToUserId(int toUserId){
        this.toUserId = toUserId;
    }
    public int getToUserId(){
        return this.toUserId;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setPlatform(int platform){
        this.platform = platform;
    }
    public int getPlatform(){
        return this.platform;
    }
    public void setEntryId(int entryId){
        this.entryId = entryId;
    }
    public int getEntryId(){
        return this.entryId;
    }
    public void setToCommentId(int toCommentId){
        this.toCommentId = toCommentId;
    }
    public int getToCommentId(){
        return this.toCommentId;
    }
    public void setIsLitigant(boolean isLitigant){
        this.isLitigant = isLitigant;
    }
    public boolean getIsLitigant(){
        return this.isLitigant;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setIsWonderful(boolean isWonderful){
        this.isWonderful = isWonderful;
    }
    public boolean getIsWonderful(){
        return this.isWonderful;
    }
    public void setLikes(int likes){
        this.likes = likes;
    }
    public int getLikes(){
        return this.likes;
    }
    public void setEntryType(int entryType){
        this.entryType = entryType;
    }
    public int getEntryType(){
        return this.entryType;
    }
    public void setHeadImg(String headImg){
        this.headImg = headImg;
    }
    public String getHeadImg(){
        return this.headImg;
    }
    public void setEdited(boolean edited){
        this.edited = edited;
    }
    public boolean getEdited(){
        return this.edited;
    }
    public void setToUserEducationInfo(String toUserEducationInfo){
        this.toUserEducationInfo = toUserEducationInfo;
    }
    public String getToUserEducationInfo(){
        return this.toUserEducationInfo;
    }
    public void setIsLiked(boolean isLiked){
        this.isLiked = isLiked;
    }
    public boolean getIsLiked(){
        return this.isLiked;
    }
    public void setContentV2(String contentV2){
        this.contentV2 = contentV2;
    }
    public String getContentV2(){
        return this.contentV2;
    }
    public void setUpdateTime(int updateTime){
        this.updateTime = updateTime;
    }
    public int getUpdateTime(){
        return this.updateTime;
    }
    public void setDislikes(int dislikes){
        this.dislikes = dislikes;
    }
    public int getDislikes(){
        return this.dislikes;
    }
    public void setAuthorId(int authorId){
        this.authorId = authorId;
    }
    public int getAuthorId(){
        return this.authorId;
    }
    public void setIsDisLiked(boolean isDisLiked){
        this.isDisLiked = isDisLiked;
    }
    public boolean getIsDisLiked(){
        return this.isDisLiked;
    }
    public void setHostComment(boolean hostComment){
        this.hostComment = hostComment;
    }
    public boolean getHostComment(){
        return this.hostComment;
    }
    public void setThumbNail(String thumbNail){
        this.thumbNail = thumbNail;
    }
    public String getThumbNail(){
        return this.thumbNail;
    }
    public void setEducationInfo(String educationInfo){
        this.educationInfo = educationInfo;
    }
    public String getEducationInfo(){
        return this.educationInfo;
    }
    public void setCreateTime(int createTime){
        this.createTime = createTime;
    }
    public int getCreateTime(){
        return this.createTime;
    }
    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }
    public String getAuthorName(){
        return this.authorName;
    }
    public void setIsAccepted(boolean isAccepted){
        this.isAccepted = isAccepted;
    }
    public boolean getIsAccepted(){
        return this.isAccepted;
    }
    public void setEntryOwnerId(int entryOwnerId){
        this.entryOwnerId = entryOwnerId;
    }
    public int getEntryOwnerId(){
        return this.entryOwnerId;
    }
    public void setToUserName(String toUserName){
        this.toUserName = toUserName;
    }
    public String getToUserName(){
        return this.toUserName;
    }
    public void setToUserHonorLevel(int toUserHonorLevel){
        this.toUserHonorLevel = toUserHonorLevel;
    }
    public int getToUserHonorLevel(){
        return this.toUserHonorLevel;
    }
    public void setHeadDecorateUrl(String headDecorateUrl){
        this.headDecorateUrl = headDecorateUrl;
    }
    public String getHeadDecorateUrl(){
        return this.headDecorateUrl;
    }
}
