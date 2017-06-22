<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>人事管理系统——修改订单项</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	
	$(function(){
    	/** 员工表单提交 */
		$("#userForm").submit(function(){
			var number = $("#number");
			var pid = $("#pid");
			var oprice = $("#oprice");
			var remark = $("#remark");
			var msg = "";
			if ($.trim(number.val()) == ""){
				msg = "数量不能为空！";
				username.focus();
			}else if ($.trim(pid.val()) == ""){
				msg = "产品不能为空！";
				status.focus();
			}else if ($.trim(oprice.val()) == ""){
				msg = "单价不能为空！";
				loginname.focus();
			}else if ($.trim(remark.val()) == ""){
				msg = "备注不能为空！";
				password.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#userForm").submit();
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：订单管理  &gt; 修改订单</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/orderItem/updateOrderItem" id="userForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
			<input type="hidden" name="id" value="${orderItem.id }">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">所属订单：<input type="text" name="oid" id="oid" size="20" value="${orderItem.oid }"/></td>
		    			<td class="font3 fftd">产品：<input type="text" name="pid" id="pid" size="20" value="${orderItem.pid }"/></td>
		    			<td class="font3 fftd">数量：<input type="text" name="number" id="number" size="20" value="${orderItem.number }"/></td>
		    			<td class="font3 fftd">规格：<input type="text" name="specification" id="specification" size="20" value="${orderItem.specification }"/></td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">单位：<input name="uid" id="uid" size="20" value="${orderItem.uid }"/></td>
		    			<td class="font3 fftd">单价：<input name="oprice" id="oprice" size="20" value="${orderItem.oprice }"/></td>
		    			<td class="font3 fftd">金额：<input name="sprice" id="sprice" size="20" value="${orderItem.sprice }"/></td>
		    			<td class="font3 fftd">备注：<input name="remark" id="remark" size="20" value="${orderItem.remark }"/></td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="修改 ">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>