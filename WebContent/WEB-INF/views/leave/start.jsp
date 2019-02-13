<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假申请</title>
<!--框架必需start-->
<script type="text/javascript" src="../oa/js/jquery-1.4.js"></script>
<script type="text/javascript" src="../oa/js/framework.js"></script>
<script language="JavaScript" src="../oa/js/form/datePicker/WdatePicker.js"></script>
<link href="../oa/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="../oa/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script src="../oa/js/menu/contextmenu.js" type="text/javascript"></script>  
<script type="text/javascript" src="../oa/js/tree/stree/stree.js"></script>
<link href="../oa/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form action="startleave" target="frmright" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>请假类型：</td>
			<td>
				<select name="leavetype">
					<c:forEach items="${leavetype }" var="type">
						<option value="${type.datadictionary }">${type.datadictionary }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>开始时间：</td>
			<td>
				<input type="text" name="leavestarttime" class="date"/>
			</td>
		</tr>
		<tr>
			<td>结束时间：</td>
			<td>
				<input type="text" name="leaveendtime" class="date"/>
			</td>
		</tr>
		<tr>
			<td>请假天数：</td>
			<td>
				<input type="text" name="leaveday"/>
			</td>
		</tr>
		<tr>
			<td>原因：</td>
			<td>
				<textarea rows="10" cols="20" name="reason"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value=" 提 交 "/>
				<input type="reset" value=" 重 置 "/>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>