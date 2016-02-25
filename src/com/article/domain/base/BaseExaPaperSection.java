package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the exa_paper_section table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : �Ծ��½�
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : exa_paper_section
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="exa_paper_section"
 */

public abstract class BaseExaPaperSection  implements Serializable {

	public static String REF = "ExaPaperSection";
	public static String PROP_PAPER_ID = "paperId";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_PER_SCORE = "perScore";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_QUESTION_TYPE = "questionType";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_DB_ID = "dbId";
	public static String PROP_QUESTION_LEVEL = "questionLevel";
	public static String PROP_ID = "id";
	public static String PROP_SECTION_NAME = "sectionName";
	public static String PROP_QUESTION_NUMS = "questionNums";
	public static String PROP_REMARK = "remark";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseExaPaperSection () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseExaPaperSection (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*PAPER_ID*/
    /*�Ծ�ID*/
	private Long paperId;
	
    /*DB_ID*/
    /*���ID*/
	private Long dbId;
	
    /*SECTION_NAME*/
    /*�½�����*/
	private String sectionName;
	
    /*PER_SCORE*/
    /*ÿ�����*/
	private Long perScore;
	
    /*QUESTION_NUMS*/
    /*��Ŀ����*/
	private Long questionNums;
	
    /*QUESTION_LEVEL*/
    /*�������׳̶�*/
	private Long questionLevel;
	
    /*QUESTION_TYPE*/
    /*��������*/
	private Long questionType;
	
    /*REMARK*/
    /*����*/
	private String remark;
	
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
	 * Return the value associated with the column: PAPER_ID
	 */
	public Long getPaperId () {
		return paperId;
	}

	/**
	 * Set the value related to the column: PAPER_ID
	 * @param paperId the PAPER_ID value
	 */
	public void setPaperId (Long paperId) {
		this.paperId = paperId;
	}


	/**
	 * Return the value associated with the column: DB_ID
	 */
	public Long getDbId () {
		return dbId;
	}

	/**
	 * Set the value related to the column: DB_ID
	 * @param dbId the DB_ID value
	 */
	public void setDbId (Long dbId) {
		this.dbId = dbId;
	}


	/**
	 * Return the value associated with the column: SECTION_NAME
	 */
	public String getSectionName () {
		return sectionName;
	}

	/**
	 * Set the value related to the column: SECTION_NAME
	 * @param sectionName the SECTION_NAME value
	 */
	public void setSectionName (String sectionName) {
		this.sectionName = sectionName;
	}


	/**
	 * Return the value associated with the column: PER_SCORE
	 */
	public Long getPerScore () {
		return perScore;
	}

	/**
	 * Set the value related to the column: PER_SCORE
	 * @param perScore the PER_SCORE value
	 */
	public void setPerScore (Long perScore) {
		this.perScore = perScore;
	}


	/**
	 * Return the value associated with the column: QUESTION_NUMS
	 */
	public Long getQuestionNums () {
		return questionNums;
	}

	/**
	 * Set the value related to the column: QUESTION_NUMS
	 * @param questionNums the QUESTION_NUMS value
	 */
	public void setQuestionNums (Long questionNums) {
		this.questionNums = questionNums;
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
		if (!(obj instanceof com.article.domain.ExaPaperSection)) return false;
		else {
			com.article.domain.ExaPaperSection exaPaperSection = (com.article.domain.ExaPaperSection) obj;
			if (null == this.getId() || null == exaPaperSection.getId()) return false;
			else return (this.getId().equals(exaPaperSection.getId()));
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
		builder.append(paperId);
		builder.append(dbId);
		builder.append(sectionName);
		builder.append(perScore);
		builder.append(questionNums);
		builder.append(questionLevel);
		builder.append(questionType);
		builder.append(remark);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}