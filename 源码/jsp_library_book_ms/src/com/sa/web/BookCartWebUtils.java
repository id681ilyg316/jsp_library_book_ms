package com.sa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sa.domain.BookCart;


public class BookCartWebUtils {
	/**
	 * 获取购物车对象：从session中获取，若session中没有该对象
	 * 则创建一个新的购物车对象，放入到session对象中
	 * 若有，则直接返回
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
