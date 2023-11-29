package com.sa.util;

import com.sa.web.CriteriaBook;
import com.sa.web.CriteriaUser;

public class CriteriaUserUtils {
	public static StringBuffer getUserFilter(CriteriaUser cu) {
		StringBuffer filter = new StringBuffer("");
		int i = 0;
		if(!cu.getUser().getId().equals("-«Î—°‘Ò-")) {
			filter.append(" id LIKE '%"+cu.getUser().getId()+"%'");
			i++;
		}
		if (!cu.getUser().getName().equals("-«Î—°‘Ò-")) {
			if(i!=0)
				filter.append(" AND");
			filter.append(" name LIKE '%"+cu.getUser().getName()+"%'");
			i++;
		}
		if(!cu.getUser().getCollege().equals("-«Î—°‘Ò-")) {
			if(i!=0)
				filter.append(" AND");
			filter.append(" college LIKE '%"+cu.getUser().getCollege()+"%'");
			i++;
		}
		if(!cu.getUser().getMajor().equals("-«Î—°‘Ò-")) {
			if(i!=0)
				filter.append(" AND");
			filter.append(" major LIKE '%"+cu.getUser().getMajor()+"%'");
			i++;
		}
		if(!cu.getUser().getClasses().equals("-«Î—°‘Ò-")) {
			if(i!=0)
				filter.append(" AND");
			filter.append(" classes LIKE '%"+cu.getUser().getClasses()+"%'");
			i++;
		}
		if(!cu.getUser().getId().equals("-«Î—°‘Ò-")) {
			if(i!=0)
				filter.append(" AND");
			filter.append(" id LIKE '%"+cu.getUser().getId()+"%'");
			i++;
		}
		if(i == 0)
			filter.append(" 1 = 1");
		return filter;
	}
	
	public static StringBuffer getBookFilter(CriteriaBook cb) {
		StringBuffer filter = new StringBuffer("");
		int i = 0;
		if(!cb.getBook().getBookId().equals("")) {
			filter.append(" bookId LIKE '%"+cb.getBook().getBookId()+"%'");
			i++;
		}
		if (!cb.getBook().getBookName().equals("")) {
			if(i!=0)
				filter.append(" AND");
			filter.append(" bookName LIKE '%"+cb.getBook().getBookName()+"%'");
			i++;
		}
		if(!cb.getBook().getType().equals("")) {
			if(i!=0)
				filter.append(" AND");
			filter.append(" type LIKE '%"+cb.getBook().getType()+"%'");
			i++;
		}
		if(!cb.getBook().getIsbn().equals("")) {
			if(i!=0)
				filter.append(" AND");
			filter.append(" isbn LIKE '%"+cb.getBook().getIsbn()+"%'");
			i++;
		}
		if(i == 0)
			filter.append(" 1 = 1");
		return filter;
	}
}
