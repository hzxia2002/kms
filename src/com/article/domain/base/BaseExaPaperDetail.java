package com.article.domain.base;

import com.article.domain.ExaQuestion;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.io.Serializable;


/**
 * This is an object that contains data related to the exa_paper_detail table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : �Ծ���ϸ
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : exa_paper_detail
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="exa_paper_detail"
 */

public abstract class BaseExaPaperDetail  implements Serializable {

	public static String REF = "ExaPaperDetail";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_ORDER_NO = "orderNo";
	public static String PROP_PAPER_ID = "paperId";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_ID = "id";
	public static String PROP_QUESTION_ID = "questionId";
	public static String PROP_SECTION_ID = "sectionId";
	public static String PROP_SCORE = "score";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseExaPaperDetail () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseExaPaperDetail (Long id) {
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
	
    /*QUESTION_ID*/
    /*��ĿID*/
	private ExaQuestion question;
	
    /*SECTION_ID*/
    /*�½�ID*/
	private Long sectionId;
	
    /*SCORE*/
    /*����*/
	private Long score;
	
    /*ORDER_NO*/
    /*����*/
	private Long orderNo;
	
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
	 * Return the value associated with the column: QUESTION_ID
	 */
	public ExaQuestion getQuestion () {
		return question;
	}

	/**
	 * Set the value related to the column: QUESTION_ID
	 * @param exaQuestion the QUESTION_ID value
	 */
	public void setQuestion (ExaQuestion exaQuestion) {
		this.question = exaQuestion;
	}


	/**
	 * Return the value associated with the column: SECTION_ID
	 */
	public Long getSectionId () {
		return sectionId;
	}

	/**
	 * Set the value related to the column: SECTION_ID
	 * @param sectionId the SECTION_ID value
	 */
	public void setSectionId (Long sectionId) {
		this.sectionId = sectionId;
	}


	/**
	 * Return the value associated with the column: SCORE
	 */
	public Long getScore () {
		return score;
	}

	/**
	 * Set the value related to the column: SCORE
	 * @param score the SCORE value
	 */
	public void setScore (Long score) {
		this.score = score;
	}


	/**
	 * Return the value associated with the column: ORDER_NO
	 */
	public Long getOrderNo () {
		return orderNo;
	}

	/**
	 * Set the value related to the column: ORDER_NO
	 * @param orderNo the ORDER_NO value
	 */
	public void setOrderNo (Long orderNo) {
		this.orderNo = orderNo;
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
		if (!(obj instanceof com.article.domain.ExaPaperDetail)) return false;
		else {
			com.article.domain.ExaPaperDetail exaPaperDetail = (com.article.domain.ExaPaperDetail) obj;
			if (null == this.getId() || null == exaPaperDetail.getId()) return false;
			else return (this.getId().equals(exaPaperDetail.getId()));
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
		builder.append(question);
		builder.append(sectionId);
		builder.append(score);
		builder.append(orderNo);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}