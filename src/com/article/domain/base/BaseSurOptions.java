package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sur_options table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 选项
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : sur_options
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="sur_options"
 */

public abstract class BaseSurOptions  implements Serializable{

	public static String REF = "SurOptions";
	public static String PROP_INDEX_NO = "indexNo";
	public static String PROP_QUESTION = "question";
	public static String PROP_CONTENT = "content";
	public static String PROP_ID = "id";


	// constructors
	public BaseSurOptions () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSurOptions (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*CONTENT*/
    /*选项描述*/
	private String content;
	
    /*INDEX_NO*/
    /*编号*/
	private String indexNo;
	

	// many to one
	private com.article.domain.SurQuestion question;



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
	 * Return the value associated with the column: INDEX_NO
	 */
	public String getIndexNo () {
		return indexNo;
	}

	/**
	 * Set the value related to the column: INDEX_NO
	 * @param indexNo the INDEX_NO value
	 */
	public void setIndexNo (String indexNo) {
		this.indexNo = indexNo;
	}


	/**
	 * Return the value associated with the column: QUESTION_ID
	 */
	public com.article.domain.SurQuestion getQuestion () {
		return question;
	}

	/**
	 * Set the value related to the column: QUESTION_ID
	 * @param question the QUESTION_ID value
	 */
	public void setQuestion (com.article.domain.SurQuestion question) {
		this.question = question;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.SurOptions)) return false;
		else {
			com.article.domain.SurOptions surOptions = (com.article.domain.SurOptions) obj;
			if (null == this.getId() || null == surOptions.getId()) return false;
			else return (this.getId().equals(surOptions.getId()));
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
		builder.append(content);
		builder.append(indexNo);
		return builder.toString();
	}


}