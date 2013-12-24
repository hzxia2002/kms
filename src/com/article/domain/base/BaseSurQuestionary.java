package com.article.domain.base;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sur_questionary table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 调查表
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : sur_questionary
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="sur_questionary"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSurQuestionary  implements Serializable{

	public static String REF = "SurQuestionary";
	public static String PROP_TYPE = "type";
	public static String PROP_SPONSOR = "sponsor";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_REMARK = "remark";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_TITLE = "title";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseSurQuestionary () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSurQuestionary (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*TITLE*/
    /*问卷名称*/
	private String title;
	
    /*SPONSOR*/
    /*发起人*/
	private String sponsor;
	
    /*TYPE*/
    /*类型：0问卷,1试卷*/
	private Long type;
	
    /*REMARK*/
    /*备注*/
	private String remark;
	
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
	 * Return the value associated with the column: SPONSOR
	 */
	public String getSponsor () {
		return sponsor;
	}

	/**
	 * Set the value related to the column: SPONSOR
	 * @param sponsor the SPONSOR value
	 */
	public void setSponsor (String sponsor) {
		this.sponsor = sponsor;
	}


	/**
	 * Return the value associated with the column: TYPE
	 */
	public Long getType () {
		return type;
	}

	/**
	 * Set the value related to the column: TYPE
	 * @param type the TYPE value
	 */
	public void setType (Long type) {
		this.type = type;
	}


	/**
	 * Return the value associated with the column: REMARK
	 */
	public String getRemark () {
		return remark;
	}

	/**
	 * Set the value related to the column: REMARK
	 * @param remark the REMARK value
	 */
	public void setRemark (String remark) {
		this.remark = remark;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.SurQuestionary)) return false;
		else {
			com.article.domain.SurQuestionary surQuestionary = (com.article.domain.SurQuestionary) obj;
			if (null == this.getId() || null == surQuestionary.getId()) return false;
			else return (this.getId().equals(surQuestionary.getId()));
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
		builder.append(sponsor);
		builder.append(type);
		builder.append(remark);
		builder.append(createTime);
		builder.append(createUser);
		builder.append(updateTime);
		builder.append(updateUser);
		return builder.toString();
	}


}