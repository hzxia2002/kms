package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the exa_exam_info table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : ״̬��STATUS��
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : exa_exam_info
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="exa_exam_info"
 */

public abstract class BaseExaExamInfo  implements Serializable {

	public static String REF = "ExaExamInfo";
	public static String PROP_IP = "ip";
	public static String PROP_PAPER_ID = "paperId";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_USER_ID = "userId";
	public static String PROP_STATUS = "status";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_END_TIME = "endTime";
	public static String PROP_ID = "id";
	public static String PROP_START_TIME = "startTime";
	public static String PROP_REMARK = "remark";
	public static String PROP_SCORE = "score";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseExaExamInfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseExaExamInfo (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*USER_ID*/
    /*�û�ID*/
	private Long userId;
	
    /*PAPER_ID*/
    /*�Ծ�ID*/
	private Long paperId;
	
    /*START_TIME*/
    /*��ʼ����ʱ��*/
	private java.sql.Timestamp startTime;
	
    /*END_TIME*/
    /*����ʱ��*/
	private java.sql.Timestamp endTime;
	
    /*IP*/
    /*IP*/
	private String ip;
	
    /*SCORE*/
    /*����*/
	private Long score;
	
    /*STATUS*/
    /*״̬��1�Ѵ��0δ���*/
	private String status;
	
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
	 * Return the value associated with the column: USER_ID
	 */
	public Long getUserId () {
		return userId;
	}

	/**
	 * Set the value related to the column: USER_ID
	 * @param userId the USER_ID value
	 */
	public void setUserId (Long userId) {
		this.userId = userId;
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
	 * Return the value associated with the column: IP
	 */
	public String getIp () {
		return ip;
	}

	/**
	 * Set the value related to the column: IP
	 * @param ip the IP value
	 */
	public void setIp (String ip) {
		this.ip = ip;
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
		if (!(obj instanceof com.article.domain.ExaExamInfo)) return false;
		else {
			com.article.domain.ExaExamInfo exaExamInfo = (com.article.domain.ExaExamInfo) obj;
			if (null == this.getId() || null == exaExamInfo.getId()) return false;
			else return (this.getId().equals(exaExamInfo.getId()));
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
		builder.append(userId);
		builder.append(paperId);
		builder.append(startTime);
		builder.append(endTime);
		builder.append(ip);
		builder.append(score);
		builder.append(status);
		builder.append(remark);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}