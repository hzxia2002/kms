package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the question table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 调查题目
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : question
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="question"
 */

public abstract class BaseQuestion  implements Serializable{

	public static String REF = "Question";
	public static String PROP_STORE = "store";
	public static String PROP_TYPE = "type";
	public static String PROP_REMARK = "remark";
	public static String PROP_TITLE = "title";
	public static String PROP_ID = "id";


	// constructors
	public BaseQuestion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseQuestion (Long id) {
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
	private com.article.domain.QestionStore store;



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
	 * Return the value associated with the column: STORE_ID
	 */
	public com.article.domain.QestionStore getStore () {
		return store;
	}

	/**
	 * Set the value related to the column: STORE_ID
	 * @param store the STORE_ID value
	 */
	public void setStore (com.article.domain.QestionStore store) {
		this.store = store;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.Question)) return false;
		else {
			com.article.domain.Question question = (com.article.domain.Question) obj;
			if (null == this.getId() || null == question.getId()) return false;
			else return (this.getId().equals(question.getId()));
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