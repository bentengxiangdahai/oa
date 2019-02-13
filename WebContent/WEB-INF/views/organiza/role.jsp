<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
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
                        { text: "添加角色", icon: "../oa/images/icons/ico1.gif", alias: "1-1", action: addRole },
                        { text: "删除角色", icon: "../oa/images/icons/ico2.gif", alias: "1-2", action: deleteRole },
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
				frmrightChild.src ="queryuserbyrole?roleId="+s;
			}
		});
		$(".simpleTree li span").contextmenu(option);
    });
	 function adduser() {
		var diag = new top.Dialog();
		diag.Title = "添加用户";
		diag.Width=220;
		diag.Height=300;
		diag.URL = "getusercomponenttorole?roleId="+currentTreeItem;
		diag.show();
    }
	 function addRole(){
		if(typeof(currentTreeItem)=='undefined'){
			currentTreeItem='root';
		}
		var diag = new top.Dialog();
		diag.Title = "添加角色";
		diag.Width=220;
		diag.Height=100;
		diag.URL = "toaddrole?parentId="+currentTreeItem;
		diag.show();
	 }
	 function deleteRole(){
		 top.Dialog.confirm("您确定要删除吗？",
			function(){
			 	var url="deleterole";
				var para={roleId:currentTreeItem,ts:new Date().getTime()};
				$.ajax({
					url: url,
					type: 'POST',
					data:para,
					dataType: 'text',
					success: function(result){
						alert(result)
					}
				});
			}
		 );
	 }
</script>
</head>
<body>
<div id="deletediv" style="display: none">
<form action="deleterole" id="deleterole">
	<input type="hidden" id="deleteroleId" name="deleteroleId">
</form>
</div>
<div id="scrollContent">
	<table width="100%">
		<tr>
			<td width="220" class="ver01">
				<ul class="simpleTree">
					<li id='root' class="root" ><span>根角色</span>
						<ul>
							<%
								List<Role> list=(List<Role>)request.getAttribute("list");
								for(Role d:list){
							%>
							<li id=<%=d.getRoleId() %>><span><%=d.getRolename() %></span>
								<ul class="ajax">
									<li id='4'>{url:queryrolelist?parentId=<%=d.getRoleId() %>}</li>
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