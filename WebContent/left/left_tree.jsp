<%@page import="org.beifeng.oa.entity.FunctionTable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.4.js"></script>
<script type="text/javascript" src="../js/framework.js"></script>
<link href="../css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin"/>
<!--框架必需end-->
<script type="text/javascript" src="../js/tree/dtree/dtree.js"></script>
<link href="../js/tree/dtree/dtree.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<body leftFrame="true">
	<div style="text-align:center;" >
	<br />
	<a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
	</div>
	<div id="scrollContent">
	<script type="text/javascript">
		<!--

		d = new dTree('d');
		d.add(0,-1,'菜单');
		<%
			List<FunctionTable> funclist=(List<FunctionTable>)session.getAttribute("funclist");
			for(FunctionTable func:funclist){
		%>
			d.add(<%=func.getFunctionId()%>,<%=func.getParentId()%>,'<%=func.getFunctionname()%>','<%=func.getUrl()%>','','frmright');
		<%
			}
		%>
		document.write(d);

		//-->
	</script>
</body>
</html>