package com.article.domain.base;

import com.article.domain.ExaPaper;
import com.comet.system.domain.SysUser;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the exa_paper_usergroup table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : exa_paper_usergroup
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="exa_paper_usergroup"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseExaPaperUsergroup  implements Serializable {

	public static String REF = "ExaPaperUsergroup";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_PAPER_ID = "paperId";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_ID = "id";
	public static String PROP_USER_ID = "userId";
	public static String PROP_UPDATE_USER = "updateUser";
	public static String PROP_ROLE_ID = "roleId";


	// constructors
	public BaseExaPaperUsergroup () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseExaPaperUsergroup (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*PAPER_ID*/
    /*�Ծ�ID*/
//	private Long paperId;
	
    /*ROLE_ID*/
    /*��ɫID*/
	private Long roleId;
	
    /*USER_ID*/
    /*�û�ID*/
//	private Long userId;
	
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


	private ExaPaper paper;
	private SysUser user;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public ExaPaper getPaper() {
		return paper;
	}

	public void setPaper(ExaPaper paper) {
		this.paper = paper;
	}

	/**
	 * Return the value associated with the column: PAPER_ID
	 */
//	public Long getPaperId () {
//		return paperId;
//	}

	/**
	 * Set the value related to the column: PAPER_ID
	 * @param paperId the PAPER_ID value
	 */
//	public void setPaperId (Long paperId) {
//		this.paperId = paperId;
//	}


	/**
	 * Return the value associated with the column: ROLE_ID
	 */
	public Long getRoleId () {
		return roleId;
	}

	/**
	 * Set the value related to the column: ROLE_ID
	 * @param roleId the ROLE_ID value
	 */
	public void setRoleId (Long roleId) {
		this.roleId = roleId;
	}


	/**
	 * Return the value associated with the column: USER_ID
	 */
//	public Long getUserId () {
//		return userId;
//	}

	/**
	 * Set the value related to the column: USER_ID
	 * @param userId the USER_ID value
	 */
//	public void setUserId (Long userId) {
//		this.userId = userId;
//	}


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
		if (!(obj instanceof com.article.domain.ExaPaperUsergroup)) return false;
		else {
			com.article.domain.ExaPaperUsergroup exaPaperUsergroup = (com.article.domain.ExaPaperUsergroup) obj;
			if (null == this.getId() || null == exaPaperUsergroup.getId()) return false;
			else return (this.getId().equals(exaPaperUsergroup.getId()));
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
		builder.append(paper);
		builder.append(roleId);
		builder.append(user);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}