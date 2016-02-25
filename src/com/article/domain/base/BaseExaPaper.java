package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the exa_paper table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : �Ծ�
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : exa_paper
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="exa_paper"
 */

public abstract class BaseExaPaper  implements Serializable {

	public static String REF = "ExaPaper";
	public static String PROP_IS_RAND_PAPER = "isRandPaper";
	public static String PROP_TOTAL_SCORE = "totalScore";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_PAPER_NAME = "paperName";
	public static String PROP_PAPER_MINUTE = "paperMinute";
	public static String PROP_STATUS = "status";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_SHOW_SCORE_TIME = "showScoreTime";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_POST_TIME = "postTime";
	public static String PROP_ID = "id";
	public static String PROP_END_TIME = "endTime";
	public static String PROP_START_TIME = "startTime";
	public static String PROP_REMARK = "remark";
	public static String PROP_UPDATE_USER = "updateUser";
	public static String PROP_QUESTION_ORDER_TYPE = "questionOrderType";


	// constructors
	public BaseExaPaper () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseExaPaper (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*PAPER_NAME*/
    /*�Ծ�����*/
	private String paperName;
	
    /*START_TIME*/
    /*��ʼʱ��*/
	private String startTime;
	
    /*END_TIME*/
    /*����ʱ��*/
	private String endTime;
	
    /*PAPER_MINUTE*/
    /*����ʱ��*/
	private Long paperMinute;
	
    /*TOTAL_SCORE*/
    /*�Ծ��ܷ�*/
	private Long totalScore;
	
    /*QUESTION_ORDER_TYPE*/
    /*ʱ����������*/
	private String questionOrderType;
	
    /*POST_TIME*/
    /*����ʱ��*/
	private java.sql.Timestamp postTime;
	
    /*SHOW_SCORE_TIME*/
    /*��ʾ����ʱ��*/
	private java.sql.Timestamp showScoreTime;
	
    /*IS_RAND_PAPER*/
    /*�Ƿ����ʱ��*/
	private Long isRandPaper;
	
    /*STATUS*/
    /*�Ծ�״̬(1���ţ�-1������)*/
	private String status;
	
    /*REMARK*/
    /*��ע*/
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
	 * Return the value associated with the column: PAPER_NAME
	 */
	public String getPaperName () {
		return paperName;
	}

	/**
	 * Set the value related to the column: PAPER_NAME
	 * @param paperName the PAPER_NAME value
	 */
	public void setPaperName (String paperName) {
		this.paperName = paperName;
	}


	/**
	 * Return the value associated with the column: START_TIME
	 */
	public String getStartTime () {
		return startTime;
	}

	/**
	 * Set the value related to the column: START_TIME
	 * @param startTime the START_TIME value
	 */
	public void setStartTime (String startTime) {
		this.startTime = startTime;
	}


	/**
	 * Return the value associated with the column: END_TIME
	 */
	public String getEndTime () {
		return endTime;
	}

	/**
	 * Set the value related to the column: END_TIME
	 * @param endTime the END_TIME value
	 */
	public void setEndTime (String endTime) {
		this.endTime = endTime;
	}


	/**
	 * Return the value associated with the column: PAPER_MINUTE
	 */
	public Long getPaperMinute () {
		return paperMinute;
	}

	/**
	 * Set the value related to the column: PAPER_MINUTE
	 * @param paperMinute the PAPER_MINUTE value
	 */
	public void setPaperMinute (Long paperMinute) {
		this.paperMinute = paperMinute;
	}


	/**
	 * Return the value associated with the column: TOTAL_SCORE
	 */
	public Long getTotalScore () {
		return totalScore;
	}

	/**
	 * Set the value related to the column: TOTAL_SCORE
	 * @param totalScore the TOTAL_SCORE value
	 */
	public void setTotalScore (Long totalScore) {
		this.totalScore = totalScore;
	}


	/**
	 * Return the value associated with the column: QUESTION_ORDER_TYPE
	 */
	public String getQuestionOrderType () {
		return questionOrderType;
	}

	/**
	 * Set the value related to the column: QUESTION_ORDER_TYPE
	 * @param questionOrderType the QUESTION_ORDER_TYPE value
	 */
	public void setQuestionOrderType (String questionOrderType) {
		this.questionOrderType = questionOrderType;
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
	 * Return the value associated with the column: SHOW_SCORE_TIME
	 */
	public java.sql.Timestamp getShowScoreTime () {
		return showScoreTime;
	}

	/**
	 * Set the value related to the column: SHOW_SCORE_TIME
	 * @param showScoreTime the SHOW_SCORE_TIME value
	 */
	public void setShowScoreTime (java.sql.Timestamp showScoreTime) {
		this.showScoreTime = showScoreTime;
	}


	/**
	 * Return the value associated with the column: IS_RAND_PAPER
	 */
	public Long getIsRandPaper () {
		return isRandPaper;
	}

	/**
	 * Set the value related to the column: IS_RAND_PAPER
	 * @param isRandPaper the IS_RAND_PAPER value
	 */
	public void setIsRandPaper (Long isRandPaper) {
		this.isRandPaper = isRandPaper;
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
		if (!(obj instanceof com.article.domain.ExaPaper)) return false;
		else {
			com.article.domain.ExaPaper exaPaper = (com.article.domain.ExaPaper) obj;
			if (null == this.getId() || null == exaPaper.getId()) return false;
			else return (this.getId().equals(exaPaper.getId()));
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
		builder.append(paperName);
		builder.append(startTime);
		builder.append(endTime);
		builder.append(paperMinute);
		builder.append(totalScore);
		builder.append(questionOrderType);
		builder.append(postTime);
		builder.append(showScoreTime);
		builder.append(isRandPaper);
		builder.append(status);
		builder.append(remark);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}