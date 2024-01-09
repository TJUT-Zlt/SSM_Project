<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <script type="text/javascript">

        $(function(){
            //退出登录
            $("#logoutBtn").click(function () {
                window.location.href = "settings/qx/user/logout.do";
            });

            $("#editPassword").click(function () {
                $("#editPwdModal").modal("show");
            });

            $("#saveEditPasswordBtn").click(function () {

                var oldPwd          =$("#oldPwd").val();
                var newPwd          =$("#newPwd").val();
                var confirmPwd      =$("#confirmPwd").val();

                if(oldPwd != "${sessionScope.sessionUser.loginPwd}"){
                    alert("原密码错误");
                    return;
                }
                if(newPwd==""){
                    alert("新密码不能为空");
                    return;
                }
                if(confirmPwd==""){
                    alert("确认密码不能为空");
                    return;
                }
                if(confirmPwd != newPwd){
                    alert("新密码与确认密码不一致");
                    return;
                }
                $.ajax({
                    url:"settings/qx/user/editPassword.do",
                    data:{
                        loginPwd:newPwd,
                    },
                    type:'post',
                    dataType:'json',
                    success:function (data) {
                        if(data.code=='1'){
                            $("#editPwdModal").modal("hide");

                        }else {
                            alert(data.message);
                            $("#editPwdModal").modal("show");
                        }
                    }
                });

            });

        });
    </script>
</head>
<body>
<!-- 我的资料 -->
<div class="modal fade" id="myInformation" role="dialog">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">我的资料</h4>
            </div>
            <div class="modal-body">
                <div style="position: relative; left: 40px;">
                    姓名：<b>${sessionScope.sessionUser.name}</b><br><br>
                    登录帐号：<b>${sessionScope.sessionUser.loginAct}</b><br><br>
                    登录密码：<b>${sessionScope.sessionUser.loginPwd}</b><br><br>
                    组织机构：<b>${sessionScope.sessionUser.deptno}</b><br><br>
                    邮箱：<b>${sessionScope.sessionUser.email}</b><br><br>
                    失效时间：<b>${sessionScope.sessionUser.expireTime}</b><br><br>
                    允许访问IP：<b>${sessionScope.sessionUser.allowIps}</b>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- 修改密码的模态窗口 -->
<div class="modal fade" id="editPwdModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="oldPwd" class="col-sm-2 control-label">原密码</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="oldPwd" style="width: 200%;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newPwd" class="col-sm-2 control-label">新密码</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="newPwd" style="width: 200%;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="confirmPwd" class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="confirmPwd" style="width: 200%;">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveEditPasswordBtn">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 退出系统的模态窗口 -->
<div class="modal fade" id="exitModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">离开</h4>
            </div>
            <div class="modal-body">
                <p>您确定要退出系统吗？</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="logoutBtn" >确定</button>
            </div>
        </div>
    </div>
</div>

<!-- 顶部 -->
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
    <div style="position: absolute; top: 15px; right: 15px;">
        <ul>
            <li class="dropdown user-dropdown">
                <a href="javascript:void(0)" style="text-decoration: none; color: white;" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user"></span>${sessionScope.sessionUser.name} <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="workbench/workbenchIndex.do"><span class="glyphicon glyphicon-home"></span> 工作台</a></li>
                    <li><a href="settings/settingsIndex.do"><span class="glyphicon glyphicon-wrench"></span> 系统设置</a></li>
                    <li><a href="javascript:void(0)" data-toggle="modal" data-target="#myInformation"><span class="glyphicon glyphicon-file"></span> 我的资料</a></li>
                    <li><a href="javascript:void(0)" id="editPassword"><span class="glyphicon glyphicon-edit"></span> 修改密码</a></li>
                    <li><a href="javascript:void(0);" data-toggle="modal" data-target="#exitModal"><span class="glyphicon glyphicon-off"></span> 退出</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>

</body>
</html>
