package com.sa.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sa.domain.System;
import com.sa.domain.User;
import com.sa.service.SystemService;
import com.sa.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();
    private SystemService systemService = new SystemService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getSession().invalidate();
    	response.sendRedirect("login.jsp");
    }
    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String identity = request.getParameter("identity");
    	String id = request.getParameter("id");
    	String password = request.getParameter("password");
    	boolean flag = false;
    	Object user = null;
    	if(id != null && !id.trim().equals("")) {   		
    		if(identity.equals("user")) {
    			user = userService.getUser(id);
    			if(user != null)
	    			if(password.trim().equals(((User)user).getPassword())) {
	    				flag = true;
	    			}
    		}else{
				user = systemService.getAdmin(id);
				if(user != null)
					if(password.trim().equals(((System)user).getPassword())) {
						flag = true;
	    			}
    		}
    		if(flag) {    			
    			request.getSession().setAttribute("user", user);
    			request.getSession().setAttribute("userIdentity", identity);
    			response.sendRedirect("index.jsp");
    			return;
    		}
    	}
    	request.setAttribute("msg", "用户名或密码错误");
    	request.getRequestDispatcher("login.jsp").forward(request, response);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class
					,HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request,response);
		} catch (Exception e) {
			throw new RuntimeException(e);//事务操作时，若发生异常，则会在transactionFilter中
										//处理异常，并回滚。若在此处就捕获处理异常，事务过滤器就毫无意义。
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     	String id = request.getParameter("id");
    	String password = request.getParameter("password");
    	
    	request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}
