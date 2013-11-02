package com.article.domain.base;

import com.comet.system.domain.SysUser;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * This is an object that contains data related to the cms_comment table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 评论
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : cms_comment
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="cms_comment"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseCmsComment  implements Serializable{

	public static String REF = "CmsComment";
	public static String PROP_KNOWLEDGE = "knowledge";
	public static String PROP_REPLY_TIME = "replyTime";
	public static String PROP_REPLYER = "replyer";
	public static String PROP_CONTENT = "content";
	public static String PROP_ID = "id";


	// constructors
	public BaseCmsComment () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsComment (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
    /*CONTENT*/
    /*回复内容*/
	private String content;
	
    /*REPLY_TIME*/
    /*回复时间*/
	private java.sql.Timestamp replyTime;

    private java.sql.Timestamp adoptTime;

    private String commentType;
    private Boolean isAdopt;

	// many to one
	private com.article.domain.CmsArticle knowledge;
	private com.comet.system.domain.SysUser replyer;
	private com.comet.system.domain.SysUser adopter;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="com.justonetech.core.orm.hibernate.LongIdGenerator"
     *  column="ID"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 * @deprecated
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
	 * Return the value associated with the column: CONTENT
	 */
	public String getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: CONTENT
	 * @param content the CONTENT value
	 */
	public void setContent (String content) {
		this.content = content;
	}

	/**
	 * Return the value associated with the column: REPLY_TIME
	 */
	public java.sql.Timestamp getReplyTime () {
		return replyTime;
	}

	/**
	 * Set the value related to the column: REPLY_TIME
	 * @param replyTime the REPLY_TIME value
	 */
	public void setReplyTime (java.sql.Timestamp replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * Return the value associated with the column: KNOWLEDGE_ID
	 */
	public com.article.domain.CmsArticle getKnowledge () {
		return knowledge;
	}

	/**
	 * Set the value related to the column: KNOWLEDGE_ID
	 * @param knowledge the KNOWLEDGE_ID value
	 */
	public void setKnowledge (com.article.domain.CmsArticle knowledge) {
		this.knowledge = knowledge;
	}


	/**
	 * Return the value associated with the column: REPLYER
	 */
	public com.comet.system.domain.SysUser getReplyer () {
		return replyer;
	}

	/**
	 * Set the value related to the column: REPLYER
	 * @param replyer the REPLYER value
	 */
	public void setReplyer (com.comet.system.domain.SysUser  replyer) {
		this.replyer = replyer;
	}

    public Timestamp getAdoptTime() {
        return adoptTime;
    }

    public void setAdoptTime(Timestamp adoptTime) {
        this.adoptTime = adoptTime;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public Boolean getIsAdopt() {
        return isAdopt;
    }

    public void setIsAdopt(Boolean adopt) {
        isAdopt = adopt;
    }

    public SysUser getAdopter() {
        return adopter;
    }

    public void setAdopter(SysUser adopter) {
        this.adopter = adopter;
    }

    public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.CmsComment)) return false;
		else {
			com.article.domain.CmsComment cmsComment = (com.article.domain.CmsComment) obj;
			if (null == this.getId() || null == cmsComment.getId()) return false;
			else return (this.getId().equals(cmsComment.getId()));
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
		builder.append(content);
		builder.append(replyTime);
		return builder.toString();
	}


}