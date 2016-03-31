package com.article.domain;

import com.article.domain.base.BaseExaPaper;
import com.article.util.Constants;
import org.apache.commons.lang.StringUtils;

import java.sql.Date;


public class ExaPaper extends BaseExaPaper {
	private static final long serialVersionUID = 1L;

	private String paperTypeName;

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

	public String getPaperTypeName() {
		if(StringUtils.equals(this.getPaperType(), Constants.PAPER_TYPE_SIMULATOR)) {
			return "模拟";
		} else if(StringUtils.equals(this.getPaperType(), Constants.PAPER_TYPE_EXERCISE)) {
			return "练习";
		} else if(StringUtils.equals(this.getPaperType(), Constants.PAPER_TYPE_EXAMINATION)) {
			return "考试";
		}

		return "";
	}

	public void setPaperTypeName(String paperTypeName) {
		this.paperTypeName = paperTypeName;
	}
}