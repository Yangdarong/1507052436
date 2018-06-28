<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  	<s:if test="#session.user != null">
  		欢迎你，${session.user.name}。<a href="${pageContext.request.contextPath}/user_logout">退出登陆</a>
  		<a href="${pageContext.request.contextPath}/user_listfavor">我的收藏</a>
  	</s:if>
  	<s:else>
  		请先<a href="${pageContext.request.contextPath}/login.jsp">登陆</a>
  	</s:else>
  	<h1>选择类别查看图书</h1>
  	<s:iterator value="category_list">
  		<a href="${pageContext.request.contextPath}/category_getbooks?id=${id}">${name}</a>
  	</s:iterator>
  	<br/>
  	<form action="${pageContext.request.contextPath}/book_search.action" method="POST">
  	<h1>模糊查找</h1>
  	<select name="type">
  		<option value="1">书名</option>
  		<option value="2">作者</option>
  	</select>
  	<input type="text" name="keyword"/><input type="submit" value="搜索"/>
  	</form>
  	<s:if test="#session.user.privilege > 0">
  	<h1>添加书本</h1>
  	<form action="book_add" method="POST">
  	书名：<input type="text" name="name"/><br/>
  	类别：<select name="categoryid">
  	  		<s:iterator value="category_list">
  				<option value="${id}">${name}</option>
  			</s:iterator>
  		</select><br/>
  	作者：<input type="text" name="author"/><br/>
  	价格：<input type="text" name="price"/><br/>
  	简介：<br/>
  	<textarea name="desc"></textarea><br/>
  	<input type="submit" value="添加"/>
  	</form>
  	</s:if>
  </body>
</html>
