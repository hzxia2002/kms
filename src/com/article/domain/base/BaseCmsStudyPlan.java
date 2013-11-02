package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_study_plan table.
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
 *  table="cms_study_plan"
 */

public abstract class BaseCmsStudyPlan  implements Serializable{

	public static String REF = "CmsStudyPlan";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_LAST_STUDY_TIME = "lastStudyTime";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_LAST_STUDY_DURATION = "lastStudyDuration";
	public static String PROP_USER = "user";
	public static String PROP_STUDY_TIMES = "studyTimes";
	public static String PROP_STUDY_DURATION = "studyDuration";
	public static String PROP_COURSE = "course";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseCmsStudyPlan () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsStudyPlan (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
	private java.sql.Timestamp lastStudyTime;
	
	private Long lastStudyDuration;
	
	private Long studyDuration;
	
	private Long studyTimes;
	
	private java.sql.Timestamp createTime;
	
	private String createUser;
	
	private java.sql.Timestamp updateTime;
	
	private String updateUser;
	

	// many to one
	private com.comet.system.domain.SysUser user;
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
	 * Return the value associated with the column: LAST_STUDY_DURATION
	 */
	public Long getLastStudyDuration () {
		return lastStudyDuration;
	}

	/**
	 * Set the value related to the column: LAST_STUDY_DURATION
	 * @param lastStudyDuration the LAST_STUDY_DURATION value
	 */
	public void setLastStudyDuration (Long lastStudyDuration) {
		this.lastStudyDuration = lastStudyDuration;
	}


	/**
	 * Return the value associated with the column: STUDY_DURATION
	 */
	public Long getStudyDuration () {
		return studyDuration;
	}

	/**
	 * Set the value related to the column: STUDY_DURATION
	 * @param studyDuration the STUDY_DURATION value
	 */
	public void setStudyDuration (Long studyDuration) {
		this.studyDuration = studyDuration;
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
		if (!(obj instanceof com.article.domain.CmsStudyPlan)) return false;
		else {
			com.article.domain.CmsStudyPlan cmsStudyPlan = (com.article.domain.CmsStudyPlan) obj;
			if (null == this.getId() || null == cmsStudyPlan.getId()) return false;
			else return (this.getId().equals(cmsStudyPlan.getId()));
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
		builder.append(lastStudyDuration);
		builder.append(studyDuration);
		builder.append(studyTimes);
		builder.append(createTime);
		builder.append(createUser);
		builder.append(updateTime);
		builder.append(updateUser);
		return builder.toString();
	}


}