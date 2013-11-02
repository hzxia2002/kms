package com.article.domain;

import com.article.domain.base.BaseCmsArticle;

import java.util.Date;


public class CmsArticle extends BaseCmsArticle {
	private static final long serialVersionUID = 1L;


    /*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsArticle () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsArticle (Long id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/


}