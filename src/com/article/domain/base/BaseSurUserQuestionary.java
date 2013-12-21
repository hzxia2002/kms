package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sur_user_questionary table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 用户调查表
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : sur_user_questionary
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="sur_user_questionary"
 */

public abstract class BaseSurUserQuestionary  implements Serializable{

	public static String REF = "SurUserQuestionary";
	public static String PROP_END_TIME = "endTime";
	public static String PROP_START_TIME = "startTime";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_ANSWER_TIME = "answerTime";
	public static String PROP_USER = "user";
	public static String PROP_QUESTIONARY = "questionary";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseSurUserQuestionary () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSurUserQuestionary (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*START_TIME*/
    /*起始时间*/
	private java.sql.Timestamp startTime;
	
    /*END_TIME*/
    /*结束时间*/
	private java.sql.Timestamp endTime;
	
    /*ANSWER_TIME*/
    /*答题时间*/
	private java.sql.Timestamp answerTime;
	
    /*CREATE_TIME*/
    /*创建时间*/
	private java.sql.Timestamp createTime;
	
    /*CREATE_USER*/
    /*创建人(记录帐号）*/
	private String createUser;
	
    /*UPDATE_TIME*/
    /*更新时间*/
	private java.sql.Timestamp updateTime;
	
    /*UPDATE_USER*/
    /*更新人(记录帐号）*/
	private String updateUser;
	

	// many to one
	private com.article.domain.SurQuestionary questionary;
	private com.comet.system.domain.SysUser user;



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
	 * Return the value associated with the column: START_TIME
	 */
	public java.sql.Timestamp getStartTime () {
		return startTime;
	}

	/**
	 * Set the value related to the column: START_TIME
	 * @param startTime the START_TIME value
	 */
	public void setStartTime (java.sql.Timestamp startTime) {
		this.startTime = startTime;
	}


	/**
	 * Return the value associated with the column: END_TIME
	 */
	public java.sql.Timestamp getEndTime () {
		return endTime;
	}

	/**
	 * Set the value related to the column: END_TIME
	 * @param endTime the END_TIME value
	 */
	public void setEndTime (java.sql.Timestamp endTime) {
		this.endTime = endTime;
	}


	/**
	 * Return the value associated with the column: ANSWER_TIME
	 */
	public java.sql.Timestamp getAnswerTime () {
		return answerTime;
	}

	/**
	 * Set the value related to the column: ANSWER_TIME
	 * @param answerTime the ANSWER_TIME value
	 */
	public void setAnswerTime (java.sql.Timestamp answerTime) {
		this.answerTime = answerTime;
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
	 * Return the value associated with the column: QUESTIONARY_ID
	 */
	public com.article.domain.SurQuestionary getQuestionary () {
		return questionary;
	}

	/**
	 * Set the value related to the column: QUESTIONARY_ID
	 * @param questionary the QUESTIONARY_ID value
	 */
	public void setQuestionary (com.article.domain.SurQuestionary questionary) {
		this.questionary = questionary;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.SurUserQuestionary)) return false;
		else {
			com.article.domain.SurUserQuestionary surUserQuestionary = (com.article.domain.SurUserQuestionary) obj;
			if (null == this.getId() || null == surUserQuestionary.getId()) return false;
			else return (this.getId().equals(surUserQuestionary.getId()));
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
		builder.append(startTime);
		builder.append(endTime);
		builder.append(answerTime);
		builder.append(createTime);
		builder.append(createUser);
		builder.append(updateTime);
		builder.append(updateUser);
		return builder.toString();
	}


}