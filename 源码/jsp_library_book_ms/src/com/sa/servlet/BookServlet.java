package com.sa.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sa.domain.Book;
import com.sa.domain.BookBorrow;
import com.sa.domain.BookCart;
import com.sa.domain.User;
import com.sa.service.BookService;
import com.sa.service.BorrowService;
import com.sa.service.ReturnService;
import com.sa.util.ImgUtils;
import com.sa.web.BookCartWebUtils;
import com.sa.web.CriteriaBook;
import com.sa.web.Page;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookService bookService = new BookService();
    private BorrowService borrowService = new BorrowService();
    private ReturnService returnService = new ReturnService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void fine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		returnService.fine(userid);
		request.setAttribute("msg", "用户 "+userid+" 已缴清罚款！");
		request.getRequestDispatcher("WEB-INF/pages/fine.jsp").forward(request, response);
    }
    protected void returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookCart bc = BookCartWebUtils.getBookCart(request);
		Collection<Book> books = bc.getBooks().values();
		//开始还书...
		Map<String,Float> overFine = new HashMap<String, Float>();
		float fine = Float.parseFloat(this.getServletContext().getInitParameter("overFine"));
		String msg = returnService.returnBook(((User)(request.getSession().getAttribute("user")))
				.getId(), books,fine,overFine);
		request.setAttribute("overFine", overFine);
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("return-success.jsp").forward(request, response);
	}
    
    protected void borrowBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookCart bc = BookCartWebUtils.getBookCart(request);
		Collection<Book> books = bc.getBooks().values();
		//开始借阅...
		String msg = borrowService.borrowBook(((User)(request.getSession().getAttribute("user"))).getId(), books);
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("borrow-success.jsp").forward(request, response);
	}
    
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookService.clear(BookCartWebUtils.getBookCart(request));
		if(request.getParameter("kind")!=null) {
			request.getRequestDispatcher("WEB-INF/pages/book-return.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward(request, response);
	}
    protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		bookService.removeBookFromCart(bookId, BookCartWebUtils.getBookCart(request));
		request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward(request, response);
	}
    protected void addToReturnCart(BookCart bc,String idStr,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean flag = true;
    	//1.校验图书是否是用户的在借图书
    	flag = returnService.isUserBorrowBook(((User)request.getSession().getAttribute("user")).getId(),
    			idStr);
    	if(!flag) {
    		request.setAttribute("msg", "该图书不是您的在借图书！");
    		request.getRequestDispatcher("WEB-INF/pages/book-return.jsp").forward(request, response);
    		return;
    	}
    	bookService.addToCart(idStr,bc);
		request.getRequestDispatcher("WEB-INF/pages/book-return.jsp").forward(request, response);;
		return;
    }
    protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//1.获取图书的id
		String idStr = request.getParameter("bookId");
		int id = -1;
		boolean flag = true;
		//2.获取购物车对象
		BookCart bc = BookCartWebUtils.getBookCart(request);
		/**
		 * 读者还书，也需要用到购物车，但无需进行 最大借阅校验，是否存在超期图书校验。
		 * 如果单独再弄一个购物车，代码量会变多。
		 * 考虑到借书和还书共用一个购物车，其中的影响可以通过一些不麻烦的约束来解除。
		 */
		if(request.getParameter("kind").equals("return")) {
			addToReturnCart(bc, idStr, request, response);
			return;
		}
		//3.校验用户是否小于最大借阅数
		User user = (User) (request.getSession().getAttribute("user"));
		int curr_BorrowCount = borrowService.getCurBorrowCount(user.getId());
		if(curr_BorrowCount + bc.getBookNumber() >= 5) {
			request.setAttribute("msg", "超出借阅数量(5本)");
			request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward(request, response);
			return;
		}
		//4.校验用于是否存在超期图书
		if(borrowService.overBook(user.getId()) > 0) {
			request.setAttribute("msg", "您存在超期图书，不能继续借阅！");
			request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward(request, response);
			return;
		}
		//5.校验用户是否已经借了同一种书
		if(borrowService.isAlreadyBorrow(user.getId(), idStr) > 0) {
			request.setAttribute("msg", "您已借有该图书，不能重复借阅！");
			request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward(request, response);
			return;
		}
		//6.调用BookService的addToCart()方法把商品放到购物车中
		flag = bookService.addToCart(idStr,bc);
		if(flag) {
			//6.返回图书借阅页面
			request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward(request, response);;
			return;
		}
		response.sendRedirect(request.getContextPath() + "/error-1.jsp");
    }
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String bookId = request.getParameter("bookId");
    	String bookName = request.getParameter("bookName");
    	String type = request.getParameter("type");
    	String author = request.getParameter("author");
    	String isbn = request.getParameter("isbn");
    	String press = request.getParameter("press");
    	String pubTime = request.getParameter("pubTime");
    	String allQuantity = request.getParameter("allQuantity");
    	String aviQuantity = request.getParameter("aviQuantity");
    	String imgPath = request.getParameter("imgPath");
    	String pdfPath = request.getParameter("pdfPath");
    	String pageNo = request.getParameter("pageNo");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	try {
			bookService.update(new Book(bookId, bookName, type, isbn, author, press,
					new java.sql.Date(sdf.parse(pubTime).getTime()), Integer.parseInt(allQuantity), 
					Integer.parseInt(aviQuantity), imgPath, pdfPath));
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
    	response.sendRedirect("bookServlet?method=getBooks&pageNo="+pageNo);
    }
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String bookId = request.getParameter("bookId");
		String type = "";
		if(request.getParameter("opType")!=null)
			type = request.getParameter("opType");
		Book book = bookService.getBook(bookId);
		request.setAttribute("book", book);
		//修改图书信息时
		if(!type.equals("update")) {			
			List<BookBorrow> bookBorrowList = bookService.getBookBorrow(bookId);
			request.setAttribute("bookBorrowList", bookBorrowList);
		}else {
			request.getRequestDispatcher("WEB-INF/pages/book-update.jsp").forward(request, response);
			return;
		}
		//借阅图书时
		if(type.equals("broQuery")) {
			Map<String, Object> result = new HashMap<String, Object>();
			if(book!=null) {				
				result.put("bookName", book.getBookName());
				result.put("author", book.getAuthor());
				result.put("type", book.getType());
				result.put("press", book.getPress());
				result.put("isbn", book.getIsbn());
				result.put("pubTime", book.getPubTime());
				result.put("allQuantity", book.getAllQuantity());
				result.put("aviQuantity", book.getAviQuantity());
			}else
				result.put("msg", "不存在该图书信息！");
			Gson gson = new Gson();
			String jsonStr = gson.toJson(result);
			response.setContentType("text/javascript;charset=utf-8");
			response.getWriter().print(jsonStr);
			return;
		}
		//游客和用户只能访问图书信息而不能访问该图书的借阅信息
		if(request.getSession().getAttribute("userIdentity") == null){
			request.getRequestDispatcher("WEB-INF/pages/book_4User.jsp").forward(request, response);
			return;
		}
		//游客和用户只能访问图书信息而不能访问该图书的借阅信息
		if(request.getSession().getAttribute("userIdentity").toString().equals("user")) {
			request.getRequestDispatcher("WEB-INF/pages/book_4User.jsp").forward(request, response);
			return;
		}
		//查询用户详细信息时
		request.getRequestDispatcher("WEB-INF/pages/book.jsp").forward(request, response);
	
    }
    protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String bookName = request.getParameter("bookName")==null?""
				:request.getParameter("bookName");
		String type = request.getParameter("type")==null?""
				:request.getParameter("type");
		String isbn = request.getParameter("isbn")==null?""
				:request.getParameter("isbn");
		String bookId = request.getParameter("bookId")==null?""
				:request.getParameter("bookId");
		String pageNoStr = request.getParameter("pageNo");
		String searchType = request.getParameter("searchWay")==null?"":request.getParameter("searchWay");
		String searchValue = request.getParameter("searchValue")==null?"":request.getParameter("searchValue");
