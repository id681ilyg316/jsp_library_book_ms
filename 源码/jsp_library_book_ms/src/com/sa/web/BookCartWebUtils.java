package com.sa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sa.domain.BookCart;


public class BookCartWebUtils {
	/**
	 * ��ȡ���ﳵ���󣺴�session�л�ȡ����session��û�иö���
	 * �򴴽�һ���µĹ��ﳵ���󣬷��뵽session������
	 * ���У���ֱ�ӷ���
	 * @param request
	 * @return
	 */
	public static BookCart getBookCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		BookCart bc = (BookCart) session.getAttribute("BookCart");
		if(bc == null) {
			bc = new BookCart();
			session.setAttribute("BookCart", bc);
		}
		return bc;
	}
}
