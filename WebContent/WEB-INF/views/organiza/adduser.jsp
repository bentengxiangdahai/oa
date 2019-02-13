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
			<td>用户姓名：</td><td><input type="text" name="username"/>
				<input type="hidden" name="departmentId" value="${departmentId }">
			</td>
		</tr>
		<tr>
			<td>用户邮箱：</td><td><input type="text" class=" validate[required,custom[email]]" name="email"/><span class="star">*</span></td>
		</tr>
		<tr>
			<td>用户电话：</td><td><input type="text" name="tel"/></td>
		</tr>
		<tr>
			<td>用户生日：</td><td><input type="text" class="date" name="birthday"/></td>
		</tr>
		<tr>
			<td>用户学历：</td><td>
				<select name="xueli">
				<%
					List<DataDictionary> list=(List<DataDictionary>)request.getAttribute("list");
					for(DataDictionary d:list){
				%>
					<option value="<%=d.getDatadictionary()%>"><%=d.getDatadictionary()%></option>
				<%
					}
				%>
				</select>
			</td>
		</tr>
		<tr>
			<td>主管领导：</td><td>
			<input type="radio" name="usertype" value="0" checked/>否
			<input type="radio" name="usertype" value="1"/>是
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