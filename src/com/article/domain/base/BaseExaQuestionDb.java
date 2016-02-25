package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the exa_question_db table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : ���
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : exa_question_db
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="exa_question_db"
 */

public abstract class BaseExaQuestionDb  implements Serializable {

	public static String REF = "ExaQuestionDb";
	public static String PROP_STATUS = "status";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_DB_NAME = "dbName";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_ID = "id";
	public static String PROP_REMARK = "remark";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseExaQuestionDb () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseExaQuestionDb (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*DB_NAME*/
    /*�������*/
	private String dbName;
	
    /*REMARK*/
    /*��ע*/
	private String remark;
	
    /*STATUS*/
    /*״̬*/
	private String status;
	
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
	



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
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
	 * Return the value associated with the column: DB_NAME
	 */
	public String getDbName () {
		return dbName;
	}

	/**
	 * Set the value related to the column: DB_NAME
	 * @param dbName the DB_NAME value
	 */
	public void setDbName (String dbName) {
		this.dbName = dbName;
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
	 * Return the value associated with the column: STATUS
	 */
	public String getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: STATUS
	 * @param status the STATUS value
	 */
	public void setStatus (String status) {
		this.status = status;
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
		if (!(obj instanceof com.article.domain.ExaQuestionDb)) return false;
		else {
			com.article.domain.ExaQuestionDb exaQuestionDb = (com.article.domain.ExaQuestionDb) obj;
			if (null == this.getId() || null == exaQuestionDb.getId()) return false;
			else return (this.getId().equals(exaQuestionDb.getId()));
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
		builder.append(dbName);
		builder.append(remark);
		builder.append(status);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}