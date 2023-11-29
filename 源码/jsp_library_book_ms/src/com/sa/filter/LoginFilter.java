package com.sa.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/*" })
public class LoginFilter implements Filter {
	private String redirectPage;
	private String checkedUrls;
	private String userAuthority;
	private String adminAuthority;
	private String error_404;
    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String servletPath = req.getServletPath();//不带contextPath 的叫 servletPath
		List<String> urls = Arrays.asList(checkedUrls.split(","));
		if(!urls.contains(servletPath)) {
			chain.doFilter(request, response);
			return;
		}
		Object user = req.getSession().getAttribute("user");
		if(user == null) {
			resp.getWriter().write("<script type='text/JavaScript'>window.open('" + req.getContextPath()+redirectPage + "','_top'); </script>");
			return;
		}
		//权限校验
		String identity = req.getSession().getAttribute("userIdentity").toString();
		if(identity.equals("user")) {
			List<String> userUrls = Arrays.asList(userAuthority.split(","));
			if(!userUrls.contains(servletPath)) {
				chain.doFilter(request, response);
				return;
			}
		}
		if(identity.equals("sys")) {
			List<String> adminUrls = Arrays.asList(adminAuthority.split(","));
			if(!adminUrls.contains(servletPath)) {
				chain.doFilter(request, response);
				return;
			}
		}
		resp.getWriter().write("<script type='text/JavaScript'>window.open('" + req.getContextPath()+error_404 + "','_top'); </script>");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext servletContext = fConfig.getServletContext();
		redirectPage = servletContext.getInitParameter("redirectPage");
		checkedUrls = servletContext.getInitParameter("checkedUrls");
		userAuthority = servletContext.getInitParameter("userAuthority");
		adminAuthority = servletContext.getInitParameter("adminAuthority");
		error_404 = servletContext.getInitParameter("error-404");
	}

}
