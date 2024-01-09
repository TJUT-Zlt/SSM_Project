<%--
  Created by IntelliJ IDEA.
  User: abel
  Date: 2023/10/19
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath = request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort()+request.getContextPath()+"/";%>
<%--http://127.0.0.1:8080/CRM/--%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css"  />
<link type="text/css" rel="stylesheet" href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css"  />
<link type="text/css" rel="stylesheet" href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css">

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<%--日历--%>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<%--分页--%>
<script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>
<%--自动补全--%>
<script type="text/javascript" src="jquery/bs_typeahead/bootstrap3-typeahead.min.js"></script>
<%--图表--%>
<script type="text/javascript" src="jquery/echarts/echarts.min.js"></script>

<meta charset="UTF-8">
