<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<%@ include file="/WEB-INF/pages/commons/config.jsp"%>
<script type="text/javascript">

	//页面加载完毕
	$(function(){

		//导航中所有文本颜色为黑色
		$(".liClass > a").css("color" , "black");

		//默认选中导航菜单中的第一个菜单项
		$(".liClass:first").addClass("active");

		//第一个菜单项的文字变成白色
		$(".liClass:first > a").css("color" , "white");

		//给所有的菜单项注册鼠标单击事件
		$(".liClass").click(function(){
			//移除所有菜单项的激活状态
			$(".liClass").removeClass("active");
			//导航中所有文本颜色为黑色
			$(".liClass > a").css("color" , "black");
			//当前项目被选中
			$(this).addClass("active");
			//当前项目颜色变成白色
			$(this).children("a").css("color","white");
		});

		//展示市场活动页面
		// window.open("permission/permissionIndex.jsp","workareaFrame");

	});

</script>

</head>
<body>

	<%--顶部导航栏--%>
	<%@ include file="/WEB-INF/pages/commons/head.jsp"%>

	<!-- 中间 -->
	<div id="center" style="position: absolute;top: 50px; bottom: 30px; left: 0px; right: 0px;">

		<!-- 导航 -->
		<div id="navigation" style="left: 0px; width: 18%; position: relative; height: 100%; overflow:auto;">

			<ul id="no1" class="nav nav-pills nav-stacked">
				<li class="liClass"><a href="settings/qx/permission/permissionIndex.do" target="workareaFrame"><span class="glyphicon glyphicon-user"></span> 许可维护</a></li>
				<li class="liClass"><a href="settings/qx/role/permissionIndex.do" target="workareaFrame"><span class="glyphicon glyphicon-user"></span> 角色维护</a></li>
				<li class="liClass"><a href="settings/qx/user/permissionIndex.do" target="workareaFrame"><span class="glyphicon glyphicon-user"></span> 用户维护</a></li>
			</ul>

			<!-- 分割线 -->
			<div id="divider1" style="position: absolute; top : 0px; right: 0px; width: 1px; height: 100% ; background-color: #B3B3B3;"></div>
		</div>

		<!-- 工作区 -->
		<div id="workarea" style="position: absolute; top : 0px; left: 18%; width: 82%; height: 100%;">
			<iframe style="border-width: 0px; width: 100%; height: 100%;" name="workareaFrame"></iframe>
		</div>

	</div>

	<div id="divider2" style="height: 1px; width: 100%; position: absolute;bottom: 30px; background-color: #B3B3B3;"></div>

	<!-- 底部 -->
	<div id="down" style="height: 30px; width: 100%; position: absolute;bottom: 0px;"></div>

</body>
</html>
