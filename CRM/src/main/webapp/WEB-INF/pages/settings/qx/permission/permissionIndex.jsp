<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<%@ include file="/WEB-INF/pages/commons/config.jsp"%>
<SCRIPT type="text/javascript">
	var setting = {
		edit: {
			enable : true,
			showRenameBtn : false,
			showRemoveBtn : true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};

	var zNodes =[
		{ id:0, name:"CRM" , open : true},
		{ id:1, pId:0, name:"市场活动" , open : true},
		{ id:11, pId:1, name:"创建市场活动"},
		{ id:12, pId:1, name:"修改市场活动"},
		{ id:13, pId:1, name:"删除市场活动"},
		{ id:2, pId:0, name:"线索"},
		{ id:21, pId:2, name:"创建线索"},
		{ id:22, pId:2, name:"修改线索"},
		{ id:23, pId:2, name:"删除线索"},
		{ id:3, pId:0, name:"客户"},
		{ id:31, pId:3, name:"创建客户"},
		{ id:32, pId:3, name:"修改客户"},
		{ id:33, pId:3, name:"删除客户"},
		{ id:4, pId:0, name:"联系人"},
		{ id:41, pId:4, name:"创建联系人"},
		{ id:42, pId:4, name:"修改联系人"},
		{ id:43, pId:4, name:"删除联系人"},
		{ id:5, pId:0, name:"交易"},
		{ id:51, pId:5, name:"创建交易"},
		{ id:52, pId:5, name:"修改交易"},
		{ id:53, pId:5, name:"删除交易"},
		{ id:6, pId:0, name:"售后回访"},
		{ id:61, pId:6, name:"创建任务"},
		{ id:62, pId:6, name:"修改任务"},
		{ id:63, pId:6, name:"删除任务"},
		{ id:7, pId:0, name:"统计图表"},
		{ id:71, pId:7, name:"市场活动统计图表"},
		{ id:72, pId:7, name:"线索统计图表"},
		{ id:73, pId:7, name:"客户和联系人统计图表"},
		{ id:74, pId:7, name:"交易统计图表"},
		{ id:8, pId:0, name:"系统设置"},
		{ id:81, pId:8, name:"权限管理"},
		{ id:811, pId:81, name:"许可维护"},
		{ id:8111, pId:811, name:"新增许可"},
		{ id:8112, pId:811, name:"修改许可"},
		{ id:8113, pId:811, name:"删除许可"},
		{ id:812, pId:81, name:"角色维护"},
		{ id:8121, pId:812, name:"新增角色"},
		{ id:8122, pId:812, name:"修改角色"},
		{ id:8123, pId:812, name:"删除角色"},
		{ id:813, pId:81, name:"用户维护"},
		{ id:8131, pId:813, name:"新增用户"},
		{ id:8132, pId:813, name:"修改用户"},
		{ id:8133, pId:813, name:"删除用户"}

	];

	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});

</SCRIPT>

</head>
<body>

	<!-- 新增许可模态窗口 -->
	<div class="modal fade" id="createPermissionModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增许可</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="create-permissionName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-permissionName" style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="create-moduleUrl" class="col-sm-2 control-label">模块URL</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-moduleUrl" style="width: 200%;">
							</div>
						</div>

						<div class="form-group">
							<label for="create-operationUrl" class="col-sm-2 control-label">操作URL</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-operationUrl" style="width: 200%;" placeholder="多个用逗号隔开">
							</div>
						</div>

						<div class="form-group">
							<label for="create-orderNo" class="col-sm-2 control-label">排序号</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="create-orderNo" style="width: 200%;">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 修改许可 -->
	<div class="modal fade" id="editPermissionModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 80%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改许可</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" role="form">

						<div class="form-group">
							<label for="edit-permissionName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-permissionName" style="width: 200%;" value="市场活动">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-moduleUrl" class="col-sm-2 control-label">模块URL</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-moduleUrl" style="width: 200%;" value="activity/index.do">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-operationUrl" class="col-sm-2 control-label">操作URL</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-operationUrl" style="width: 200%;" placeholder="多个用逗号隔开" value="activity/create.do,activity/save.do">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-orderNo" class="col-sm-2 control-label">排序号</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-orderNo" style="width: 200%;" value="1">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>

	<div style="width: 20%; height: 100%; background-color: #F7F7F7; position: absolute; overflow:auto;">
		<ul id="treeDemo" class="ztree" style="position: relative; top: 15px; left: 15px;"></ul>
	</div>
	<div style="position: absolute; width: 80%; height: 100%; overflow: auto; left: 20%;">
		<!-- 大标题 -->
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>市场活动 <small>许可明细</small></h3>
			</div>
			<div style="position: relative; height: 50px; width: 250px;  top: -72px; left: 60%;">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createPermissionModal"><span class="glyphicon glyphicon-plus"></span> 新增</button>
				<button type="button" class="btn btn-default" data-toggle="modal" data-target="#editPermissionModal"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
			</div>
		</div>
		<!-- 详细信息 -->
		<div style="position: relative; top: -70px;">
			<div style="position: relative; left: 40px; height: 30px; top: 20px;">
				<div style="width: 300px; color: gray;">名称</div>
				<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>市场活动</b></div>
				<div style="height: 1px; width: 600px; background: #D5D5D5; position: relative; top: -20px;"></div>
			</div>
			<div style="position: relative; left: 40px; height: 30px; top: 40px;">
				<div style="width: 300px; color: gray;">模块URL</div>
				<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>activity/index.do</b></div>
				<div style="height: 1px; width: 600px; background: #D5D5D5; position: relative; top: -20px;"></div>
			</div>
			<div style="position: relative; left: 40px; height: 30px; top: 60px;">
				<div style="width: 300px; color: gray;">操作URL</div>
				<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>activity/create.do,activity/edit.do,activity/delete.do</b></div>
				<div style="height: 1px; width: 600px; background: #D5D5D5; position: relative; top: -20px;"></div>
			</div>
			<div style="position: relative; left: 40px; height: 30px; top: 80px;">
				<div style="width: 300px; color: gray;">排序号</div>
				<div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>1</b></div>
				<div style="height: 1px; width: 600px; background: #D5D5D5; position: relative; top: -20px;"></div>
			</div>
		</div>
	</div>
</body>
</html>
