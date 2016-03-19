package com.article.domain;

import com.article.domain.base.BaseExaPaper;
import org.apache.commons.lang.StringUtils;

import java.sql.Date;


public class ExaPaper extends BaseExaPaper {
	private static final long serialVersionUID = 1L;

	public boolean getCanNotDownload(){
		return StringUtils.isNotBlank(getStartTime())&& Date.valueOf(getStartTime()).compareTo(new Date(System.currentTimeMillis()))>0||
				StringUtils.isNotBlank(getEndTime())&&Date.valueOf(getEndTime()).compareTo(new Date(System.currentTimeMillis()))<0	;
	}


	/*[CONSTRUCTOR MARKER BEGIN]*/
	public ExaPaper () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ExaPaper (Long id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/


}