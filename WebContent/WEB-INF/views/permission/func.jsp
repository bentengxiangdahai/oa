<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,org.beifeng.oa.entity.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>功能点管理</title>
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
                        { text: "添加功能点", icon: "../oa/images/icons/ico1.gif", alias: "1-1", action: addfunc },
                        { text: "删除功能点", icon: "../oa/images/icons/ico2.gif", alias: "1-2", action: deletefunc },
                        { text: "修改功能点", icon: "../oa/images/icons/ico3.gif", alias: "1-3", action: updatefunc }
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
	 function updatefunc() {
		if(typeof(currentTreeItem)=='undefined'){
			alert('根节点不允许修改');
		}
		var diag = new top.Dialog();
		diag.Title = "修改功能点";
		diag.Width=220;
		diag.Height=300;
		diag.URL = "toupdatefunc?functionId="+currentTreeItem;
		diag.show();
    }
	 function addfunc(){
		if(typeof(currentTreeItem)=='undefined'){
			currentTreeItem='0';
		}
		var diag = new top.Dialog();
		diag.Title = "添加功能点";
		diag.Width=220;
		diag.Height=100;
		diag.URL = "toaddfunc?parentId="+currentTreeItem;
		diag.show();
	 }
	 function deletefunc(){
		 top.Dialog.confirm("您确定要删除吗？",
			function(){
			 	if(typeof(currentTreeItem)=='undefined'){
					alert('根节点不允许删除!');
					return;
				}
				$("#deletefunctionId").val(currentTreeItem);
				$("#deletefunction").submit();
			}
		 );
	 }
</script>
</head>
<body>
<div id="deletediv" style="display: none">
<form action="deletefunction" id="deletefunction">
	<input type="hidden" id="deletefunctionId" name="deletefunctionId">
</form>
</div>
<div id="scrollContent">
	<table width="100%">
		<tr>
			<td width="220" class="ver01">
				<ul class="simpleTree">
					<li id='root' class="root" ><span>根功能</span>
						<ul>
							<%
								List<FunctionTable> list=(List<FunctionTable>)request.getAttribute("list");
								for(FunctionTable d:list){
							%>
							<li id=<%=d.getFunctionId() %>><span><%=d.getFunctionname()%></span>
								<ul class="ajax">
									<li id='4'>{url:queryfunclist?parentId=<%=d.getFunctionId() %>}</li>
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