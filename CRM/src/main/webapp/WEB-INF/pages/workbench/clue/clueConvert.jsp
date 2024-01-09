<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<%@ include file="/WEB-INF/pages/commons/config.jsp"%>


	<script type="text/javascript">
	$(function(){

		$(".mydate").datetimepicker({
			language:'zh-CN',//语言
			format:'yyyy-mm-dd',//日期的格式
			minView:'month',//可以选择的最小视图
			initialDate:new Date(),//初始化显示的日期
			autoclose:true,//设置选择完日期或者时间之后，日否自动关闭日历
			todayBtn:true,//设置是否显示"今天"按钮,默认是false
			clearBtn:true//设置是否显示"清空"按钮，默认是false
		});

		$("#isCreateTransaction").click(function(){
			if(this.checked){
				$("#create-transaction2").show(200);
			}else{
				$("#create-transaction2").hide(200);
			}
		});

		$("#searchActivityBtn").click(function () {
			$("#searchActivityTxt").val("");
			$("#tBody").html("");
			$("#searchActivityModal").modal("show");
		});

		$("#searchActivityTxt").keyup(function () {

			var activityName = this.value;
			var clueId='${clue.id}';
			$.ajax({
				url:'workbench/clue/queryActivityForConvertByNameClueId.do',
				data:{
					activityName:activityName,
					clueId:clueId
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					var htmlStr="";
					$.each(data,function (index,obj) {
						htmlStr+="<tr>";
						htmlStr+="<td><input type=\"radio\" value=\""+obj.id+"\" activityName=\"" + obj.name+"\" name=\"activity\"/></td>";
						htmlStr+="<td>"+obj.name+"</td>";
						htmlStr+="<td>"+obj.startDate+"</td>";
						htmlStr+="<td>"+obj.endDate+"</td>";
						htmlStr+="<td>"+obj.owner+"</td>";
						htmlStr+="</tr>";
					});
					$("tBody").html(htmlStr);
				}
			});
		});

		$("#tBody").on("click","input[type='radio']",function () {
			var id = this.value;
			var activityName=$(this).attr("activityName");

			$("#activityId").val(id);
			$("#activityName").val(activityName);

			$("#searchActivityModal").modal("hide");
		});


		$("#saveConvertClueBtn").click(function () {

			var clueId = '${clue.id}';
			var money = $.trim($("#amountOfMoney").val());
			var name = $.trim($("#tradeName").val());
			var expectedDate = $("#expectedClosingDate").val();
			var stage = $("#stage").val();
			var activityId = $("#activityId").val();
			var isCreateTran = $("#isCreateTransaction").prop("checked");
			$.ajax({
				url:'workbench/clue/convertClue.do',
				data:{
					clueId:clueId,
					money:money,
					name:name,
					expectedDate:expectedDate,
					stage:stage,
					activityId:activityId,
					isCreateTran:isCreateTran
				},
				type: 'post',
				dataType: 'json',
				success:function (data) {
					if(data.code== "1"){
						window.location.href = "workbench/clue/clueIndex.do";
					}else {
						alert(data.message);
					}
				}
			});
		});

		$("#cancelConvertClueBtn").click(function () {
			window.history.back();
		});

	});
</script>

</head>
<body>

	<!-- 搜索市场活动的模态窗口 -->
	<div class="modal fade" id="searchActivityModal" role="dialog" >
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">搜索市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
						    <input  id="searchActivityTxt" type="text" class="form-control" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
								<td></td>
							</tr>
						</thead>
						<tbody id="tBody">

<%--							<tr>--%>
<%--								<td><input type="radio" name="activity"/></td>--%>
<%--								<td>发传单</td>--%>
<%--								<td>2020-10-10</td>--%>
<%--								<td>2020-10-20</td>--%>
<%--								<td>zhangsan</td>--%>
<%--							</tr>--%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="title" class="page-header" style="position: relative; left: 20px;">
		<h4>转换线索 <small>${clue.fullname}${clue.appellation}-${clue.company}</small></h4>
	</div>
	<div id="create-customer" style="position: relative; left: 40px; height: 35px;">
		新建客户：${clue.company}
	</div>
	<div id="create-contact" style="position: relative; left: 40px; height: 35px;">
		新建联系人：${clue.fullname}${clue.appellation}
	</div>
	<div id="create-transaction1" style="position: relative; left: 40px; height: 35px; top: 25px;">
		<input type="checkbox" id="isCreateTransaction"/>
		为客户创建交易
	</div>
	<div id="create-transaction2" style="position: relative; left: 40px; top: 20px; width: 80%; background-color: #F7F7F7; display: none;" >

		<form>
		  <div class="form-group" style="width: 400px; position: relative; left: 20px;">
		    <label for="amountOfMoney">金额</label>
		    <input type="text" class="form-control" id="amountOfMoney">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="tradeName">交易名称</label>
		    <input type="text" class="form-control" id="tradeName" value="${clue.company}-">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="expectedClosingDate">预计成交日期</label>
		    <input type="text" class="form-control mydate" name="mydate" id="expectedClosingDate" readonly>
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="stage">阶段</label>
		    <select id="stage"  class="form-control">
		    	<option></option>
				<c:forEach items="${stageList}" var="s">
					<option value="${s.id}">${s.value}</option>
				</c:forEach>
		    </select>
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
			  <label for="activityName">市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" id="searchActivityBtn" style="text-decoration: none;"><span class="glyphicon glyphicon-search"></span></a></label>
			  <input type="hidden" id="activityId">
			  <input type="text" class="form-control" id="activityName" placeholder="点击上面搜索" readonly>
		  </div>
		</form>

	</div>

	<div id="owner" style="position: relative; left: 40px; height: 35px; top: 50px;">
		记录的所有者：<br>
		<b>${clue.owner}</b>
	</div>
	<div id="operation" style="position: relative; left: 40px; height: 35px; top: 100px;">
		<input class="btn btn-primary" type="button" id="saveConvertClueBtn" value="转换">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="btn btn-default" type="button" id="cancelConvertClueBtn" value="取消">
	</div>
</body>
</html>
