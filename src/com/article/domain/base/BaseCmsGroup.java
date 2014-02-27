package com.article.domain.base;

import com.article.domain.CmsGroup;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cmc_group table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 用户组
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : cmc_group
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="cmc_group"
 */

public abstract class BaseCmsGroup implements Serializable{

	public static String REF = "CmsGroup";
	public static String PROP_CODE = "code";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";


	// constructors
	public BaseCmsGroup() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsGroup(Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*NAME*/
    /*名称*/
	private String name;
	
    /*CODE*/
    /*编码*/
	private String code;
	



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
	 * Return the value associated with the column: CODE
	 */
	public String getCode () {
		return code;
	}

	/**
	 * Set the value related to the column: CODE
	 * @param code the CODE value
	 */
	public void setCode (String code) {
		this.code = code;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof CmsGroup)) return false;
		else {
			CmsGroup cmsGroup = (CmsGroup) obj;
			if (null == this.getId() || null == cmsGroup.getId()) return false;
			else return (this.getId().equals(cmsGroup.getId()));
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
		builder.append(code);
		return builder.toString();
	}


}