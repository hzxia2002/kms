package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_course_article table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 知识点
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : cmc_course_article
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="cms_course_article"
 */

public abstract class BaseCmsCourseArticle  implements Serializable{

	public static String REF = "CmsCourseArticle";
	public static String PROP_COURSE = "course";
	public static String PROP_ID = "id";
	public static String PROP_ARTICLE = "article";


	// constructors
	public BaseCmsCourseArticle () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsCourseArticle (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// many to one
	private com.article.domain.CmsArticle article;
	private com.article.domain.CmsStudyCourse course;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="com.justonetech.core.orm.hibernate.LongIdGenerator"
     *  column="ID"
     */
	public Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 * @deprecated
	 */
	public void setId (Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: ARTICLE_ID
	 */
	public com.article.domain.CmsArticle getArticle () {
		return article;
	}

	/**
	 * Set the value related to the column: ARTICLE_ID
	 * @param article the ARTICLE_ID value
	 */
	public void setArticle (com.article.domain.CmsArticle article) {
		this.article = article;
	}


	/**
	 * Return the value associated with the column: COURSE_ID
	 */
	public com.article.domain.CmsStudyCourse getCourse () {
		return course;
	}

	/**
	 * Set the value related to the column: COURSE_ID
	 * @param course the COURSE_ID value
	 */
	public void setCourse (com.article.domain.CmsStudyCourse course) {
		this.course = course;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.CmsCourseArticle)) return false;
		else {
			com.article.domain.CmsCourseArticle cmsCourseArticle = (com.article.domain.CmsCourseArticle) obj;
			if (null == this.getId() || null == cmsCourseArticle.getId()) return false;
			else return (this.getId().equals(cmsCourseArticle.getId()));
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
		return builder.toString();
	}


}