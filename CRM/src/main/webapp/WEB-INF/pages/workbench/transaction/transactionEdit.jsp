<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

	<%@ include file="/WEB-INF/pages/commons/config.jsp"%>
	<script type="text/javascript">
		$(function () {

			$(".mydate").datetimepicker({
				language:'zh-CN',//语言
				format:'yyyy-mm-dd',//日期的格式
				minView:'month',//可以选择的最小视图
				initialDate:new Date(),//初始化显示的日期
				autoclose:true,//设置选择完日期或者时间之后，日否自动关闭日历
				todayBtn:true,//设置是否显示"今天"按钮,默认是false
				clearBtn:true//设置是否显示"清空"按钮，默认是false
			});

			//给"阶段"下拉框添加change事件
			$("#edit-stage").change(function () {

				var stageValue = $("#edit-stage option:selected").text();

				if(stageValue == ""){
					$("#edit-possibility").val("");
					return;
				}
				$.ajax({
					url:'workbench/transaction/getPossibilityByStage.do',
					data:{
						stageValue:stageValue
					},
					type:'post',
					dataType:'json',
					success:function (data) {
						$("#edit-possibility").val(data);
					}
				});
			});
			//当容器加载完成之后，对容器调用工具函数
			$("#edit-customerName").typeahead({
				source:function (jquery,process) {
					$.ajax({
						url:'workbench/transaction/queryCustomerNameByName.do',
						data:{
							customerName:jquery
						},
						type:'post',
						dataType: 'json',
						success:function (data) {
							process(data);
						}
					});
				}
			});

			$("#searchActivityTxt").keyup(function () {
				var activityName = this.value;
				$.ajax({
					url:'workbench/transaction/queryActivityByActivityName.do',
					data:{
						activityName:activityName,
					},
					type:'post',
					dataType:'json',
					success:function (data) {
						var htmlStr = "";
						$.each(data,function (index,obj) {
							htmlStr+="<tr>";
							htmlStr+="<td><input type=\"radio\" value=\""+obj.id+"\" activityName=\"" + obj.name+"\" name=\"activity\"/></td>";
							htmlStr+="<td>"+obj.name+"</td>";
							htmlStr+="<td>"+obj.startDate+"</td>";
							htmlStr+="<td>"+obj.endDate+"</td>";
							htmlStr+="<td>"+obj.owner+"</td>";
							htmlStr+="</tr>";
						});
						$("#tBody-activity").html(htmlStr);
					}
				});
			});

			$("#tBody-activity").on("click","input[type='radio']",function () {
				var id = this.value;
				var activityName=$(this).attr("activityName");

				$("#edit-activityId").val(id);
				$("#edit-activityName").val(activityName);

				$("#findMarketActivity").modal("hide");
			});

			$("#searchContactsTxt").keyup(function () {
				var contactsName = this.value;
				$.ajax({
					url:'workbench/transaction/queryContactsByContactsName.do',
					data:{
						contactsName:contactsName,
					},
					type:'post',
					dataType:'json',
					success:function (data) {
						var htmlStr = "";
						$.each(data,function (index,obj) {
							htmlStr+="<tr>";
							htmlStr+="<td><input type=\"radio\" value=\""+obj.id+"\" contactsName=\"" + obj.fullname+"\" name=\"contacts\"/></td>";
							htmlStr+="<td>"+obj.fullname+"</td>";
							htmlStr+="<td>"+obj.email+"</td>";
							htmlStr+="<td>"+obj.mphone+"</td>";
							htmlStr+="</tr>";
						});
						$("#tBody-contacts").html(htmlStr);
					}
				});
			})

			$("#tBody-contacts").on("click","input[type='radio']",function () {
				var id = this.value;
				var contactsName=$(this).attr("contactsName");

				$("#edit-contactsId").val(id);
				$("#edit-contactsName").val(contactsName);

				$("#findContacts").modal("hide");
			});


			//给"保存修改"按钮添加单击事件
			$("#saveEditTranBtn").click(function () {

				var id 			   =$("#edit-id").val();

				var owner          =$("#edit-owner").val();
				var money          =$.trim($("#edit-money").val());
				var name           =$.trim($("#edit-name").val());
				var expectedDate   =$("#edit-expectedDate").val();
				var customerName   =$.trim($("#edit-customerName").val());
				var stage          =$("#edit-stage").val();
				var type           =$("#edit-type").val();
				var source         =$("#edit-source").val();
				var activityId     =$("#edit-activityId").val();//
				var contactsId     =$("#edit-contactsId").val();//
				var description    =$.trim($("#edit-description").val());
				var contactSummary =$.trim($("#edit-contactSummary").val());
				var nextContactTime=$("#edit-nextContactTime").val();
				if(owner == ""){
					alert("所有者不能为空");
					return;
				}
				if(name == ""){
					alert("名称不能为空");
					return;
				}
				if(stage == ""){
					alert("阶段不能为空");
					return;
				}
				if(expectedDate == ""){
					alert("预计成交时间不能为空");
					return;
				}
				if(customerName == ""){
					alert("客户名称不能为空");
					return;
				}
				//发送请求
				$.ajax({
					url:'workbench/transaction/saveEditTran.do',
					data:{
						id             :id             ,
						owner          :owner          ,
						money          :money          ,
						name           :name           ,
						expectedDate   :expectedDate   ,
						customerName   :customerName   ,
						stage          :stage          ,
						type           :type           ,
						source         :source         ,
						activityId     :activityId     ,
						contactsId     :contactsId     ,
						description    :description    ,
						contactSummary :contactSummary ,
						nextContactTime:nextContactTime
					},
					type:'post',
					dataType:'json',
					success:function (data) {
						if(data.code=="1"){
							//跳转到交易主页面
							window.location.href="workbench/transaction/transactionIndex.do";
						}else{
							//提示信息
							alert(data.message);
						}
					}
				});
			});

			$("#cancelEditTranBtn").click(function () {
				window.history.back();
			});

		});
	</script>

