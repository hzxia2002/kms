package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sur_question table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 调查题目
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : sur_question
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="sur_question"
 */

public abstract class BaseSurQuestion  implements Serializable{

	public static String REF = "SurQuestion";
	public static String PROP_TYPE = "type";
	public static String PROP_REMARK = "remark";
	public static String PROP_TITLE = "title";
	public static String PROP_QUESTIONARY = "questionary";
	public static String PROP_ID = "id";


	// constructors
	public BaseSurQuestion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSurQuestion (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*TITLE*/
    /*题干*/
	private String title;
	
    /*TYPE*/
    /*题型*/
	private String type;
	
    /*REMARK*/
    /*备注*/
	private String remark;
	

	// many to one
	private com.article.domain.SurQuestionary questionary;



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
	 * Return the value associated with the column: TYPE
	 */
	public String getType () {
		return type;
	}

	/**
	 * Set the value related to the column: TYPE
	 * @param type the TYPE value
	 */
	public void setType (String type) {
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
	 * Return the value associated with the column: QUESTIONARY_ID
	 */
	public com.article.domain.SurQuestionary getQuestionary () {
		return questionary;
	}

	/**
	 * Set the value related to the column: QUESTIONARY_ID
	 * @param questionary the QUESTIONARY_ID value
	 */
	public void setQuestionary (com.article.domain.SurQuestionary questionary) {
		this.questionary = questionary;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.SurQuestion)) return false;
		else {
			com.article.domain.SurQuestion surQuestion = (com.article.domain.SurQuestion) obj;
			if (null == this.getId() || null == surQuestion.getId()) return false;
			else return (this.getId().equals(surQuestion.getId()));
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
		builder.append(type);
		builder.append(remark);
		return builder.toString();
	}


}