package com.article.domain.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tcg on 2016/3/20.
 */
public class BaseConQuestion implements Serializable {
    // constructors
    public BaseConQuestion () {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseConQuestion (Long id) {
        this.setId(id);
        initialize();
    }

    protected void initialize () {}


    private int hashCode = Integer.MIN_VALUE;

    private Long id;
    private String title;
    private String content;
    private String remark;
    private Long indexNo;
    private String asker;
    private Date publishTime;
    private Date responseTime;
    private String responser;
    private Boolean isTop;
    private Date setTopTime;
    private Date createTime;
    private Date updateTime;
    private String updateUser;
    private String createUser;
    private Long refId;

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSetTopTime() {
        return setTopTime;
    }

    public void setSetTopTime(Date setTopTime) {
        this.setTopTime = setTopTime;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean top) {
        isTop = top;
    }

    public String getResponser() {
        return responser;
    }

    public void setResponser(String responser) {
        this.responser = responser;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public Long getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Long indexNo) {
        this.indexNo = indexNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean equals (Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.article.domain.ConQuestion)) return false;
        else {
            com.article.domain.ConQuestion conQuestion = (com.article.domain.ConQuestion) obj;
            if (null == this.getId() || null == conQuestion.getId()) return false;
            else return (this.getId().equals(conQuestion.getId()));
        }
    }

    public int hashCode () {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getId()) return super.hashCode();
            else {
                String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }


    public String toString () {
        org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
        builder.append(id);
        builder.append(remark);
        return builder.toString();
    }
}