//		System.out.println("searchValue:"+searchValue);
		//指定条件搜索时
		if(!searchType.equals("")) {		
			if(searchType.equals("bookName"))
				bookName = searchValue;
			else if(searchType.equals("bookId"))
				bookId = searchValue;
			else if(searchType.equals("isbn"))
				isbn = searchValue;
			else if(searchType.equals("type"))
				type = searchValue;
		}
//		System.out.println("searchType:"+searchType);
//		System.out.println("bookName:"+bookName);
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (Exception e) {}
//		System.out.println("^pageNo:"+pageNo);
		CriteriaBook cb = new CriteriaBook(new Book(bookId, bookName, type, isbn, "", "", null, -1, -1, "", ""), pageNo);
		Page<Book> bookPage = null;
		try {
			bookPage = bookService.getBookPage(cb);
		} catch (Exception e) {
			
		}
		request.setAttribute("bookPage", bookPage);
		request.getRequestDispatcher("WEB-INF/pages/books.jsp").forward(request, response);
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
	
	protected void getReturnPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookService.clear(BookCartWebUtils.getBookCart(request));
		request.getRequestDispatcher("WEB-INF/pages/book-return.jsp").forward(request, response);;
	}
	
	protected void getBorrowPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bookService.clear(BookCartWebUtils.getBookCart(request));
		request.getRequestDispatcher("WEB-INF/pages/book-borrow.jsp").forward(request, response);
	}
	
	protected void getAddBookPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/book-add.jsp").forward(request, response);
	}
	
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = null;
		try {
			book = (Book) ImgUtils.uploadBookImg(request);
		} catch (ParseException e) {
			request.setAttribute("msg", "日期输入不合法！");
			request.getRequestDispatcher("WEB-INF/pages/book-add.jsp").forward(request, response);
			return;
		}
		bookService.addBook(book);
		request.setAttribute("msg", "图书："+book.getBookName()+"添加成功！");
		request.getRequestDispatcher("WEB-INF/pages/book-add.jsp").forward(request, response);
	}
}
