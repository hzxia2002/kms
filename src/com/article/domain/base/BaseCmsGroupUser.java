package com.article.domain.base;

import com.article.domain.CmsGroup;
import com.article.domain.CmsGroupUser;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cmc_group_user table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 用户组关联
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : cmc_group_user
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="cmc_group_user"
 */

public abstract class BaseCmsGroupUser implements Serializable{

	public static String REF = "CmsGroupUser";
	public static String PROP_USER = "user";
	public static String PROP_ID = "id";
	public static String PROP_GROUP = "group";


	// constructors
	public BaseCmsGroupUser() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsGroupUser(Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// many to one
	private CmsGroup group;
	private com.comet.system.domain.SysUser user;



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
	 * Return the value associated with the column: GROUP_ID
	 */
	public CmsGroup getGroup () {
		return group;
	}

	/**
	 * Set the value related to the column: GROUP_ID
	 * @param group the GROUP_ID value
	 */
	public void setGroup (CmsGroup group) {
		this.group = group;
	}


	/**
	 * Return the value associated with the column: USER_ID
	 */
	public  com.comet.system.domain.SysUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: USER_ID
	 * @param user the USER_ID value
	 */
	public void setUser ( com.comet.system.domain.SysUser user) {
		this.user = user;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof CmsGroupUser)) return false;
		else {
			CmsGroupUser cmsGroupUser = (CmsGroupUser) obj;
			if (null == this.getId() || null == cmsGroupUser.getId()) return false;
			else return (this.getId().equals(cmsGroupUser.getId()));
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