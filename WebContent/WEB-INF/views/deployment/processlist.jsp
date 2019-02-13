<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程部署</title>
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
<body>
<table class="tableStyle" >
	<tr>
		<td>流程定义Id</td>
		<td>流程部署Id</td>
		<td>名称</td>
		<td>KEY</td>
		<td>版本号</td>
		<td>XML</td>
		<td>图片</td>
	</tr>
	<c:forEach items="${processlist }" var="process">
		<td>${process.id }</td>
		<td>${process.deploymentId }</td>
		<td>${process.name }</td>
		<td>${process.key }</td>
		<td>${process.version }</td>
		<td><a href="lookprocessdefine?deploymentId=${process.deploymentId }&resourceName=${process.resourceName}" target="_blank">查看</a></td>
		<td><a href="lookprocessdefine?deploymentId=${process.deploymentId }&resourceName=${process.diagramResourceName}" target="_blank">查看</a></td>
	</c:forEach>
</table>
</body>
</html>