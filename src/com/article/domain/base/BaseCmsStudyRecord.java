package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_study_record table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * Treeable : false
 * SyncDao : false
 * SyncTemplatepatterns : list\w*
 * Projectable : false
 * SyncJsp : true
 * SyncBoolean : get
 *
 * @hibernate.class
 *  table="cms_study_record"
 */

public abstract class BaseCmsStudyRecord  implements Serializable{

	public static String REF = "CmsStudyRecord";
	public static String PROP_TOTAL_HOUR = "totalHour";
	public static String PROP_LAST_STUDY_TIME = "lastStudyTime";
	public static String PROP_USER = "user";
	public static String PROP_STUDY_TIMES = "studyTimes";
	public static String PROP_COURSE = "course";
	public static String PROP_ID = "id";


	// constructors
	public BaseCmsStudyRecord () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsStudyRecord (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
	private java.sql.Timestamp lastStudyTime;
	
	private Double totalHour;
	
	private Long studyTimes;
	

	// many to one
	private com.comet.system.domain.SysUser user;
	private com.article.domain.CmsArticle course;



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
	 * Return the value associated with the column: LAST_STUDY_TIME
	 */
	public java.sql.Timestamp getLastStudyTime () {
		return lastStudyTime;
	}

	/**
	 * Set the value related to the column: LAST_STUDY_TIME
	 * @param lastStudyTime the LAST_STUDY_TIME value
	 */
	public void setLastStudyTime (java.sql.Timestamp lastStudyTime) {
		this.lastStudyTime = lastStudyTime;
	}


	/**
	 * Return the value associated with the column: TOTAL_HOUR
	 */
	public Double getTotalHour () {
		return totalHour;
	}

	/**
	 * Set the value related to the column: TOTAL_HOUR
	 * @param totalHour the TOTAL_HOUR value
	 */
	public void setTotalHour (Double totalHour) {
		this.totalHour = totalHour;
	}


	/**
	 * Return the value associated with the column: STUDY_TIMES
	 */
	public Long getStudyTimes () {
		return studyTimes;
	}

	/**
	 * Set the value related to the column: STUDY_TIMES
	 * @param studyTimes the STUDY_TIMES value
	 */
	public void setStudyTimes (Long studyTimes) {
		this.studyTimes = studyTimes;
	}


	/**
	 * Return the value associated with the column: USER_ID
	 */
	public com.comet.system.domain.SysUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: USER_ID
	 * @param user the USER_ID value
	 */
	public void setUser (com.comet.system.domain.SysUser user) {
		this.user = user;
	}


	/**
	 * Return the value associated with the column: COURSE_ID
	 */
	public com.article.domain.CmsArticle getCourse () {
		return course;
	}

	/**
	 * Set the value related to the column: COURSE_ID
	 * @param course the COURSE_ID value
	 */
	public void setCourse (com.article.domain.CmsArticle course) {
		this.course = course;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.CmsStudyRecord)) return false;
		else {
			com.article.domain.CmsStudyRecord cmsStudyRecord = (com.article.domain.CmsStudyRecord) obj;
			if (null == this.getId() || null == cmsStudyRecord.getId()) return false;
			else return (this.getId().equals(cmsStudyRecord.getId()));
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
		builder.append(lastStudyTime);
		builder.append(totalHour);
		builder.append(studyTimes);
		return builder.toString();
	}


}