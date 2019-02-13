<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<td>请假类型</td>
		<td>开始时间</td>
		<td>结束时间</td>
		<td>天数</td>
		<td>流程查看</td>
	</tr>
	<c:forEach items="${list }" var="l">
		<tr>
		<td>${l.leavetype }</td>
		<td>${l.leavestarttime }</td>
		<td>${l.leaveendtime }</td>
		<td>${l.leaveday }</td>
		<td>查看</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>