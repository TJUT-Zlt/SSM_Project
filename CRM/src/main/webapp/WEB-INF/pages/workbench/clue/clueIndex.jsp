<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<%@ include file="/WEB-INF/pages/commons/config.jsp"%>

	<script type="text/javascript">

		$(function(){


			queryClueByConditionForPage(1,10);

			//给"创建"按钮添加单击事件
			$("#createClueBtn").click(function () {
				//初始化工作
				$("#createClueForm")[0].reset();
				//弹出模态窗口
				$("#createClueModal").modal("show");
			});

			$(".mydate").datetimepicker({
				language:'zh-CN',//语言
				format:'yyyy-mm-dd',//日期的格式
				minView:'month',//可以选择的最小视图
				initialDate:new Date(),//初始化显示的日期
				autoclose:true,//设置选择完日期或者时间之后，日否自动关闭日历
				todayBtn:true,//设置是否显示"今天"按钮,默认是false
				clearBtn:true//设置是否显示"清空"按钮，默认是false
			});

			//给"保存"按钮添加单击事件
			$("#saveCreateClueBtn").click(function () {
				//收集参数
				var fullname       =$.trim($("#create-fullname").val());
				var appellation    =$("#create-appellation").val();
				var owner          =$("#create-owner").val();
				var company        =$.trim($("#create-company").val());
				var job            =$.trim($("#create-job").val());
				var email          =$.trim($("#create-email").val());
				var phone          =$.trim($("#create-phone").val());
				var website        =$.trim($("#create-website").val());
				var mphone         =$.trim($("#create-mphone").val());
				var state          =$("#create-state").val();
				var source         =$("#create-source").val();
				var description    =$.trim($("#create-description").val());
				var contactSummary =$.trim($("#create-contactSummary").val());
				var nextContactTime=$.trim($("#create-nextContactTime").val());
				var address        =$.trim($("#create-address").val());


				if(owner==""){
					alert("所有者不能为空");
					return;
				}

				if(company==""){
					alert("公司不能为空");
					return;
				}
				if(fullname==""){
					alert("姓名不能为空");
					return;
				}
				var emailTest = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				if (!emailTest.test(email)) {
					alert("邮箱格式不正确");
					return false;
				}


				$.ajax({
					url:'workbench/clue/saveCreateClue.do',
					data:{
						fullname       :fullname       ,
						appellation    :appellation    ,
						owner          :owner          ,
						company        :company        ,
						job            :job            ,
						email          :email          ,
						phone          :phone          ,
						website        :website        ,
						mphone         :mphone         ,
						state          :state          ,
						source         :source         ,
						description    :description    ,
						contactSummary :contactSummary ,
						nextContactTime:nextContactTime,
						address        :address
					},
					type:'post',
					dataType:'json',
					success:function (data) {
						if(data.code=="1"){
							//关闭模态窗口
							$("#createClueModal").modal("hide");
							//刷新线索列表，显示第一页数据，保持每页显示条数不变(作业)
							queryClueByConditionForPage(1,$("#demo_page_nav").bs_pagination('getOption', 'rowsPerPage'));

						}else{
							//提示信息
							alert(data.message);
							//模态窗口不关闭
							$("#createClueModal").modal("show");
						}
					}
				})
			});
			//给"查询"按钮添加单击事件
			$("#queryClueBtn").click(function () {
				//查询所有符合条件数据的第一页以及所有符合条件数据的总条数;
				queryClueByConditionForPage(1,$("#demo_page_nav").bs_pagination('getOption', 'rowsPerPage'));
			});

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


			//给"删除"按钮添加单击事件
			$("#deleteClueBtn").click(function () {
				var checkedIds = $("#tBody input[type='checkbox']:checked");
				if(checkedIds.size() == 0){
					alert("请选择要删除的线索");
					return;
				}
				if(window.confirm("确定要删除吗?")){
					var clueIds = "";
					$.each(checkedIds,function () {//id=xxxx&id=xxx&.....&id=xxx&
						clueIds += "id=" + this.value + "&";
					});
					clueIds = clueIds.substr(0,clueIds.length-1);//id=xxxx&id=xxx&.....&id=xxx

					//发送请求
					$.ajax({
						url:'workbench/clue/deleteClueIds.do',
						data:clueIds,
						type:'post',
						dataType:'json',
						success:function (data) {
							if(data.code=="1"){
								//刷新市场活动列表,显示第一页数据,保持每页显示条数不变
								queryClueByConditionForPage(1,$("#demo_page_nav").bs_pagination('getOption', 'rowsPerPage'));
							}else{
								//提示信息
								alert(data.message);
							}
						}
					});
				}
			});

			//给"修改"按钮添加单击事件
			$("#editClueBtn").click(function () {
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

				$.ajax({
					url:'workbench/clue/queryClueById.do',
					data:{id:id},
					type:'post',
					dataType:'json',
					success:function (data) {
						// 把市场活动的信息显示在修改的模态窗口上

						$("#edit-id").val(data.id);

						$("#edit-owner").val(data.owner);
						$("#edit-company").val(data.company);
						$("#edit-appellation").val(data.appellation);
						$("#edit-fullname").val(data.fullname);
						$("#edit-job").val(data.job);
						$("#edit-email").val(data.email);
						$("#edit-phone").val(data.phone);
						$("#edit-website").val(data.website);
						$("#edit-mphone").val(data.mphone);
						$("#edit-state").val(data.state);
						$("#edit-source").val(data.source);
						$("#edit-description").val(data.description);
						$("#edit-contactSummary").val(data.contactSummary);
						$("#edit-nextContactTime").val(data.nextContactTime);
						$("#edit-address").val(data.address);
						// 弹出模态窗口
						$("#editClueModal").modal("show");
					}
				});
			});

			//给"保存修改"按钮添加单击事件
			$("#saveEditCLueBtn").click(function () {

				var id 			   =$("#edit-id").val();

				var fullname       =$.trim($("#edit-fullname").val());
				var appellation    =$("#edit-appellation").val();
				var owner          =$("#edit-owner").val();
				var company        =$.trim($("#edit-company").val());
				var job            =$.trim($("#edit-job").val());
				var email          =$.trim($("#edit-email").val());
				var phone          =$.trim($("#edit-phone").val());
				var website        =$.trim($("#edit-website").val());
				var mphone         =$.trim($("#edit-mphone").val());
				var state          =$("#edit-state").val();
				var source         =$("#edit-source").val();
				var description    =$.trim($("#edit-description").val());
				var contactSummary =$.trim($("#edit-contactSummary").val());
				var nextContactTime=$.trim($("#edit-nextContactTime").val());
				var address        =$.trim($("#edit-address").val());

				$.ajax({
					url:'workbench/clue/saveEditClue.do',
					data:{
						id              :id            ,
						fullname       :fullname       ,
						appellation    :appellation    ,
						owner          :owner          ,
						company        :company        ,
						job            :job            ,
						email          :email          ,
						phone          :phone          ,
						website        :website        ,
						mphone         :mphone         ,
						state          :state          ,
						source         :source         ,
						description    :description    ,
						contactSummary :contactSummary ,
						nextContactTime:nextContactTime,
						address        :address
					},
					type:'post',
					dataType:'json',
					success:function (data) {
						if(data.code == "1"){
							//关闭模态窗口
							$("#editClueModal").modal("hide");
							//刷新市场活动列表,保持页号和每页显示条数都不变
							queryClueByConditionForPage($("#demo_page_nav").bs_pagination('getOption', 'currentPage'),$("#demo_page_nav").bs_pagination('getOption', 'rowsPerPage'));
						}else {
							//提示信息
							alert(data.message);
							//模态窗口不关闭
							$("#editClueModal").modal("show");
						}

					}
				})
			});



		});




		function queryClueByConditionForPage(pageNo,pageSize) {
			//收集参数
			var fullname=$.trim($("#query-fullname").val());
			var company=$.trim($("#query-company").val());
			var phone=$.trim($("#query-phone").val());
			var source=$("#query-source option:selected").text();
			var owner=$.trim($("#query-owner").val());
			var mphone=$.trim($("#query-mphone").val());

			var state=$("#query-state option:selected").text();

			//发送请求
			$.ajax({
				url:'workbench/clue/queryClueByConditionForPage.do',
				data:{
					fullname:fullname,
					company:company,
					phone:phone,
					source:source,
					owner:owner,
					mphone:mphone,
					state:state,
					pageNo:pageNo,
					pageSize:pageSize
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					var htmlStr="";
					$.each(data.clueList,function (index,obj) {
						htmlStr+="<tr class=\"active\">";
						htmlStr+="<td><input type=\"checkbox\" value=\""+obj.id+"\"/></td>";
						htmlStr+="<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='workbench/clue/detailClue.do?id="+obj.id+"'\">"+obj.fullname+obj.appellation+"</a></td>";
						htmlStr+="<td>"+obj.company+"</td>";
						htmlStr+="<td>"+obj.phone+"</td>";
						htmlStr+="<td>"+obj.mphone+"</td>";
						htmlStr+="<td>"+obj.source+"</td>";
						htmlStr+="<td>"+obj.owner+"</td>";
						htmlStr+="<td>"+obj.state+"</td>";
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
							queryClueByConditionForPage(pageObj.currentPage,pageObj.rowsPerPage);
						}
					});
				}
			});
		}

	</script>
