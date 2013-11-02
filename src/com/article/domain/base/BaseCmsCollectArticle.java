package com.article.domain.base;

import com.article.domain.CmsArticle;
import com.comet.system.domain.SysUser;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_collect_article table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 收藏文章
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : cms_collect_article
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="cms_collect_article"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseCmsCollectArticle  implements Serializable{

	public static String REF = "CmsCollectArticle";
	public static String PROP_CATAGORY = "catagory";
	public static String PROP_REMARK = "remark";
	public static String PROP_USER_ID = "userId";
	public static String PROP_URL = "url";
	public static String PROP_ID = "id";


	// constructors
	public BaseCmsCollectArticle () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsCollectArticle (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

    /*URL*/
    /*链接地址*/
	private String url;

    /*REMARK*/
    /*备注名称*/
	private String remark;


	// many to one
	private com.article.domain.CmsCollectCatagory catagory;

	private CmsArticle article;
	private SysUser user;

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

    public CmsArticle getArticle() {
        return article;
    }

    public void setArticle(CmsArticle article) {
        this.article = article;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    /**
	 * Return the value associated with the column: URL
	 */
	public String getUrl () {
		return url;
	}

	/**
	 * Set the value related to the column: URL
	 * @param url the URL value
	 */
	public void setUrl (String url) {
		this.url = url;
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
	 * Return the value associated with the column: CATAGORY_ID
	 */
	public com.article.domain.CmsCollectCatagory getCatagory () {
		return catagory;
	}

	/**
	 * Set the value related to the column: CATAGORY_ID
	 * @param catagory the CATAGORY_ID value
	 */
	public void setCatagory (com.article.domain.CmsCollectCatagory catagory) {
		this.catagory = catagory;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.CmsCollectArticle)) return false;
		else {
			com.article.domain.CmsCollectArticle cmsCollectArticle = (com.article.domain.CmsCollectArticle) obj;
			if (null == this.getId() || null == cmsCollectArticle.getId()) return false;
			else return (this.getId().equals(cmsCollectArticle.getId()));
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
		builder.append(user.getId());
		builder.append(url);
		builder.append(remark);
		return builder.toString();
	}


}