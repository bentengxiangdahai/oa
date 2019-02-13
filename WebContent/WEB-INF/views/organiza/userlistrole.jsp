<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*,org.springframework.data.domain.*"
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
function lookuser(userId){
	var diag = new top.Dialog();
	diag.Title = "查看用户";
	diag.Width=220;
	diag.Height=300;
	diag.URL = "lookuser?userId="+userId;
	diag.show();
}
</script>
</head>
<body>
<div id="scrollContent">
	<table class="tableStyle" >
		<tr>
			<th>姓名</th><th>邮箱</th><th>电话</th><th>学历</th>
		</tr>
		<%
			Page<RoleUser> p=(Page<RoleUser>)request.getAttribute("page");
			List<User> list=(List<User>)request.getAttribute("list");
			int currentPage=(Integer)request.getAttribute("currentPage");
			for(User u:list){
		%>
		<tr>
			<td><a href="#" ondblclick=lookuser('<%=u.getUserId() %>')><%=u.getUsername() %></a></td><td><%=u.getEmail() %></td><td><%=u.getTel() %></td><td><%=u.getXueli() %></td>
		</tr>
		<%
			}
		%>
	</table>
</div>
<div style="height:35px;">
	<div class="float_right padding5 paging">
		<div class="float_left padding_top4">
			<%
				if(p.hasPreviousPage()){
			%>
				<span>			
				<a href="queryuserbyrole?roleId=${roleId }&nowpage=<%=currentPage-1%>">上一页</a>
				</span>
			<%
				}else{
			%>
				<span  class="paging_disabled">
				<a href="javascript:;">上一页</a>
				</span>
			<%
				}
			%>
		<%
			int totalPage=p.getTotalPages();
			if(totalPage>5){
				
		%>
		<span><a href="queryuserbyrole?roleId=${roleId }&nowpage=0">1</a></span>
		<span><a href="queryuserbyrole?roleId=${roleId }&nowpage=1">2</a></span>
		<span><a href="queryuserbyrole?roleId=${roleId }&nowpage=2">3</a></span>
		<span><a href="queryuserbyrole?roleId=${roleId }&nowpage=3">4</a></span>
		<span><a href="queryuserbyrole?roleId=${roleId }&nowpage=4">5</a></span>
		<span>...</span>
		<span><a href="queryuserbyrole?roleId=${roleId }&nowpage=<%=totalPage-1 %>"><%=totalPage %></a></span>
		<%
			}else{
				for(int i=0;i<totalPage;i++){
		%>
			<span><a href="queryuserbyrole?roleId=${roleId }&nowpage=<%=i%>"><%=(i+1) %></a></span>
		<%
				}
			}
		%>
			<%
				if(p.hasNextPage()){
			%>
				<span>
				<a href="queryuserbyrole?roleId=${roleId }&nowpage=<%=currentPage+1%>">下一页</a>
				</span>
			<%
				}else{
			%>
				<span class="paging_disabled">		
				<a href="javascript:;">下一页</a>
				</span>
			<%
				}
			%>
		当前
		</div>
		
		<div class="float_left padding_top4">${currentPage+1 }页</div>
		<div class="clear"></div>
	</div>
	<div class="clear"></div>
</div>			
</body>
</html>