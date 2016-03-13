package com.article.domain;

import com.article.domain.base.BaseExaQuestion;

import java.util.Set;


public class ExaQuestion extends BaseExaQuestion {
	private static final long serialVersionUID = 1L;
	private static final long SINGLE_SELECT = 0L;
	private static final long MORE_SELECT = 1L;
	private static final long JUDGE = 2L;
	private static final long FILL_BLANK = 3L;
	private static final long QA = 4L;

	private String questionTypeName;


	public String getQuestionTypeName() {
		if(getQuestionType() == SINGLE_SELECT){
			return "单选题";
		}else if(getQuestionType() == MORE_SELECT){
			return "多选题";
		}else if(getQuestionType() == JUDGE){
			return "判断题";
		}else if(getQuestionType() == FILL_BLANK){
			return "填空题";
		}else if(getQuestionType() == QA){
			return "问答题";
		}
		return "";
	}

	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}

	/*[CONSTRUCTOR MARKER BEGIN]*/
	public ExaQuestion () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ExaQuestion (Long id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/


}