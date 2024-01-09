<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<%@ include file="/WEB-INF/pages/commons/config.jsp"%>

	<script type="text/javascript">
		$(function () {

			//给"全选"按钮添加单击事件
			$("#checkAll").click(function () {
				$("#tBody input[type='checkbox']").prop("checked",this.checked);
			});

			$("#tBody").on("click","input[type='checkbox']",function () {
				//如果列表中的所有checkbox都选中，则"全选"按钮也选中
				if($("#tBody input[type='checkbox']").size()==$("#tBody input[type='checkbox']:checked").size()){
					$("#checkAll").prop("checked",true);
				}else{//如果列表中的所有checkbox至少有一个没选中，则"全选"按钮也取消
					$("#checkAll").prop("checked",false);
				}
			});

			$.ajax({
				url:'settings/dictionary/value/queryAllDicValue.do',
				type:'post',
				dateType: 'json',
				success:function (data) {
					var htmlStr = "";
					var num = 1;
					$.each(data.dicValueList,function (index,obj) {
						htmlStr+="<tr class=\"active\">";
						htmlStr+="<td><input type=\"checkbox\" value=\""+obj.id+"\"/></td>";
						htmlStr+="<td>"+ num++ +"</td>";
						htmlStr+="<td>"+obj.value+"</td>";
						htmlStr+="<td>"+obj.text+"</td>";
						htmlStr+="<td>"+obj.orderNo+"</td>";
						htmlStr+="<td>"+obj.typeCode+"</td>";
						htmlStr+="</tr>";
					});
					$("#tBody").html(htmlStr);
					//取消"全选"按钮
					$("#checkAll").prop("checked",false);
				}
			});
		})
	</script>
</head>
<body>

	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>字典值列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button type="button" class="btn btn-primary" onclick="window.location.href='transactionSave.jsp'"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button type="button" class="btn btn-default" onclick="window.location.href='transactionEdit.jsp'"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" /></td>
					<td>序号</td>
					<td>字典值</td>
					<td>文本</td>
					<td>排序号</td>
					<td>字典类型编码</td>
				</tr>
			</thead>
			<tbody id="tBody">

			</tbody>
		</table>
	</div>

</body>
</html>
