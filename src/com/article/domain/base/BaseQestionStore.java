package com.article.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the qestion_store table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 题库
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : question_store
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="qestion_store"
 */

public abstract class BaseQestionStore  implements Serializable{

	public static String REF = "QestionStore";
	public static String PROP_REMARK = "remark";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";


	// constructors
	public BaseQestionStore () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseQestionStore (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*NAME*/
    /*题库名称*/
	private String name;
	
    /*REMARK*/
    /*备注*/
	private String remark;
	



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
	 * Return the value associated with the column: NAME
	 */
	public String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: NAME
	 * @param name the NAME value
	 */
	public void setName (String name) {
		this.name = name;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.QestionStore)) return false;
		else {
			com.article.domain.QestionStore qestionStore = (com.article.domain.QestionStore) obj;
			if (null == this.getId() || null == qestionStore.getId()) return false;
			else return (this.getId().equals(qestionStore.getId()));
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
		builder.append(name);
		builder.append(remark);
		return builder.toString();
	}


}