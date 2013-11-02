package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_attachments table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 知识点附件
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : cms_attachments
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="cms_attachments"
 */

public abstract class BaseCmsAttachments  implements Serializable{

	public static String REF = "CmsAttachments";
	public static String PROP_KNOWLEDGE = "knowledge";
	public static String PROP_PATH = "path";
	public static String PROP_UPLOADER = "uploader";
	public static String PROP_TARGET_NAME = "targetName";
	public static String PROP_UPLOAD_DATE = "uploadDate";
	public static String PROP_ORG_NAME = "orgName";
	public static String PROP_ID = "id";
	public static String PROP_EXT_NAME = "extName";


	// constructors
	public BaseCmsAttachments () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsAttachments (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
    /*ORG_NAME*/
    /*文件原名*/
	private String orgName;
	
    /*TARGET_NAME*/
    /*修改后的名称*/
	private String targetName;
	
    /*EXT_NAME*/
    /*扩展名*/
	private String extName;
	
    /*UPLOAD_DATE*/
    /*上传时间*/
	private String uploadDate;
	
    /*UPLOADER*/
    /*上传人员*/
	private String uploader;
	

	// many to one
	private com.article.domain.CmsCatalog path;
	private com.article.domain.CmsArticle knowledge;



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
	 * Return the value associated with the column: ORG_NAME
	 */
	public String getOrgName () {
		return orgName;
	}

	/**
	 * Set the value related to the column: ORG_NAME
	 * @param orgName the ORG_NAME value
	 */
	public void setOrgName (String orgName) {
		this.orgName = orgName;
	}


	/**
	 * Return the value associated with the column: TARGET_NAME
	 */
	public String getTargetName () {
		return targetName;
	}

	/**
	 * Set the value related to the column: TARGET_NAME
	 * @param targetName the TARGET_NAME value
	 */
	public void setTargetName (String targetName) {
		this.targetName = targetName;
	}


	/**
	 * Return the value associated with the column: EXT_NAME
	 */
	public String getExtName () {
		return extName;
	}

	/**
	 * Set the value related to the column: EXT_NAME
	 * @param extName the EXT_NAME value
	 */
	public void setExtName (String extName) {
		this.extName = extName;
	}


	/**
	 * Return the value associated with the column: UPLOAD_DATE
	 */
	public String getUploadDate () {
		return uploadDate;
	}

	/**
	 * Set the value related to the column: UPLOAD_DATE
	 * @param uploadDate the UPLOAD_DATE value
	 */
	public void setUploadDate (String uploadDate) {
		this.uploadDate = uploadDate;
	}


	/**
	 * Return the value associated with the column: UPLOADER
	 */
	public String getUploader () {
		return uploader;
	}

	/**
	 * Set the value related to the column: UPLOADER
	 * @param uploader the UPLOADER value
	 */
	public void setUploader (String uploader) {
		this.uploader = uploader;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.CmsAttachments)) return false;
		else {
			com.article.domain.CmsAttachments cmsAttachments = (com.article.domain.CmsAttachments) obj;
			if (null == this.getId() || null == cmsAttachments.getId()) return false;
			else return (this.getId().equals(cmsAttachments.getId()));
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
		builder.append(orgName);
		builder.append(targetName);
		builder.append(extName);
		builder.append(uploadDate);
		builder.append(uploader);
		return builder.toString();
	}


}