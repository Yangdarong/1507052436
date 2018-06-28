<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'category.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
        table{
            border-collapse: collapse;
            border-top:        2px black solid;
    		border-bottom:     2px black solid;       
        }
        td{
            padding: 3px 10px 3px 10px;
            border-top: thin black solid;
            border-bottom: thin black solid;
            border-left: thin black dotted;
            border-right: thin black dotted;
            text-align: center;
        }
        th{
		    background-color: rgb(81,130,187);
		    color: #fff;
		    padding:    0 15px 0 15px;
            border-left: thin black dotted;
            border-right: thin black dotted;
		    border-bottom:     thin black solid;
		    text-transform:     capitalize;
		}
		.evenline{
    		background-color: rgb(211,223,237);
		}
	</style>

  </head>
  <body>
  <h1>${title}的查询结果</h1>
  <table>
  <tr><th>书名</th><th>类别</th><th>作者</th><th>价格</th></tr>
  <s:iterator value="booklist" status="st">
  	<tr class="<s:if test='#st.even'>evenline</s:if>">
  	<td><a href="${pageContext.request.contextPath}/book_info?bookid=${id}">${name}</a>
  	</td>
  	<td>${category.name}</td>
  	<td>${author}</td>
  	<td>${price}</td>
  	</tr>
  </s:iterator>
  </table>
  <a href="${pageContext.request.contextPath}/entry">返回主页</a>
  </body>
</html>
