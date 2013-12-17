package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the answer table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 调查结果
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : answer
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="answer"
 */

public abstract class BaseAnswer  implements Serializable{

	public static String REF = "Answer";
	public static String PROP_OPTION = "option";
	public static String PROP_USER = "user";
	public static String PROP_QUESTION = "question";
	public static String PROP_SCORE = "score";
	public static String PROP_ID = "id";
	public static String PROP_USER_QUESTIONARY = "userQuestionary";


	// constructors
	public BaseAnswer () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAnswer (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*SCORE*/
    /*分数*/
	private Long score;
	

	// many to one
	private com.article.domain.UserQuestionary userQuestionary;
	private com.article.domain.Question question;
	private com.comet.system.domain.SysUser user;
	private com.article.domain.Options option;



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
	 * Return the value associated with the column: USER_QUESTIONARY_ID
	 */
	public com.article.domain.UserQuestionary getUserQuestionary () {
		return userQuestionary;
	}

	/**
	 * Set the value related to the column: USER_QUESTIONARY_ID
	 * @param userQuestionary the USER_QUESTIONARY_ID value
	 */
	public void setUserQuestionary (com.article.domain.UserQuestionary userQuestionary) {
		this.userQuestionary = userQuestionary;
	}


	/**
	 * Return the value associated with the column: QUESTION_ID
	 */
	public com.article.domain.Question getQuestion () {
		return question;
	}

	/**
	 * Set the value related to the column: QUESTION_ID
	 * @param question the QUESTION_ID value
	 */
	public void setQuestion (com.article.domain.Question question) {
		this.question = question;
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
	 * Return the value associated with the column: OPTION_ID
	 */
	public com.article.domain.Options getOption () {
		return option;
	}

	/**
	 * Set the value related to the column: OPTION_ID
	 * @param option the OPTION_ID value
	 */
	public void setOption (com.article.domain.Options option) {
		this.option = option;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.Answer)) return false;
		else {
			com.article.domain.Answer answer = (com.article.domain.Answer) obj;
			if (null == this.getId() || null == answer.getId()) return false;
			else return (this.getId().equals(answer.getId()));
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
		builder.append(score);
		return builder.toString();
	}


}