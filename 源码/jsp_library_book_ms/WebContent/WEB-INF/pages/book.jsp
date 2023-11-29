<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${book.bookName }</title>
<link href="css/haiersoft.css" rel="stylesheet" type="text/css" media="screen,print" />
<link href="css/print.css" rel="stylesheet" type="text/css"  media="print" />
<link rel="stylesheet" type="text/css" href="css/css.css" />
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/side.js" type="text/javascript"></script>
<%@ include file="/commons/bookQueryCondition.jsp" %>
</head>
<body>
	<div class="form_book">
		<div class="pageTop">
			<div class="page">
				<img src="img/coin02.png" /><span><a href="index.jsp?" target="whole">首页</a>&nbsp;-&nbsp;
					<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">图书管理</a>
					&nbsp;-</span>&nbsp;图书信息
			</div>
		</div>
		<div class="bookDisp">
			<div class="bookMain4Sys">
			<table cellpadding="0" cellspacing="0" border="1px">
				<tr>
					<td>图书名称：</td>
					<td>${book.bookName }</td>
					<td colspan="2" rowspan="2"></td>
				</tr>
				<tr>
					<td>作者：</td>
					<td>${book.author }</td>
				</tr>
				<tr>
					<td>所属类别：</td>
					<td>${book.type }</td>
					<td rowspan="4">
						<img src="img/book/${book.imgPath }" width="150" height="150"/>
					</td>
				</tr>
				<tr>
					<td>出版发行：</td>
					<td>${book.press }</td>
				</tr>
				<tr>
					<td>isbn:</td>
					<td>${book.isbn }</td>
					<td rowspan="4">
					</td>
				</tr>
				<tr>
					<td>编号：</td>
					<td>${book.bookId }</td>
				</tr>
				<tr>
					<td>出版时间：</td>
					<td>${book.pubTime }</td>
				</tr>
				<tr>
					<td>电子书阅读：</td>
					<td><a href="pdf/book/${book.pdfPath }">阅读（右键，打开标签页）</a></td>
				</tr>
				<tr>
					<td>馆藏信息：</td>
					<td>馆藏：<font class="font_bold_green">${book.allQuantity }</font>
						可借：
						<c:choose>
							<c:when test="${book.aviQuantity > 0}">
								<font class="font_bold_green">${book.aviQuantity }</font>
							</c:when>
							<c:otherwise>
								<font class="font_bold_red">${book.aviQuantity }</font>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</table>
			</div>
			<div class="bookBroInfo">
				<h4>借阅信息</h4>
				<table cellpadding="0" cellspacing="0" border="1px">
					<tr>
						<td colspan="2">借阅次数：</td>
						<td>${fn:length(bookBorrowList) }</td>
					</tr>
					<tr>
						<td>序号</td>
						<td>借阅编号</td>
						<td>学号/工号</td>
						<td>姓名</td>
						<td>班级</td>
						<td>借阅时间</td>
						<td>应还时间</td>
						<td>实还时间</td>
					</tr>
					<c:forEach items="${bookBorrowList }" var="borrow" varStatus="status">
						<%-- 设置隔行变色 --%>
						<c:set var="rows" scope="page" value="${status.index + 1 }"/>
						<c:choose>
							<c:when  test="${rows % 2 == 0 }">
								<c:set var="className" value="bgcA"/>
							</c:when>
							<c:otherwise>
								<c:set var="className" value="bgcB"/>
							</c:otherwise>
						</c:choose>
						<tr class="${className }">
							<td>${status.index + 1 }</td>
							<td>${borrow.id }</td>
							<td>${borrow.userId }</td>
							<td>${borrow.name }</td>
							<td>${borrow.classes }</td>
							<td>${borrow.borTime }</td>
							<td>${borrow.retTime }</td>
							<td>${borrow.relTime }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>