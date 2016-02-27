package com.article.domain.base;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the exa_question table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : ����
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : exa_question
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="exa_question"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseExaQuestion  implements Serializable {

	public static String REF = "ExaQuestion";
	public static String PROP_DB = "db";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_QUESTION_FROM = "questionFrom";
	public static String PROP_STATUS = "status";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_QUESTION_TYPE = "questionType";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_POST_TIME = "postTime";
	public static String PROP_SKEY = "skey";
	public static String PROP_ID = "id";
	public static String PROP_QUESTION_LEVEL = "questionLevel";
	public static String PROP_KEY_DESC = "keyDesc";
	public static String PROP_CONTENT = "content";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseExaQuestion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseExaQuestion (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*QUESTION_TYPE*/
	private Long questionType;
	
    /*QUESTION_LEVEL*/
    /*���׳̶�*/
	private Long questionLevel;
	
    /*QUESTION_FROM*/
    /*��Ŀ��Դ*/
	private Long questionFrom;
	
    /*STATUS*/
    /*״̬*/
	private String status;
	
    /*CONTENT*/
    /*����*/
	private String content;
	
    /*POST_TIME*/
    /*����ʱ��*/
	private java.sql.Timestamp postTime;
	
    /*SKEY*/
    /*ѡ�����*/
	private String skey;
	
    /*KEY_DESC*/
    /*����������*/
	private String keyDesc;
	
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
	

	// many to one
	private com.article.domain.ExaQuestionDb db;



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
	 * Return the value associated with the column: QUESTION_TYPE
	 */
	public Long getQuestionType () {
		return questionType;
	}

	/**
	 * Set the value related to the column: QUESTION_TYPE
	 * @param questionType the QUESTION_TYPE value
	 */
	public void setQuestionType (Long questionType) {
		this.questionType = questionType;
	}


	/**
	 * Return the value associated with the column: QUESTION_LEVEL
	 */
	public Long getQuestionLevel () {
		return questionLevel;
	}

	/**
	 * Set the value related to the column: QUESTION_LEVEL
	 * @param questionLevel the QUESTION_LEVEL value
	 */
	public void setQuestionLevel (Long questionLevel) {
		this.questionLevel = questionLevel;
	}


	/**
	 * Return the value associated with the column: QUESTION_FROM
	 */
	public Long getQuestionFrom () {
		return questionFrom;
	}

	/**
	 * Set the value related to the column: QUESTION_FROM
	 * @param questionFrom the QUESTION_FROM value
	 */
	public void setQuestionFrom (Long questionFrom) {
		this.questionFrom = questionFrom;
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
	 * Return the value associated with the column: POST_TIME
	 */
	public java.sql.Timestamp getPostTime () {
		return postTime;
	}

	/**
	 * Set the value related to the column: POST_TIME
	 * @param postTime the POST_TIME value
	 */
	public void setPostTime (java.sql.Timestamp postTime) {
		this.postTime = postTime;
	}


	/**
	 * Return the value associated with the column: SKEY
	 */
	public String getSkey () {
		return skey;
	}

	/**
	 * Set the value related to the column: SKEY
	 * @param skey the SKEY value
	 */
	public void setSkey (String skey) {
		this.skey = skey;
	}


	/**
	 * Return the value associated with the column: KEY_DESC
	 */
	public String getKeyDesc () {
		return keyDesc;
	}

	/**
	 * Set the value related to the column: KEY_DESC
	 * @param keyDesc the KEY_DESC value
	 */
	public void setKeyDesc (String keyDesc) {
		this.keyDesc = keyDesc;
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
	 * Return the value associated with the column: DB_ID
	 */
	public com.article.domain.ExaQuestionDb getDb () {
		return db;
	}

	/**
	 * Set the value related to the column: DB_ID
	 * @param db the DB_ID value
	 */
	public void setDb (com.article.domain.ExaQuestionDb db) {
		this.db = db;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.ExaQuestion)) return false;
		else {
			com.article.domain.ExaQuestion exaQuestion = (com.article.domain.ExaQuestion) obj;
			if (null == this.getId() || null == exaQuestion.getId()) return false;
			else return (this.getId().equals(exaQuestion.getId()));
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
		builder.append(questionType);
		builder.append(questionLevel);
		builder.append(questionFrom);
		builder.append(status);
		builder.append(content);
		builder.append(postTime);
		builder.append(skey);
		builder.append(keyDesc);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}