<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../oa/js/jquery-1.4.js"></script>
<script type="text/javascript" src="../oa/js/framework_light.js"></script>
<link href="../oa/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="../oa/css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<script type="text/javascript">
function add(){
	window.location.href="toaddgroup";
}
function querygroup(){
	var groupquery=$("#groupquery").val();
	if(groupquery==''){
		
	}else{
		$("#querygroupname").val(groupquery);
		document.getElementById('querygroup').submit();
	}
}
function fenpeiuser(groupId){
	var diag = new top.Dialog();
	diag.Title = "分配用户";
	diag.Width=220;
	diag.Height=300;
	diag.URL = "getusercomponenttogroup?groupId="+groupId;
	diag.show();
}
function fuquan(groupId){
	var diag = new top.Dialog();
	diag.Title = "分配权限";
	diag.Width=220;
	diag.Height=300;
	diag.URL = "getfunctiontogroup?groupId="+groupId;
	diag.show();
}
</script>
</head>
<body>
<form action="querygroup" id="querygroup" method="post">
	<input type="hidden" id="querygroupname" name="querygroupname">
</form>
<div class="box2" panelTitle="功能面板" roller="false">
		<table>
			<tr>
				<td>权限组：</td>
				<td><input type="text" id="groupquery"/></td>
				<td><input type='button' value='查询' onclick="querygroup()"></td>
				<td><input type='button' value='新增' onclick='add()'></td>
			</tr>
		</table>
</div>
<div id="scrollContent">
	<table class="tableStyle" >
		<tr>
			<th>权限组</th><th>给权限组赋权</th><th>给权限组分配用户</th>
		</tr>
		<%
			List<PermissionGroup> list=(List<PermissionGroup>)request.getAttribute("list");
			for(PermissionGroup u:list){
		%>
		<tr>
			<td><%=u.getGroupname() %></td><td><input type='button' value='赋权' onclick="fuquan('<%=u.getGroupId() %>')"></td><td><input type='button' value='分配用户' onclick="fenpeiuser('<%=u.getGroupId() %>')"></td>
		</tr>
		<%
			}
		%>
	</table>
</div>
</body>
</html>