package com.article.domain.base;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_article table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 文章
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : cms_article
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="cms_article"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseCmsArticle  implements Serializable{

	public static String REF = "CmsArticle";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_PUBLISHER = "publisher";
	public static String PROP_ACCESS_RANGE_ID = "accessRangeId";
	public static String PROP_ATTACH_PATH = "attachPath";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_VISIT_TIMES = "visitTimes";
	public static String PROP_IS_TOP = "isTop";
	public static String PROP_IS_VALID = "isValid";
	public static String PROP_CLICK_NUM = "clickNum";
	public static String PROP_THUMB_PATH = "thumbPath";
	public static String PROP_IS_PUBLISHED = "isPublished";
	public static String PROP_PUBLISH_TIME = "publishTime";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_PUBLISH_DATE = "publishDate";
	public static String PROP_KEY_WORD = "keyWord";
	public static String PROP_PATH = "path";
	public static String PROP_LINK_URL = "linkUrl";
	public static String PROP_TITLE = "title";
	public static String PROP_CONTENT = "content";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseCmsArticle () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsArticle (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
    /*TITLE*/
    /*标题*/
	private String title;
	
    /*KEY_WORD*/
    /*关键字*/
	private String keyWord;
	
    /*CONTENT*/
    /*内容*/
	private String content;
	
    /*CLICK_NUM*/
    /*点击次数*/
	private java.lang.Long clickNum;
	
    /*IS_PUBLISHED*/
    /*是否发布*/
	private Boolean isPublished;
	
    /*PUBLISH_TIME*/
    /*发布时间*/
	private java.sql.Timestamp publishTime;
	
    /*PUBLISH_DATE*/
    /*发布时间*/
	private java.sql.Timestamp publishDate;
	
    /*VISIT_TIMES*/
    /*访问次数*/
	private java.lang.Long visitTimes;
	
    /*IS_VALID*/
    /*是否有效*/
	private Boolean isValid;
	
    /*IS_TOP*/
    /*是否置顶*/
	private Boolean isTop;
	
    /*ATTACH_PATH*/
    /*展示图片*/
	private String attachPath;
	
    /*THUMB_PATH*/
    /*缩略图*/
	private String thumbPath;
	
    /*LINK_URL*/
    /*外部链接*/
	private String linkUrl;
	
    /*CREATE_TIME*/
    /*创建时间*/
	private java.sql.Timestamp createTime;
	
    /*UPDATE_TIME*/
    /*更新时间*/
	private java.sql.Timestamp updateTime;
	
    /*UPDATE_USER*/
    /*更新人*/
	private String updateUser;
	
    /*CREATE_USER*/
    /*创建人*/
	private String createUser;
	
    /*ACCESS_RANGE_ID*/
    /*发布范围*/
	private java.lang.Long accessRangeId;
	

	// many to one
	private com.article.domain.CmsCatalog path;
	private com.comet.system.domain.SysUser publisher;

    private String author;
    private String summary;
    private Long classHour;
    private java.sql.Timestamp studyStartTime;
    private java.sql.Timestamp studyEndTime;
    private Boolean isRecommend;

    private Long docId;

    private String extension1;
    private String extension2;
    private String extension3;
    private String extension4;
    private String extension5;

    private String catalogue;

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }


    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    public java.sql.Timestamp getStudyEndTime() {
        return studyEndTime;
    }

    public void setStudyEndTime(java.sql.Timestamp studyEndTime) {
        this.studyEndTime = studyEndTime;
    }

    public java.sql.Timestamp getStudyStartTime() {
        return studyStartTime;
    }

    public void setStudyStartTime(java.sql.Timestamp studyStartTime) {
        this.studyStartTime = studyStartTime;
    }

    public Long getClassHour() {
        return classHour;
    }

    public void setClassHour(Long classHour) {
        this.classHour = classHour;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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
	 * Return the value associated with the column: TITLE
	 */
	public String getTitle () {
		return title;
	}

	/**
	 * Set the value related to the column: TITLE
	 * @param title the TITLE value
	 */
	public void setTitle (String title) {
		this.title = title;
	}


	/**
	 * Return the value associated with the column: KEY_WORD
	 */
	public String getKeyWord () {
		return keyWord;
	}

	/**
	 * Set the value related to the column: KEY_WORD
	 * @param keyWord the KEY_WORD value
	 */
	public void setKeyWord (String keyWord) {
		this.keyWord = keyWord;
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
	 * Return the value associated with the column: CLICK_NUM
	 */
	public java.lang.Long getClickNum () {
		return clickNum;
	}

	/**
	 * Set the value related to the column: CLICK_NUM
	 * @param clickNum the CLICK_NUM value
	 */
	public void setClickNum (java.lang.Long clickNum) {
		this.clickNum = clickNum;
	}


	/**
	 * Return the value associated with the column: IS_PUBLISHED
	 */
	public Boolean getIsPublished () {
		return isPublished;
	}

	/**
	 * Set the value related to the column: IS_PUBLISHED
	 * @param isPublished the IS_PUBLISHED value
	 */
	public void setIsPublished (Boolean isPublished) {
		this.isPublished = isPublished;
	}


	/**
	 * Return the value associated with the column: PUBLISH_TIME
	 */
	public java.sql.Timestamp getPublishTime () {
		return publishTime;
	}

	/**
	 * Set the value related to the column: PUBLISH_TIME
	 * @param publishTime the PUBLISH_TIME value
	 */
	public void setPublishTime (java.sql.Timestamp publishTime) {
		this.publishTime = publishTime;
	}


	/**
	 * Return the value associated with the column: PUBLISH_DATE
	 */
	public java.sql.Timestamp getPublishDate () {
		return publishDate;
	}

	/**
	 * Set the value related to the column: PUBLISH_DATE
	 * @param publishDate the PUBLISH_DATE value
	 */
	public void setPublishDate (java.sql.Timestamp publishDate) {
		this.publishDate = publishDate;
	}


	/**
	 * Return the value associated with the column: VISIT_TIMES
	 */
	public java.lang.Long getVisitTimes () {
		return visitTimes;
	}

	/**
	 * Set the value related to the column: VISIT_TIMES
	 * @param visitTimes the VISIT_TIMES value
	 */
	public void setVisitTimes (java.lang.Long visitTimes) {
		this.visitTimes = visitTimes;
	}


	/**
	 * Return the value associated with the column: IS_VALID
	 */
	public Boolean getIsValid () {
		return isValid;
	}

	/**
	 * Set the value related to the column: IS_VALID
	 * @param isValid the IS_VALID value
	 */
	public void setIsValid (Boolean isValid) {
		this.isValid = isValid;
	}


	/**
	 * Return the value associated with the column: IS_TOP
	 */
	public Boolean getIsTop () {
		return isTop;
	}

	/**
	 * Set the value related to the column: IS_TOP
	 * @param isTop the IS_TOP value
	 */
	public void setIsTop (Boolean isTop) {
		this.isTop = isTop;
	}


	/**
	 * Return the value associated with the column: ATTACH_PATH
	 */
	public String getAttachPath () {
		return attachPath;
	}

	/**
	 * Set the value related to the column: ATTACH_PATH
	 * @param attachPath the ATTACH_PATH value
	 */
	public void setAttachPath (String attachPath) {
		this.attachPath = attachPath;
	}


	/**
	 * Return the value associated with the column: THUMB_PATH
	 */
	public String getThumbPath () {
		return thumbPath;
	}

	/**
	 * Set the value related to the column: THUMB_PATH
	 * @param thumbPath the THUMB_PATH value
	 */
	public void setThumbPath (String thumbPath) {
		this.thumbPath = thumbPath;
	}


	/**
	 * Return the value associated with the column: LINK_URL
	 */
	public String getLinkUrl () {
		return linkUrl;
	}

	/**
	 * Set the value related to the column: LINK_URL
	 * @param linkUrl the LINK_URL value
	 */
	public void setLinkUrl (String linkUrl) {
		this.linkUrl = linkUrl;
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


	/**
	 * Return the value associated with the column: ACCESS_RANGE_ID
	 */
	public java.lang.Long getAccessRangeId () {
		return accessRangeId;
	}

	/**
	 * Set the value related to the column: ACCESS_RANGE_ID
	 * @param accessRangeId the ACCESS_RANGE_ID value
	 */
	public void setAccessRangeId (java.lang.Long accessRangeId) {
		this.accessRangeId = accessRangeId;
	}


	/**
	 * Return the value associated with the column: PATH_ID
	 */
	public com.article.domain.CmsCatalog getPath () {
		return path;
	}

	/**
	 * Set the value related to the column: PATH_ID
	 * @param path the PATH_ID value
	 */
	public void setPath (com.article.domain.CmsCatalog path) {
		this.path = path;
	}


	/**
	 * Return the value associated with the column: PUBLISHER
	 */
	public com.comet.system.domain.SysUser getPublisher () {
		return publisher;
	}

	/**
	 * Set the value related to the column: PUBLISHER
	 * @param publisher the PUBLISHER value
	 */
	public void setPublisher (com.comet.system.domain.SysUser publisher) {
		this.publisher = publisher;
	}

    public String getExtension1() {
        return extension1;
    }

    public void setExtension1(String extension1) {
        this.extension1 = extension1;
    }

    public String getExtension2() {
        return extension2;
    }

    public void setExtension2(String extension2) {
        this.extension2 = extension2;
    }

    public String getExtension3() {
        return extension3;
    }

    public void setExtension3(String extension3) {
        this.extension3 = extension3;
    }

    public String getExtension4() {
        return extension4;
    }

    public void setExtension4(String extension4) {
        this.extension4 = extension4;
    }

    public String getExtension5() {
        return extension5;
    }

    public void setExtension5(String extension5) {
        this.extension5 = extension5;
    }

    public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.CmsArticle)) return false;
		else {
			com.article.domain.CmsArticle cmsArticle = (com.article.domain.CmsArticle) obj;
			if (null == this.getId() || null == cmsArticle.getId()) return false;
			else return (this.getId().equals(cmsArticle.getId()));
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
		builder.append(title);
		builder.append(keyWord);
		builder.append(content);
		builder.append(clickNum);
		builder.append(isPublished);
		builder.append(publishTime);
		builder.append(publishDate);
		builder.append(visitTimes);
		builder.append(isValid);
		builder.append(isTop);
		builder.append(attachPath);
		builder.append(thumbPath);
		builder.append(linkUrl);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		builder.append(accessRangeId);
		return builder.toString();
	}


}