</head>
<body>

	<!-- 查找市场活动 -->
	<div class="modal fade" id="findMarketActivity" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">查找市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
						    <input type="text" class="form-control" id="searchActivityTxt" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable3" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
							</tr>
						</thead>
						<tbody id="tBody-activity">
						<%--							<tr>--%>
<%--								<td><input type="radio" name="activity"/></td>--%>
<%--								<td>发传单</td>--%>
<%--								<td>2020-10-10</td>--%>
<%--								<td>2020-10-20</td>--%>
<%--								<td>zhangsan</td>--%>
<%--							</tr>--%>
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

	<!-- 查找联系人 -->
	<div class="modal fade" id="findContacts" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">查找联系人</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
						    <input type="text" class="form-control" id="searchContactsTxt" style="width: 300px;" placeholder="请输入联系人名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>邮箱</td>
								<td>手机</td>
							</tr>
						</thead>
						<tbody id="tBody-contacts">
<%--							<tr>--%>
<%--								<td><input type="radio" name="activity"/></td>--%>
<%--								<td>李四</td>--%>
<%--								<td>lisi@bjpowernode.com</td>--%>
<%--								<td>12345678901</td>--%>
<%--							</tr>--%>
<%--							<tr>--%>
<%--								<td><input type="radio" name="activity"/></td>--%>
<%--								<td>李四</td>--%>
<%--								<td>lisi@bjpowernode.com</td>--%>
<%--								<td>12345678901</td>--%>
<%--							</tr>--%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>


	<div style="position:  relative; left: 30px;">
		<h3>修改交易</h3>
	  	<div style="position: relative; top: -40px; left: 70%;">
			<button type="button" class="btn btn-primary" id="saveEditTranBtn">保存</button>
			<button type="button" class="btn btn-default" id="cancelEditTranBtn">取消</button>
		</div>
		<hr style="position: relative; top: -40px;">
	</div>

	<form class="form-horizontal" role="form" style="position: relative; top: -30px;">

		<input type="hidden" id="edit-id" value="${tran.id}">

		<div class="form-group">
			<label for="edit-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="edit-owner">
					<option> </option>
					<c:forEach items="${userList}" var="u">
						<option value="${u.id}">${u.name}</option>
					</c:forEach>
				</select>
			</div>
			<label for="edit-money" class="col-sm-2 control-label">金额</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-money" value="${tran.money}">
			</div>
		</div>

		<div class="form-group">
			<label for="edit-name" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-name" value="${tran.name}">
			</div>
			<label for="edit-expectedDate" class="col-sm-2 control-label">预计成交日期<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control mydate" id="edit-expectedDate" value="${tran.expectedDate}">
			</div>
		</div>

		<div class="form-group">
			<label for="edit-customerName" class="col-sm-2 control-label">客户名称<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-customerName" placeholder="支持自动补全，输入客户不存在则新建" value="${tran.customerId}">
			</div>
			<label for="edit-stage" class="col-sm-2 control-label">阶段<span style="font-size: 15px; color: red;">*</span></label>
			<div class="col-sm-10" style="width: 300px;">
			  <select class="form-control" id="edit-stage">
				  <option></option>
				  <c:forEach items="${stageList}" var="st">
					  <option value="${st.id}">${st.value}</option>
				  </c:forEach>
			  </select>
			</div>
		</div>

		<div class="form-group">
			<label for="edit-type" class="col-sm-2 control-label">类型</label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="edit-type">
					<option></option>
					<c:forEach items="${transactionTypeList}" var="tt">
						<option value="${tt.id}">${tt.value}</option>
					</c:forEach>
				</select>
			</div>
			<label for="edit-possibility" class="col-sm-2 control-label">可能性</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-possibility" >
			</div>
		</div>

		<div class="form-group">
			<label for="edit-source" class="col-sm-2 control-label">来源</label>
			<div class="col-sm-10" style="width: 300px;">
				<select class="form-control" id="edit-source">
					<option></option>
					<c:forEach items="${sourceList}" var="sl">
						<option value="${sl.id}">${sl.value}</option>
					</c:forEach>
				</select>
			</div>
			<label for="edit-activityName" class="col-sm-2 control-label">市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" data-toggle="modal" data-target="#findMarketActivity"><span class="glyphicon glyphicon-search"></span></a></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-activityName"value="${tran.activityId}">
				<input type="hidden" id="edit-activityId">
			</div>
		</div>

		<div class="form-group">
			<label for="edit-contactsName" class="col-sm-2 control-label">联系人名称&nbsp;&nbsp;<a href="javascript:void(0);" data-toggle="modal" data-target="#findContacts"><span class="glyphicon glyphicon-search"></span></a></label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control" id="edit-contactsName" value="${tran.contactsId}">
				<input type="hidden" id="edit-contactsId">
			</div>
		</div>

		<div class="form-group">
			<label for="edit-description" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-10" style="width: 70%;">
				<textarea class="form-control" rows="3" id="edit-description">${tran.description}</textarea>
			</div>
		</div>

		<div class="form-group">
			<label for="edit-contactSummary" class="col-sm-2 control-label">联系纪要</label>
			<div class="col-sm-10" style="width: 70%;">
				<textarea class="form-control" rows="3" id="edit-contactSummary" >${tran.contactSummary}</textarea>
			</div>
		</div>

		<div class="form-group">
			<label for="edit-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
			<div class="col-sm-10" style="width: 300px;">
				<input type="text" class="form-control mydate" id="edit-nextContactTime" value="${tran.nextContactTime}" readonly>
			</div>
		</div>

	</form>
</body>
</html>
