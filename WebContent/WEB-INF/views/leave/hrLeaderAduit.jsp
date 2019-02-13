<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假申请</title>
<!--框架必需start-->
<script type="text/javascript" src="../js/jquery-1.4.js"></script>
<script type="text/javascript" src="../js/framework.js"></script>
<script language="JavaScript" src="../js/form/datePicker/WdatePicker.js"></script>
<link href="../css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="../css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script src="../js/menu/contextmenu.js" type="text/javascript"></script>  
<script type="text/javascript" src="../js/tree/stree/stree.js"></script>
<link href="../js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form action="completeleavehr" target="frmright" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>请假类型：</td>
			<td>
				${leave.leavetype }
				<input type="hidden" name="leaveId" value="${leave.leaveId }">
				<input type="hidden" name="taskId" value="${taskId }">
			</td>
		</tr>
		<tr>
			<td>开始时间：</td>
			<td>
				${leave.leavestarttime }
			</td>
		</tr>
		<tr>
			<td>结束时间：</td>
			<td>
				${leave.leaveendtime }
			</td>
		</tr>
		<tr>
			<td>请假天数：</td>
			<td>
				${leave.leaveday }
			</td>
		</tr>
		<tr>
			<td>原因：</td>
			<td>
				${leave.reason }
			</td>
		</tr>
		<tr>
			<td>选择：</td>
			<td>
				<input type="radio" name="hrLeaderPass" value="1" checked>同意
				<input type="radio" name="hrLeaderPass" value="0">不同意
			</td>
		</tr>
		<tr>
			<td>
				审批意见：
			</td>
			<td>
				<textarea rows="10" cols="50" name="approvation"></textarea>
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
<table class="tableStyle" transMode="true">
					<tr>
						<th width="30">审批人</th><th width="100">审批时间</th><th width="100">审批意见</th>
					</tr>
					<c:forEach items="${applist }" var="a">
						<tr>
							<td>${a.approvationusername }</td>
							<td>${a.approvationtime }</td>
							<td>${a.approvation }</td>
						</tr>
					</c:forEach>
				</table>
</body>
</html>