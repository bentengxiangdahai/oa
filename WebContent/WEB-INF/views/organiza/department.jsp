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
<script src="../oa/js/menu/contextmenu.js" type="text/javascript"></script>  
<script type="text/javascript" src="../oa/js/tree/stree/stree.js"></script>
<link href="../oa/js/tree/stree/stree.css" rel="stylesheet" type="text/css"/>
<!--去除链接虚线start-->
<style>
a {
	behavior:url(../oa/js/method/focus.htc)
}
</style>
<!--去除链接虚线end-->
<script type="text/javascript">
	var simpleTreeCollection;
	var currentTreeItem;
    $(function() {
        var option = { width: 150, items: [
                        { text: "添加部门", icon: "../oa/images/icons/ico1.gif", alias: "1-1", action: addDepartment },
                        { text: "删除部门", icon: "../oa/images/icons/ico2.gif", alias: "1-2", action: deleteDepartment },
                        { text: "添加用户", icon: "../oa/images/icons/ico3.gif", alias: "1-3", action: adduser }
                        ]
        };
		
		simpleTreeCollection = $('.simpleTree').simpleTree({
			autoclose: false,
			animate:true,
			afterAjax:function()
			{
				 $(".simpleTree li span").contextmenu(option);
			},
			afterContextMenu:function(node){
				var s=node[0].id;
				currentTreeItem=s;
			},
			afterClick:function(node){
				var s=node[0].id;
				var frmrightChild=$("#frmrightChild")[0];
				frmrightChild.src ="queryuser?departmentId="+s;
			}
		});
		$(".simpleTree li span").contextmenu(option);
    });
	 function adduser() {
		var diag = new top.Dialog();
		diag.Title = "添加部门";
		diag.Width=220;
		diag.Height=300;
		diag.URL = "toadd?parentId="+currentTreeItem;
		diag.show();
    }
	 function addDepartment(){
		if(typeof(currentTreeItem)=='undefined'){
			currentTreeItem='root';
		}
		var diag = new top.Dialog();
		diag.Title = "添加部门";
		diag.Width=220;
		diag.Height=100;
		diag.URL = "toadddepartment?parentId="+currentTreeItem;
		diag.show();
	 }
	 function deleteDepartment(){
		 top.Dialog.confirm("您确定要删除吗？",
			function(){
				$("#deletedepartmentId").val(currentTreeItem);
				$("#deleteDepartment").submit();
			}
		 );
	 }
</script>
</head>
<body>
<div id="deletediv" style="display: none">
<form action="deleteDepartment" id="deleteDepartment">
	<input type="hidden" id="deletedepartmentId" name="deletedepartmentId">
</form>
</div>
<div id="scrollContent">
	<table width="100%">
		<tr>
			<td width="220" class="ver01">
				<ul class="simpleTree">
					<li id='root' class="root" ><span>公司</span>
						<ul>
							<%
								List<Department> list=(List<Department>)request.getAttribute("list");
								for(Department d:list){
							%>
							<li id=<%=d.getDepartmentId() %>><span><%=d.getDepartmentname() %></span>
								<ul class="ajax">
									<li id='4'>{url:querydepartlist?parentId=<%=d.getDepartmentId() %>}</li>
								</ul>
							</li>	
							<%
								}
							%>	
						</ul>
					</li>
				</ul>
			</td>
			<td class="ver01">
				<div class="box1">
					<IFRAME scrolling="no" width="100%" frameBorder=0 id=frmrightChild name=frmrightChild onload="iframeHeight('frmrightChild')" src=""  allowTransparency="true"></IFRAME>
				</div>
			</td>
		</tr>
	</table>
</div>	
</body>
</html>