package com.article.domain.base;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the cms_collect_catagory table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 收藏夹
 * SyncTemplatepatterns : tree\w*
 * SyncDao : false
 * TableName : cmc_collect_catagory
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : true
 * Projectable : false
 *
 * @hibernate.class
 *  table="cms_collect_catagory"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseCmsCollectCatagory  implements Serializable,com.comet.core.entity.Treeable{

    public static String REF = "CmsCollectCatagory";
    public static String PROP_TYPE = "type";
    public static String PROP_TREE_ID = "treeId";
    public static String PROP_USER_ID = "userId";
    public static String PROP_PARENT = "parent";
    public static String PROP_CODE = "code";
    public static String PROP_IS_LEAF = "isLeaf";
    public static String PROP_IS_VALID = "isValid";
    public static String PROP_NAME = "name";
    public static String PROP_ID = "id";


    // constructors
    public BaseCmsCollectCatagory () {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseCmsCollectCatagory (Long id) {
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
    private java.lang.String name;

    /*CODE*/
    /*代码*/
    private java.lang.String code;

    /*USER_ID*/
    /*用户ID*/
    private java.lang.String userId;

    /*TYPE*/
    /*类型,0表示全部用户共用,1用户自己创建*/
    private java.lang.String type;

    /*IS_LEAF*/
    /*叶节点*/
    private Boolean isLeaf;

    /*IS_VALID*/
    /*是否有效*/
    private Boolean isValid;

    /*TREE_ID*/
    /*树形层次*/
    private java.lang.String treeId;


    // many to one
    private com.article.domain.CmsCollectCatagory parent;



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
    public java.lang.String getName () {
        return name;
    }

    /**
     * Set the value related to the column: NAME
     * @param name the NAME value
     */
    public void setName (java.lang.String name) {
        this.name = name;
    }


    /**
     * Return the value associated with the column: CODE
     */
    public java.lang.String getCode () {
        return code;
    }

    /**
     * Set the value related to the column: CODE
     * @param code the CODE value
     */
    public void setCode (java.lang.String code) {
        this.code = code;
    }


    /**
     * Return the value associated with the column: USER_ID
     */
    public java.lang.String getUserId () {
        return userId;
    }

    /**
     * Set the value related to the column: USER_ID
     * @param userId the USER_ID value
     */
    public void setUserId (java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Return the value associated with the column: TYPE
     */
    public java.lang.String getType () {
        return type;
    }

    /**
     * Set the value related to the column: TYPE
     * @param type the TYPE value
     */
    public void setType (java.lang.String type) {
        this.type = type;
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

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        isValid = isValid;
    }

    /**
     * Return the value associated with the column: TREE_ID
     */
    public java.lang.String getTreeId () {
        return treeId;
    }

    /**
     * Set the value related to the column: TREE_ID
     * @param treeId the TREE_ID value
     */
    public void setTreeId (java.lang.String treeId) {
        this.treeId = treeId;
    }


    /**
     * Return the value associated with the column: PARENT_ID
     */
    public com.article.domain.CmsCollectCatagory getParent () {
        return parent;
    }

    /**
     * Set the value related to the column: PARENT_ID
     * @param parent the PARENT_ID value
     */
    public void setParent (com.article.domain.CmsCollectCatagory parent) {
        this.parent = parent;
    }



    public boolean equals (Object obj) {
        if (null == obj) return false;
        if (!(obj instanceof com.article.domain.CmsCollectCatagory)) return false;
        else {
            com.article.domain.CmsCollectCatagory cmsCollectCatagory = (com.article.domain.CmsCollectCatagory) obj;
            if (null == this.getId() || null == cmsCollectCatagory.getId()) return false;
            else return (this.getId().equals(cmsCollectCatagory.getId()));
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
        builder.append(userId);
        builder.append(type);
        builder.append(isLeaf);
        builder.append(treeId);
        return builder.toString();
    }


}