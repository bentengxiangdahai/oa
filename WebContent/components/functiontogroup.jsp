<%@page import="org.beifeng.oa.entity.UserDepartmentMapping"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.vo.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能点组件</title>
<!--框架必需start-->
<script type="text/javascript" src="../js/jquery-1.4.js"></script>
<script type="text/javascript" src="../js/framework.js"></script>
<link href="../css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="../css/skins/sky/import_skin.css" rel="stylesheet" type="text/css" themeColor="blue"/>
<!--框架必需end-->
<script type="text/javascript" src="../js/tree/dtree/dtree.js"></script>
<link href="../js/tree/dtree/dtree.css" rel="stylesheet" type="text/css"/>
<!--去除链接虚线start-->
<style>
a {
	behavior:url(../js/method/focus.htc)
}
</style>
<!--去除链接虚线end-->
</head>
<body>
<div id="checkTreeContainer">
<script type="text/javascript">
	mytree2 = new dTree('mytree2');
	mytree2.config.useCheckbox = true;
	<%
		List<TreeNode> list=(List<TreeNode>)session.getAttribute("treedata");
		for(TreeNode tree:list){
	%>
		mytree2.add(<%=tree.getId()%>,<%=tree.getParentid()%>,'<%=tree.getName()%>','<%=tree.getUrl()%>');
	<%
		}
	%>
	document.write(mytree2);
	
	function add(){
		var msg="";
		$("#checkTreeContainer input[type=checkbox]").each(function(){
			if($(this).attr("checked")){
				msg=msg+","+$(this).attr("trueiddata");
			}
		})
		if(msg==""){
			msg="请选择功能点"
			top.Dialog.alert(msg);
			return;
		}else{
			$("#funcs").val(msg);
			document.getElementById('addfunctiontogroup').submit();
			//top.Dialog.close();
			/*
			var url="addusertorole";
			var roleId=$("#roleId").val();
			var para={users:msg,roleId:roleId,ts:new Date().getTime()};
			$.ajax({
				url: url,
				type: 'POST',
				data:para,
				dataType: 'text',
				success: function(result){
					if(result=='ok'){
						top.Dialog.close();
					}
				}
			});
			*/
		}
		
	}
</script>
<input type='button' value='提交' onclick='add()'>
<form id='addfunctiontogroup' action="/oa/addfunctiontogroup" method='post'>
	<input type='hidden' id='funcs' name='funcs'>
	<input type='hidden' name='groupId' value="${groupId }">
</form>
</div>
</body>
</html>