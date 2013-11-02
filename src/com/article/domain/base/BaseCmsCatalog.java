package com.article.domain.base;

import com.article.domain.CmsCatalog;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_catalog table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 目录
 * SyncTemplatepatterns : tree\w*
 * SyncDao : false
 * TableName : cms_catalog
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : true
 * Projectable : false
 *
 * @hibernate.class
 *  table="cms_catalog"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseCmsCatalog  implements Serializable,com.comet.core.entity.Treeable{

	public static String REF = "CmsCatalog";
	public static String PROP_TYPE = "type";
	public static String PROP_PARENT_ID = "parentId";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_TREE_ID = "treeId";
	public static String PROP_PATH = "path";
	public static String PROP_CODE = "code";
	public static String PROP_ICON = "icon";
	public static String PROP_IS_LEAF = "isLeaf";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";
	public static String PROP_IS_VALID = "isValid";


	// constructors
	public BaseCmsCatalog () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsCatalog (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
    /*NAME*/
    /*名称*/
	private String name;
	
    /*CODE*/
    /*编码*/
	private String code;
	
    /*PATH*/
    /*文件目录*/
	private String path;
	
    /*ICON*/
    /*显示图标*/
	private String icon;
	
    /*PARENT_ID*/
    /*父节点标识*/
	private CmsCatalog parent;
	
    /*IS_LEAF*/
    /*叶节点*/
	private Boolean isLeaf;
	
    /*TREE_ID*/
    /*树形层次*/
	private String treeId;
	
    /*TYPE*/
    /*类型目录:0  知识点目录;1 图片目录;2 附件目录*/
	private String type;
	
    /*IS_VALID*/
    /*是否有效*/
	private Boolean isValid;
	
    /*DESCRIPTION*/
    /*备注*/
	private String description;
	



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="com.justonetech.core.orm.hibernate.LongIdGenerator"
     *  column="ID"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 * @deprecated
	 */
	public void setId (java.lang.Long id) {
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


	/**
	 * Return the value associated with the column: PATH
	 */
	public String getPath () {
		return path;
	}

	/**
	 * Set the value related to the column: PATH
	 * @param path the PATH value
	 */
	public void setPath (String path) {
		this.path = path;
	}


	/**
	 * Return the value associated with the column: ICON
	 */
	public String getIcon () {
		return icon;
	}

	/**
	 * Set the value related to the column: ICON
	 * @param icon the ICON value
	 */
	public void setIcon (String icon) {
		this.icon = icon;
	}


	/**
	 * Return the value associated with the column: PARENT_ID
	 */
    public CmsCatalog getParent() {
        return parent;
    }

    /**
	 * Set the value related to the column: PARENT_ID
	 * @param parent the PARENT_ID value
	 */
	public void setParent(CmsCatalog parent) {
		this.parent = parent;
	}


	/**
	 * Return the value associated with the column: IS_LEAF
	 */
	public Boolean getIsLeaf () {
		return isLeaf;
	}

	/**
	 * Set the value related to the column: IS_LEAF
	 * @param isLeaf the IS_LEAF value
	 */
	public void setIsLeaf (Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}


	/**
	 * Return the value associated with the column: TREE_ID
	 */
	public String getTreeId () {
		return treeId;
	}

	/**
	 * Set the value related to the column: TREE_ID
	 * @param treeId the TREE_ID value
	 */
	public void setTreeId (String treeId) {
		this.treeId = treeId;
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
	 * Return the value associated with the column: IS_VALID
	 */
	public Boolean getIsValid () {
		return isValid;
	}

	/**
	 * Set the value related to the column: IS_VALID
	 * @param isValid the IS_VALID value
	 */
	public void setIsValid (Boolean isValid) {
		this.isValid = isValid;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.article.domain.CmsCatalog)) return false;
		else {
			com.article.domain.CmsCatalog cmsCatalog = (com.article.domain.CmsCatalog) obj;
			if (null == this.getId() || null == cmsCatalog.getId()) return false;
			else return (this.getId().equals(cmsCatalog.getId()));
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
		builder.append(path);
		builder.append(icon);
		builder.append(parent);
		builder.append(isLeaf);
		builder.append(treeId);
		builder.append(type);
		builder.append(isValid);
		builder.append(description);
		return builder.toString();
	}


}