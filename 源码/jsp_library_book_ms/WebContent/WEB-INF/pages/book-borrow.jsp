<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书借阅</title>
<link href="css/haiersoft.css" rel="stylesheet" type="text/css" media="screen,print" />
<link href="css/print.css" rel="stylesheet" type="text/css"  media="print" />
<link href="css/base.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/css.css" />
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/side.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$("#bookId").change(function(){
			var bookId = $(this).val();
			var reg = /^[0-9a-zA-Z]+$/;
			var flag = false;
			if(reg.test(bookId)){
				flag = true;
			}
			if(!flag){
				alert("输入的编号不合法!");
				$(this).val("");
				return;
			}
			//1.input文本初始化为空
			$("#bookName").val("");
			$("#author").val("");
			$("#type").val("");
			$("#press").val("");
			$("#isbn").val("");
			$("#pubTime").val("");
			$("#allQuantity").val("");
			$("#aviQuantity").val("");
			$("#msg").text("");
			//2.请求地址为bookServlet
			var url = "bookServlet";  
			//3.请求参数为： method：updateItemQuantity, id:xx, quantity:val, time:new Date()
			var idVal = $.trim(this.name);
			var args = {"method":"getBook","bookId":bookId,"opType":"broQuery","time":new Date()}
			
			$.post(url,args,function(data){
				var bookName = data.bookName;
				var author = data.author;
				var type = data.type;
				var press = data.press;
				var isbn = data.isbn;
				var pubTime = data.pubTime;
				var allQuantity = data.allQuantity;
				var aviQuantity = data.aviQuantity;
				var msg = data.msg;
				//6.更新当前页面的 book 信息
				$("#bookName").val(bookName);
				$("#author").val(author);
				$("#type").val(type);
				$("#press").val(press);
				$("#isbn").val(isbn);
				$("#pubTime").val(pubTime);
				$("#allQuantity").val(allQuantity);
				$("#aviQuantity").val(aviQuantity);
				$("#msg").text(msg);
			},"JSON");
		});
		$(".delete").click(function(){
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:nth-child(3)").text());
			var flag = confirm("确定要移除"+title+"的信息吗？");
			if(flag){
				return true;
			}
			return false;
		});
	})
</script>
</head>
<body>
	<div class="book_borrow">
		<div class="pageTop">
			<div class="page">
				<img src="img/coin02.png" /><span><a href="index.jsp?" target="whole">首页</a>&nbsp;-&nbsp;
					<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">查询馆藏</a>
					&nbsp;-</span>&nbsp;图书借阅
			</div>
		</div>
		<div class="bor_book_query">
		<form action="bookServlet?method=addToCart&kind=borrow" method="post">
		<!-- 此处因为没有加method 而造成空指针异常 -->
			<table cellpadding="0" cellspacing="0" border="1px">
				<tr>
					<td>编号：</td>
					<td class="input_center"><input type="text" class="input_border" name="bookId" id="bookId"/></td>
				</tr>
				<tr>
					<td>图书名称：</td>
					<td class="input_center"><input type="text" name="bookName" id="bookName" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>作者：</td>
					<td class="input_center"><input type="text" name="author" id="author" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>所属类别：</td>
					<td class="input_center"><input type="text" name="type" id="type" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>出版发行：</td>
					<td class="input_center"><input type="text" name="press" id="press" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>isbn：</td>
					<td class="input_center"><input type="text" name="isbn" id="isbn" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>出版时间：</td>
					<td class="input_center"><input type="text" name="pubTime" id="pubTime" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>馆藏信息：</td>
					<td class="input_center">馆藏：<input type="text" size="1" name="allQuantity" id="allQuantity" readonly="readonly"/>&nbsp;
					可借：<input type="text" size="1" name="aviQuantity" id="aviQuantity" readonly="readonly"/></td>
				</tr>
				<tr>
					<td></td>
					<td class="input_center"><input type="submit" value="添加" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out"/>&nbsp;
					<input type="reset" value="重置" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out"/></td>
				</tr>
				<tr>
					<td colspan="2" ><font id="msg" color="red">${requestScope.msg }</font></td>
				</tr>
			</table>
		</form>
		</div>
		<div class="bookCart">
		<form action="bookServlet?method=borrowBook" method="post">
			<table  cellpadding="0" cellspacing="0" border="1px">
				<tr>
					<th colspan="7" style="border: none;" ><font style="font-size: 20px;">我的书架</font></th>
				</tr>
				<tr>
					<td>序号</td>
					<td>图书编号</td>
					<td>图书名称</td>
					<td>作者</td>
					<td>isbn</td>
					<td>出版社</td>
					<td>操作</td>
				</tr>
				<c:if test="${!empty sessionScope.BookCart.books }">
					<c:forEach items="${sessionScope.BookCart.items }" var="book" varStatus="status">
					<tr>
						<td>${status.index + 1 }</td>
						<td>${book.bookId }</td>
						<td>${book.bookName }</td>
						<td>${book.author }</td>
						<td>${book.isbn }</td>
						<td>${book.press }</td>
						<td>
							<a href="bookServlet?method=remove&bookId=${book.bookId }" class="delete">删除</a>
						</td>
					</tr>
					</c:forEach>
				</c:if>
				<tr>
					<td colspan="7" style="text-align: center;">					
						<input type="submit" value="借阅" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out"/>&nbsp;
						<a href="bookServlet?method=clear">清空</a>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</body>
</html>