</head>
<body>

	<!-- 创建线索的模态窗口 -->
	<div class="modal fade" id="createClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">创建线索</h4>
				</div>
				<div class="modal-body">
					<form id="createClueForm" class="form-horizontal" role="form">

						<div class="form-group">
							<label for="create-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">
									<c:forEach items="${userList}" var="u">
										<option value="${u.id}">${u.name}</option>
									</c:forEach>
								</select>
							</div>
							<label for="create-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-company">
							</div>
						</div>

						<div class="form-group">
							<label for="create-appellation" class="col-sm-2 control-label">称呼</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-appellation">
								  <option></option>
									<c:forEach items="${appellationList}" var="app">
										<option value="${app.id}">${app.value}</option>
									</c:forEach>
								</select>
							</div>
							<label for="create-fullname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-fullname">
							</div>
						</div>

						<div class="form-group">
							<label for="create-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-job">
							</div>
							<label for="create-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-email">
							</div>
						</div>

						<div class="form-group">
							<label for="create-phone" class="col-sm-2 control-label">公司座机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-phone">
							</div>
							<label for="create-website" class="col-sm-2 control-label">公司网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-website">
							</div>
						</div>

						<div class="form-group">
							<label for="create-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-mphone">
							</div>
							<label for="create-state" class="col-sm-2 control-label">线索状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-state">
								  <option></option>
									<c:forEach items="${clueStateList}" var="cs">
										<option value="${cs.id}">${cs.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="create-source" class="col-sm-2 control-label">线索来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-source">
								  <option></option>
									<c:forEach items="${sourceList}" var="sl">
										<option value="${sl.id}">${sl.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="form-group">
							<label for="create-description" class="col-sm-2 control-label">线索描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>

						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

						<div style="position: relative;top: 15px;">
							<div class="form-group">
								<label for="create-contactSummary" class="col-sm-2 control-label">联系纪要</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3" id="create-contactSummary"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="create-nextContactTime"  class="col-sm-2 control-label">下次联系时间</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control mydate" id="create-nextContactTime" name="mydate" readonly>
								</div>
							</div>
						</div>

						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

						<div style="position: relative;top: 20px;">
							<div class="form-group">
                                <label for="create-address" class="col-sm-2 control-label">详细地址</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="create-address"></textarea>
                                </div>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id = "saveCreateClueBtn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改线索的模态窗口 -->
	<div class="modal fade" id="editClueModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">修改线索</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">

						<input type="hidden" id="edit-id">

						<div class="form-group">
							<label for="edit-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: #ff0000;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">
									<c:forEach items="${userList}" var="u">
										<option value="${u.id}">${u.name}</option>
									</c:forEach>
								</select>
							</div>
							<label for="edit-company" class="col-sm-2 control-label">公司<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-company" value="动力节点">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-appellation" class="col-sm-2 control-label">称呼</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-appellation">
								  <option></option>
									<c:forEach items="${appellationList}" var="app">
										<option value="${app.id}">${app.value}</option>
									</c:forEach>
								</select>
							</div>
							<label for="edit-fullname" class="col-sm-2 control-label">姓名<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-fullname" value="李四">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-job" class="col-sm-2 control-label">职位</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-job" value="CTO">
							</div>
							<label for="edit-email" class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-email" value="lisi@bjpowernode.com">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-phone" class="col-sm-2 control-label">公司座机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-phone" value="010-84846003">
							</div>
							<label for="edit-website" class="col-sm-2 control-label">公司网站</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-website" value="http://www.bjpowernode.com">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-mphone" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-mphone" value="12345678901">
							</div>
							<label for="edit-state" class="col-sm-2 control-label">线索状态</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-state">
								  <option></option>
									<c:forEach items="${clueStateList}" var="cs">
										<option value="${cs.id}">${cs.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="edit-source" class="col-sm-2 control-label">线索来源</label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-source">
								  <option></option>
									<c:forEach items="${sourceList}" var="sl">
										<option value="${sl.id}">${sl.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="edit-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description">这是一条线索的描述信息</textarea>
							</div>
						</div>

						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative;"></div>

						<div style="position: relative;top: 15px;">
							<div class="form-group">
								<label for="edit-contactSummary" class="col-sm-2 control-label">联系纪要</label>
								<div class="col-sm-10" style="width: 81%;">
									<textarea class="form-control" rows="3" id="edit-contactSummary">这个线索即将被转换</textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="edit-nextContactTime" class="col-sm-2 control-label">下次联系时间</label>
								<div class="col-sm-10" style="width: 300px;">
									<input type="text" class="form-control" id="edit-nextContactTime" value="2017-05-01">
								</div>
							</div>
						</div>

						<div style="height: 1px; width: 103%; background-color: #D5D5D5; left: -13px; position: relative; top : 10px;"></div>

                        <div style="position: relative;top: 20px;">
                            <div class="form-group">
                                <label for="edit-address" class="col-sm-2 control-label">详细地址</label>
                                <div class="col-sm-10" style="width: 81%;">
                                    <textarea class="form-control" rows="1" id="edit-address">北京大兴区大族企业湾</textarea>
                                </div>
                            </div>
                        </div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id ="saveEditCLueBtn">更新</button>
				</div>
			</div>
		</div>
	</div>




	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>线索列表</h3>
			</div>
		</div>
	</div>

	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">

		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">

			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="query-fullname">
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">公司</div>
				      <input class="form-control" type="text" id="query-company">
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">公司座机</div>
				      <input class="form-control" type="text" id="query-phone">
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">线索来源</div>
					  <select class="form-control" id="query-source">
					  	  <option></option>
						  <c:forEach items="${sourceList}" var="sl">
							  <option value="${sl.id}" >${sl.value}</option>
						  </c:forEach>
					  </select>
				    </div>
				  </div>

				  <br>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="query-owner">
				    </div>
				  </div>



				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">手机</div>
				      <input class="form-control" type="text" id="query-mphone">
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">线索状态</div>
					  <select class="form-control"id="query-state">
					  	<option></option>
						  <c:forEach items="${clueStateList}" var="cs">
							  <option value="${cs.id}">${cs.value}</option>
						  </c:forEach>
					  </select>
				    </div>
				  </div>

					<button type="button" class="btn btn-default" id="queryClueBtn">查询</button>

				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 40px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="createClueBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editClueBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteClueBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>


			</div>
			<div style="position: relative;top: 50px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id = "checkAll"/></td>
							<td>名称</td>
							<td>公司</td>
							<td>公司座机</td>
							<td>手机</td>
							<td>线索来源</td>
							<td>所有者</td>
							<td>线索状态</td>
						</tr>
					</thead>
					<tbody id="tBody">
						<%--动态生成--%>
					</tbody>
				</table>

				<%--分页标签--%>
				<div id="demo_page_nav"></div>
			</div>

		</div>

	</div>
</body>
</html>
