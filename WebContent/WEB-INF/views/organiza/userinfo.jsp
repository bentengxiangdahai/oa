<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>组织机构管理</title>
<!--框架必需start-->
<script type="text/javascript" src="../oa/js/jquery-1.4.js"></script>
<script type="text/javascript" src="../oa/js/framework.js"></script>
<link href="../oa/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="../oa/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script src="../oa/js/form/validationEngine-cn.js" type="text/javascript"></script>
<script src="../oa/js/form/validationEngine.js" type="text/javascript"></script>
<script src="../oa/js/menu/contextmenu.js" type="text/javascript"></script>  
<script type="text/javascript" src="../oa/js/tree/stree/stree.js"></script>
<script language="JavaScript" src="../oa/js/form/datePicker/WdatePicker.js"></script>
<link href="../oa/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form action="adduser" target="frmright" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>用户姓名：</td><td>${user.username }
				<input type="hidden" name="departmentId" value="${departmentId }">
			</td>
		</tr>
		<tr>
			<td>用户邮箱：</td><td>${user.email }</td>
		</tr>
		<tr>
			<td>用户电话：</td><td>${user.tel }</td>
		</tr>
		<tr>
			<td>用户生日：</td><td>${user.birthday }</td>
		</tr>
		<tr>
			<td>用户学历：</td><td>${user.xueli }</td>
		</tr>
	</table>
	</form>	
</body>
</html>