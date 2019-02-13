<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假列表</title>
<!--框架必需start-->
<script type="text/javascript" src="../oa/js/jquery-1.4.js"></script>
<script type="text/javascript" src="../oa/js/framework.js"></script>
<link href="../oa/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="../oa/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script src="../oa/js/menu/contextmenu.js" type="text/javascript"></script>  
<script type="text/javascript" src="../oa/js/tree/stree/stree.js"></script>
<link href="../oa/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript">
function add(){
	window.location.href="tostartleave";
}
</script>
<body>
<div class="box2" panelTitle="功能面板" roller="false">
		<table>
			<tr>
				<td>原因：</td>
				<td><input type="text" id="reason"/></td>
				<td><input type='button' value='查询' onclick="queryleave()"></td>
				<td><input type='button' value='新增' onclick='add()'></td>
			</tr>
		</table>
</div>
<table class="tableStyle" >
	<tr>
		<td>标题1</td>
		<td>标题2</td>
		<td>业务</td>
		<td>当前任务节点</td>
		<td>任务创建时间</td>
		<td>流程状态</td>
		<td>操作</td>
	</tr>
	<c:set var="tp" value="${todopage }"></c:set>
	<c:forEach items="${tp.context }" var="l">
		<tr>
		<td>${l.column1 }</td>
		<td>${l.column2 }</td>
		<td>${l.businessvalue }</td>
		<td>${l.task.name }</td>
		<td><fmt:formatDate value="${l.task.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
		<td>${l.processins.suspended?"已挂起":"正常" }</td>
		<td>
			<c:if test="${empty l.task.assignee }">
				<a href="claim?taskId=${l.task.id }&currentpage=${tp.currentpage}">签收</a>
			</c:if>
			<c:if test="${not empty l.task.assignee }">
				<a href="detail?taskId=${l.task.id }">处理</a>
			</c:if>
		</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="7">
			共有${tp.totalcount }条记录,分为${tp.totalpage }页,当前是${tp.currentpage+1 }页
			<c:if test="${tp.haslast }">
				<a href="todolist?currentPage=0">首页</a>
				<a href="todolist?currentPage=${tp.currentpage-1 }">上一页</a>
			</c:if>
			<c:if test="${!tp.haslast }">
				首页
				上一页
			</c:if>
			<c:if test="${tp.hasnext }">
				<a href="todolist?currentPage=${tp.currentpage+1 }">下一页</a>
				<a href="todolist?currentPage=${tp.totalpage-1 }">尾页</a>
			</c:if>
			<c:if test="${!tp.hasnext }">
				下一页
				尾页
			</c:if>
		</td>
	</tr>
</table>
</body>
</html>