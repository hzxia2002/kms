package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the exa_question_options table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : ����ѡ��
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : exa_question_options
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="exa_question_options"
 */

public abstract class BaseExaQuestionOptions  implements Serializable {

	public static String REF = "ExaQuestionOptions";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_OPTION_OPTION = "optionOption";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_OPTION_KEY = "optionKey";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_ID = "id";
	public static String PROP_QUESTION_ID = "questionId";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseExaQuestionOptions () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseExaQuestionOptions (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*QUESTION_ID*/
    /*��ĿID*/
	private Long questionId;
	
    /*OPTION_KEY*/
    /*ѡ��*/
	private String optionKey;
	
    /*OPTION_OPTION*/
    /*ѡ������*/
	private String optionOption;
	
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
	 * Return the value associated with the column: QUESTION_ID
	 */
	public Long getQuestionId () {
		return questionId;
	}

	/**
	 * Set the value related to the column: QUESTION_ID
	 * @param questionId the QUESTION_ID value
	 */
	public void setQuestionId (Long questionId) {
		this.questionId = questionId;
	}


	/**
	 * Return the value associated with the column: OPTION_KEY
	 */
	public String getOptionKey () {
		return optionKey;
	}

	/**
	 * Set the value related to the column: OPTION_KEY
	 * @param optionKey the OPTION_KEY value
	 */
	public void setOptionKey (String optionKey) {
		this.optionKey = optionKey;
	}


	/**
	 * Return the value associated with the column: OPTION_OPTION
	 */
	public String getOptionOption () {
		return optionOption;
	}

	/**
	 * Set the value related to the column: OPTION_OPTION
	 * @param optionOption the OPTION_OPTION value
	 */
	public void setOptionOption (String optionOption) {
		this.optionOption = optionOption;
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
		if (!(obj instanceof com.article.domain.ExaQuestionOptions)) return false;
		else {
			com.article.domain.ExaQuestionOptions exaQuestionOptions = (com.article.domain.ExaQuestionOptions) obj;
			if (null == this.getId() || null == exaQuestionOptions.getId()) return false;
			else return (this.getId().equals(exaQuestionOptions.getId()));
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
		builder.append(questionId);
		builder.append(optionKey);
		builder.append(optionOption);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}