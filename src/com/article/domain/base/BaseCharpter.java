package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the charpter table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 题目分类
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : charpter
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="charpter"
 */

public abstract class BaseCharpter  implements Serializable{

	public static String REF = "Charpter";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_INDEX_NO = "indexNo";
	public static String PROP_QUESTIONARY = "questionary";
	public static String PROP_ID = "id";


	// constructors
	public BaseCharpter () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCharpter (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*INDEX_NO*/
    /*排序号*/
	private Long indexNo;
	
    /*DESCRIPTION*/
    /*描述*/
	private String description;
	

	// many to one
	private com.article.domain.Questionary questionary;



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
	 * Return the value associated with the column: INDEX_NO
	 */
	public Long getIndexNo () {
		return indexNo;
	}

	/**
	 * Set the value related to the column: INDEX_NO
	 * @param indexNo the INDEX_NO value
	 */
	public void setIndexNo (Long indexNo) {
		this.indexNo = indexNo;
	}


	/**
	 * Return the value associated with the column: DESCRIPTION
	 */
	public String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: DESCRIPTION
	 * @param description the DESCRIPTION value
	 */
	public void setDescription (String description) {
		this.description = description;
	}


	/**
	 * Return the value associated with the column: QUESTIONARY_ID
	 */
	public com.article.domain.Questionary getQuestionary () {
		return questionary;
	}

	/**
	 * Set the value related to the column: QUESTIONARY_ID
	 * @param questionary the QUESTIONARY_ID value
	 */
	public void setQuestionary (com.article.domain.Questionary questionary) {
		this.questionary = questionary;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.Charpter)) return false;
		else {
			com.article.domain.Charpter charpter = (com.article.domain.Charpter) obj;
			if (null == this.getId() || null == charpter.getId()) return false;
			else return (this.getId().equals(charpter.getId()));
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
		builder.append(indexNo);
		builder.append(description);
		return builder.toString();
	}


}