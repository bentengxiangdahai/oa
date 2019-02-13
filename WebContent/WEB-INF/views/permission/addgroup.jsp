<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加权限组</title>
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
<form action="addgroup" target="frmright" method="post">
		<table class="tableStyle" transMode="true">
		<tr>
			<td>权限组名称：</td><td><input type="text" name="groupname"/>
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