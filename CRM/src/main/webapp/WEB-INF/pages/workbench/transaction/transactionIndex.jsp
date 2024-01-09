<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

	<%@ include file="/WEB-INF/pages/commons/config.jsp"%>
<script type="text/javascript">

	$(function(){

		queryTranByConditionForPage(1,10);

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

		//给"创建"按钮添加单击事件
		$("#createTranBtn").click(function () {
			//发送同步请求
			window.location.href="workbench/transaction/toSave.do";
		});

		//给"删除"按钮添加单击事件
		$("#deleteContactsBtn").click(function () {
			var checkedIds = $("#tBody input[type='checkbox']:checked");
			if(checkedIds.size() == 0){
				alert("请选择要删除的线索");
				return;
			}
			if(window.confirm("确定要删除吗?")){
				var tranIds = "";
				$.each(checkedIds,function () {//id=xxxx&id=xxx&.....&id=xxx&
					tranIds += "id=" + this.value + "&";
				});
				tranIds = tranIds.substr(0,tranIds.length-1);//id=xxxx&id=xxx&.....&id=xxx
				//发送请求
				$.ajax({
					url:'workbench/transaction/deleteTranIds.do',
					data:tranIds,
					type:'post',
					dataType:'json',
					success:function (data) {
						if(data.code=="1"){
							//刷新市场活动列表,显示第一页数据,保持每页显示条数不变
							queryTranByConditionForPage(1,$("#demo_page_nav").bs_pagination('getOption', 'rowsPerPage'));
						}else{
							//提示信息
							alert(data.message);
						}
					}
				});
			}
		});
		//给"修改"按钮添加单击事件
		$("#editContactsBtn").click(function () {
			var checkedIds = $("#tBody input[type='checkbox']:checked");
			if(checkedIds.size() == 0){
				alert("请选择要修改的线索");
				return;
			}
			if(checkedIds.size()>1){
				alert("每次只能修改一条线索");
				return;
			}
			var id = checkedIds[0].value;
			//发送同步请求
			window.location.href="workbench/transaction/queryTranById.do?id="+id;
		});

	});
	function queryTranByConditionForPage(pageNo,pageSize) {

		//待完善 未添加查询

		//发送请求
		$.ajax({
			url:'workbench/transaction/queryTranByConditionForPage.do',
			data:{
				pageNo:pageNo,
				pageSize:pageSize
			},
			type:'post',
			dataType:'json',
			success:function (data) {

				var htmlStr="";
				$.each(data.tranList,function (index,obj) {
					htmlStr+="<tr class=\"active\">";
					htmlStr+="<td><input type=\"checkbox\" value=\""+obj.id+"\"/></td>";
					htmlStr+="<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='workbench/transaction/detailTran.do?id="+obj.id+"'\">"+obj.name+"</a></td>";
					htmlStr+="<td>"+obj.customerId+"</td>";
					htmlStr+="<td>"+obj.stage+"</td>";
					htmlStr+="<td>"+obj.type+"</td>";
					htmlStr+="<td>"+obj.owner+"</td>";
					htmlStr+="<td>"+obj.source+"</td>";
					htmlStr+="<td>"+obj.contactsId+"</td>";
					htmlStr+="</tr>";
				});
				$("#tBody").html(htmlStr);


				//取消"全选"按钮
				$("#checkAll").prop("checked",false);

				//计算总页数
				var totalPages=1;
				if(data.totalRows%pageSize==0){
					totalPages=data.totalRows/pageSize;
				}else{
					totalPages=parseInt(data.totalRows/pageSize)+1;
				}

				//对容器调用bs_pagination工具函数，显示翻页信息
				$("#demo_page_nav").bs_pagination({
					currentPage:pageNo,//当前页号,相当于pageNo

					rowsPerPage:pageSize,//每页显示条数,相当于pageSize
					totalRows:data.totalRows,//总条数
					totalPages: totalPages,  //总页数,必填参数.

					visiblePageLinks:5,//最多可以显示的卡片数

					showGoToPage:true,//是否显示"跳转到"部分,默认true--显示
					showRowsPerPage:true,//是否显示"每页显示条数"部分。默认true--显示
					showRowsInfo:true,//是否显示记录的信息，默认true--显示

					//用户每次切换页号，都自动触发本函数;
					//每次返回切换页号之后的pageNo和pageSize
					onChangePage: function(event,pageObj) { // returns page_num and rows_per_page after a link has clicked
						//js代码
						//alert(pageObj.currentPage);
						//alert(pageObj.rowsPerPage);
						queryTranByConditionForPage(pageObj.currentPage,pageObj.rowsPerPage);
					}
				});
			}
		});
	}

</script>
</head>
<body>



	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>交易列表</h3>
			</div>
		</div>
	</div>

	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">

		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">

			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text">
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text">
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">客户名称</div>
				      <input class="form-control" type="text">
				    </div>
				  </div>

				  <br>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">阶段</div>
					  <select class="form-control">
					  	<option></option>
						  <c:forEach items="${stageList}" var="s">
							  <option value="${s.id}">${s.value}</option>
						  </c:forEach>
					  </select>
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">类型</div>
					  <select class="form-control">
					  	<option></option>
						  <c:forEach items="${transactionTypeList}" var="tt">
							  <option value="${tt.id}">${tt.value}</option>
						  </c:forEach>
					  </select>
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">来源</div>
				      <select class="form-control" id="create-clueSource">
						  <option></option>
						  <c:forEach items="${sourceList}" var="so">
							  <option value="${so.id}">${so.value}</option>
						  </c:forEach>
						</select>
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">联系人名称</div>
				      <input class="form-control" type="text">
				    </div>
				  </div>

				  <button type="submit" class="btn btn-default">查询</button>

				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 10px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="createTranBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editContactsBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteContactsBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>


			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id = "checkAll"/></td>
							<td>名称</td>
							<td>客户名称</td>
							<td>阶段</td>
							<td>类型</td>
							<td>所有者</td>
							<td>来源</td>
							<td>联系人名称</td>
						</tr>
					</thead>
					<tbody id="tBody">

					</tbody>
				</table>
				<%--分页标签--%>
				<div id="demo_page_nav"></div>

			</div>


		</div>

	</div>
</body>
</html>
