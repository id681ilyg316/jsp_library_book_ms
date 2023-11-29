package com.sa.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;

import com.google.gson.Gson;
import com.sa.domain.AjaxFormData;
import com.sa.domain.Borrow;
import com.sa.domain.System;
import com.sa.domain.User;
import com.sa.service.BorrowService;
import com.sa.service.SystemService;
import com.sa.service.UserService;
import com.sa.util.ImgUtils;
import com.sa.util.UploadUtils;
import com.sa.web.CriteriaUser;
import com.sa.web.Page;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private UserService userService = new UserService();
	private BorrowService borrowService = new BorrowService();
	private SystemService systemService = new SystemService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	protected void getColleges(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AjaxFormData> colleges = userService.getColleges();
		Map<String, String> cm = new HashMap<String, String>();
		StringBuffer cgStr = new StringBuffer();
		for(int i = 0;i<colleges.size();i++) {
			cgStr.append(colleges.get(i).getCollege());
			if(i < colleges.size()-1)
				cgStr.append(",");
		}
		cm.put("college", cgStr.toString());
		Gson gson = new Gson();
		String jsonStr = gson.toJson(cm);
		response.setContentType("text/javascript;charset=utf-8");
		response.getWriter().print(jsonStr);
	}
	protected void getMajors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String college = request.getParameter("college");
		List<AjaxFormData> majors = userService.getMajors(college);
		Map<String, String> mm = new HashMap<String, String>();
		StringBuffer cgStr = new StringBuffer();
		for(int i = 0;i<majors.size();i++) {
			cgStr.append(majors.get(i).getMajor());
			if(i < majors.size()-1)
				cgStr.append(",");
		}
		mm.put("major", cgStr.toString());
		Gson gson = new Gson();
		String jsonStr = gson.toJson(mm);
		response.setContentType("text/javascript;charset=utf-8");
		response.getWriter().print(jsonStr);
	}
	protected void getClasses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String major = request.getParameter("major");
		List<AjaxFormData> classes = userService.getClasses(major);
		Map<String, String> mm = new HashMap<String, String>();
		StringBuffer cgStr = new StringBuffer();
		for(int i = 0;i<classes.size();i++) {
			cgStr.append(classes.get(i).getClasses());
			if(i < classes.size()-1)
				cgStr.append(",");
		}
		mm.put("classes", cgStr.toString());
		Gson gson = new Gson();
		String jsonStr = gson.toJson(mm);
		response.setContentType("text/javascript;charset=utf-8");
		response.getWriter().print(jsonStr);
	}
	protected void getUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String college = request.getParameter("college")==null?""
				:request.getParameter("college");
		String major = request.getParameter("major")==null?""
				:request.getParameter("major");
		String classes = request.getParameter("classes")==null?""
				:request.getParameter("classes");
		String name = request.getParameter("name")==null?""
				:request.getParameter("name");
		String id = request.getParameter("id")==null?""
				:request.getParameter("id");
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
//		System.out.println("^pageNo:"+pageNo);
		CriteriaUser cu = new CriteriaUser(new User(id, name, college, major, classes, "","")
				, pageNo);
		Page<User> userPage = userService.getUserPage(cu);
		request.setAttribute("userPage", userPage);
		request.getRequestDispatcher("WEB-INF/pages/tab.jsp").forward(request, response);
	}
	protected void getFinePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/fine.jsp").forward(request, response);
	}
	protected void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userid");
		String type = "";
		if(request.getParameter("type")!=null)
			type = request.getParameter("type");
		User user = userService.getUser(id);
		request.setAttribute("user", user);
		if(type.equals("update")) {			
			request.getRequestDispatcher("WEB-INF/pages/user-update.jsp").forward(request, response);
			return;
		}else if(type.equals("fine")) {
			List<Borrow> borrowList = borrowService.getUserBorrowRec(id);
			List<Borrow> fineList = new ArrayList<Borrow>();
			float fineMoney = 0;
			for(int i = 0;i<borrowList.size();i++) {
				if(borrowList.get(i).getOverFine() != null)
					if(borrowList.get(i).getOverFine() > 0) {
						fineList.add(borrowList.get(i));
						fineMoney += borrowList.get(i).getOverFine();
					}
			}
			request.setAttribute("fineMoney", fineMoney);
			request.setAttribute("fineList", fineList);
			request.getRequestDispatcher("WEB-INF/pages/fine.jsp").forward(request, response);
			return;
		}
		else {
			List<Borrow> borrowList = borrowService.getUserBorrowRec(id);
			request.setAttribute("borrowList", borrowList);
		}
		request.getRequestDispatcher("WEB-INF/pages/user-info.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String college = request.getParameter("college");
		String major = request.getParameter("major");
		String classes = request.getParameter("classes");
		String password = request.getParameter("password");
		String imgPath = request.getParameter("imgPath");
		String pageNo = request.getParameter("pageNo");
		userService.updateUser(new User(id, name, college, major, classes, password, imgPath));
//		System.out.println(pageNo);
		response.sendRedirect("userServlet?method=getUsers&pageNo="+pageNo);
	}
	protected void changePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String newPwd = request.getParameter("newPwd");
		String code = request.getParameter("code");
		//验证码校验
		String checkCode = request.getSession().getAttribute("checkcode").toString();
		if(!checkCode.equalsIgnoreCase(code)) {
			request.setAttribute("codeMsg", "验证码错误！");
			request.getRequestDispatcher("WEB-INF/pages/changepwd.jsp").forward(request, response);
			return;
		}
		if(request.getSession().getAttribute("userIdentity").equals("user")) {
			userService.changePwd(userId, newPwd);
			request.setAttribute("msg", "密码修改成功,新密码："+((User)request.getSession().getAttribute("user")).getPassword());			
		}
		else {
			systemService.changePwd(userId, newPwd);
			request.setAttribute("msg", "密码修改成功,新密码："+((System)request.getSession().getAttribute("user")).getPassword());			
		}
		request.getRequestDispatcher("WEB-INF/pages/changepwd.jsp").forward(request, response);;
	}
	protected void getCgPwdPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/changepwd.jsp").forward(request, response);
	}
	protected void getAddUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/user-add.jsp").forward(request, response);
	}
	protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) ImgUtils.uploadUserImg(request);
		//开始向数据库表添加用户信息
		userService.addUser(user);
		request.setAttribute("msg", "用户："+user.getName()+"添加成功！");
        request.getRequestDispatcher("WEB-INF/pages/user-add.jsp").forward(request, response);
	}
}
