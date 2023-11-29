<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${book.bookName }</title>
<link href="css/haiersoft.css" rel="stylesheet" type="text/css" media="screen,print" />
<link href="css/print.css" rel="stylesheet" type="text/css"  media="print" />
<link rel="stylesheet" type="text/css" href="css/css.css" />
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/webCalendar.js" type="text/javascript"></script> 
<script src="js/side.js" type="text/javascript"></script>
<%@ include file="/commons/bookQueryCondition.jsp" %>
</head>
<script type="text/javascript">
		$(function(){
			$(":submit").click(function(){
				//检查用户输入信息是否完整
				var bookName = $("#bookName").val();
				var author = $("#author").val();
				var type = $("#type").val();
				var press = $("#press").val();
				var isbn = $("#isbn").val();
				var pubTime = $("#pubTime").val();
				var allQuantity = $("#allQuantity").val();
				if(bookName!="" && author != "" && type != "" && press != "" && isbn != ""
					&& pubTime != "" && allQuantity != "")
					return;
				alert("信息不完整！");
				return false;
			});
		})
	</script>
<body>
	<div class="form_book">
		<div class="pageTop">
			<div class="page">
				<img src="img/coin02.png" /><span><a href="index.jsp?" target="whole">首页</a>&nbsp;-&nbsp;
					<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">图书管理</a>
					&nbsp;-</span>&nbsp;修改图书信息
			</div>
		</div>
		<div class="bookDisp">
			<div class="bookMain">
			<form action="bookServlet?method=addBook" enctype="multipart/form-data" method="post">
			<table cellpadding="0" cellspacing="0" border="1px">
				<tr>
					<td>图片：</td>
					<td><input type="file" name="file"><br/></td>
				</tr>
				<tr>
					<td>图书名称：</td>
					<td><input type="text" id = "bookName" name="bookName"/></td>
				</tr>
				<tr>
					<td>图片资源（PDF）：</td>
					<td><input type="file" name="pdf_file"><br/></td>
				</tr>
				<tr>
					<td>作者：</td>
					<td><input type="text" id = "author" name="author" /></td>
				</tr>
				<tr>
					<td>所属类别：</td>
					<td><input type="text" id = "type" name="type" /></td>
				</tr>
				<tr>
					<td>出版发行：</td>
					<td><input type="text" id = "press" name="press" /></td>
				</tr>
				<tr>
					<td>isbn:</td>
					<td><input type="text" id = "isbn" name="isbn" /></td>
				</tr>
				<tr>
					<td>出版时间：</td>
					<td>
						<input type="text" placeholder="点击设置时间" id="pubTime" name="pubTime" value="" maxlength="100" onclick="SelectDate(this,'yyyy-MM-dd hh:mm')" readonly="readonly" style="width:160px;cursor:pointer;" /> 
					</td>
				</tr>
				<tr>
					<td>添加数量：</td>
					<td><input type="text" size="1" id = "allQuantity" name="allQuantity" />
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;"><input type="submit" value="确认添加" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" class="input_out"/></td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<font color="red">${msg }</font>
					</td>
				</tr>
			</table>
			</form>
			</div>
		</div>
	</div>
</body>
</html>