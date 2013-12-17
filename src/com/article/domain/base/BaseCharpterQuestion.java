package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the charpter_question table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 试卷问题
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : charpter_question
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="charpter_question"
 */

public abstract class BaseCharpterQuestion  implements Serializable{

	public static String REF = "CharpterQuestion";
	public static String PROP_QUESTION = "question";
	public static String PROP_CHARPTER = "charpter";
	public static String PROP_ID = "id";


	// constructors
	public BaseCharpterQuestion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCharpterQuestion (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// many to one
	private com.article.domain.Question question;
	private com.article.domain.Charpter charpter;



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
	 * Return the value associated with the column: CHARPTER_ID
	 */
	public com.article.domain.Charpter getCharpter () {
		return charpter;
	}

	/**
	 * Set the value related to the column: CHARPTER_ID
	 * @param charpter the CHARPTER_ID value
	 */
	public void setCharpter (com.article.domain.Charpter charpter) {
		this.charpter = charpter;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.CharpterQuestion)) return false;
		else {
			com.article.domain.CharpterQuestion charpterQuestion = (com.article.domain.CharpterQuestion) obj;
			if (null == this.getId() || null == charpterQuestion.getId()) return false;
			else return (this.getId().equals(charpterQuestion.getId()));
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
		return builder.toString();
	}


}