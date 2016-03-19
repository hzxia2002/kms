package com.article.domain.base;

import com.article.domain.CmsArticle;
import com.article.domain.CmsStudyCourse;
import com.article.domain.ExaPaper;

/**
 * Created by tcg on 2016/3/19.
 */
public class BaseExaArticlePaper {


    // constructors
    public BaseExaArticlePaper () {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseExaArticlePaper (Long id) {
        this.setId(id);
        initialize();
    }

    protected void initialize () {}



    private int hashCode = Integer.MIN_VALUE;

    // primary key
    private Long id;

    private ExaPaper paper;


    private CmsStudyCourse course;



    /*CREATE_TIME*/
    /*����ʱ��*/
    private java.sql.Timestamp createTime;

    /*UPDATE_TIME*/
    /*����ʱ��*/
    private java.sql.Timestamp updateTime;

    /*UPDATE_USER*/
    /*������*/
    private String updateUser;

    /*CREATE_USER*/
    /*������*/
    private String createUser;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CmsStudyCourse getCourse() {
        return course;
    }

    public void setCourse(CmsStudyCourse course) {
        this.course = course;
    }

    public ExaPaper getPaper() {
        return paper;
    }

    public void setPaper(ExaPaper paper) {
        this.paper = paper;
    }


    /**
     * Return the value associated with the column: CREATE_TIME
     */
    public java.sql.Timestamp getCreateTime () {
        return createTime;
    }

    /**
     * Set the value related to the column: CREATE_TIME
     * @param createTime the CREATE_TIME value
     */
    public void setCreateTime (java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    /**
     * Return the value associated with the column: UPDATE_TIME
     */
    public java.sql.Timestamp getUpdateTime () {
        return updateTime;
    }

    /**
     * Set the value related to the column: UPDATE_TIME
     * @param updateTime the UPDATE_TIME value
     */
    public void setUpdateTime (java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
    }


    /**
     * Return the value associated with the column: UPDATE_USER
     */
    public String getUpdateUser () {
        return updateUser;
    }

    /**
     * Set the value related to the column: UPDATE_USER
     * @param updateUser the UPDATE_USER value
     */
    public void setUpdateUser (String updateUser) {
        this.updateUser = updateUser;
    }


    /**
     * Return the value associated with the column: CREATE_USER
     */
    public String getCreateUser () {
        return createUser;
    }

    /**
     * Set the value related to the column: CREATE_USER
     * @param createUser the CREATE_USER value
     */
    public void setCreateUser (String createUser) {
        this.createUser = createUser;
    }



    public boolean equals (Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.article.domain.ExaArticlePaper)) return false;
        else {
            com.article.domain.ExaArticlePaper exaArticlePaper = (com.article.domain.ExaArticlePaper) obj;
            if (null == this.getId() || null == exaArticlePaper.getId()) return false;
            else return (this.getId().equals(exaArticlePaper.getId()));
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

        builder.append(createTime);
        builder.append(updateTime);
        builder.append(updateUser);
        builder.append(createUser);
        return builder.toString();
    }
}
