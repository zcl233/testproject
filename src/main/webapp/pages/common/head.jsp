<%--
  Created by IntelliJ IDEA.
  User: zcl
  Date: 2018/8/30
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!--
        Charisma v1.0.0

        Copyright 2012 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
    -->
    <meta charset="utf-8">
    <title>Free HTML5 Bootstrap Admin Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

    <!-- The styles -->
    <link id="bs-css" href="/statics/css/bootstrap-cerulean.css" rel="stylesheet">
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }
    </style>
    <link href="/statics/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="/statics/css/charisma-app.css" rel="stylesheet">
    <link href="/statics/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
    <link href='/statics/css/fullcalendar.css' rel='stylesheet'>
    <link href='/statics/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
    <link href='/statics/css/chosen.css' rel='stylesheet'>
    <link href='/statics/css/uniform.default.css' rel='stylesheet'>
    <link href='/statics/css/colorbox.css' rel='stylesheet'>
    <link href='/statics/css/jquery.cleditor.css' rel='stylesheet'>
    <link href='/statics/css/jquery.noty.css' rel='stylesheet'>
    <link href='/statics/css/noty_theme_default.css' rel='stylesheet'>
    <link href='/statics/css/elfinder.min.css' rel='stylesheet'>
    <link href='/statics/css/elfinder.theme.css' rel='stylesheet'>
    <link href='/statics/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='/statics/css/opa-icons.css' rel='stylesheet'>
    <link href='/statics/css/uploadify.css' rel='stylesheet'>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="/statics/img/favicon.ico">
    <script type="text/javascript">var tt='${mList}';</script>
</head>

<body>
<!-- topbar starts -->
<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="/main.html"> <img alt="Charisma Logo" src="/statics/img/logo20.png" /> <span>SL会员商城</span></a>

            <!-- theme selector starts -->
            <div class="btn-group pull-right theme-container" >
                <ul class="nav">
                    <li><a href="">你好，${user.loginCode}</a></li>
                    <li><a href="">角色：${user.roleName}</a></li>
                    <li><a href="">首页</a></li>
                    <li><a href="">购物车</a></li>
                    <li><a href="">留言板</a></li>
                    <li><a href="javascript:void(0);" class="btn-setting modifypwd">修改密码</a></li>
                    <li><a href="/logout.html">注销</a></li>
                </ul>
            </div>
            <div class="modal hide fade" id="myModal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3>修改密码</h3>
            </div>
            <div class="modal-body">
                <p>
                    <label>请输入原密码：</label>
                    <input id="oldpassword" type="password">
                    <span style="color: red;font-weight: bold;">*</span>
                    <label>请输入新密码：</label>
                    <input id="newpassword" type="password">
                    <span style="color: red;font-weight: bold;">*新密码必须6位以上</span>
                    <label>再次输入新密码：</label>
                    <input id="aginpassword" type="password">
                    <span style="color: red;font-weight: bold;">*</span>
                </p>
                <p id="modifypwdtip"></p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn" data-dismiss="modal">取消</a>
                <a href="#" id="modifySavePassword" class="btn btn-primary">修改</a>
            </div>
        </div>
            <!-- theme selector ends -->

            <!-- user dropdown starts -->

            <!-- user dropdown ends -->

        </div>
    </div>
</div>
<!-- topbar ends -->
<div class="container-fluid">
    <div class="row-fluid">

        <!-- left menu starts -->
        <div class="span2 main-menu-span">
            <div class="well nav-collapse sidebar-nav">
                <ul class="nav nav-tabs nav-stacked main-menu" id="menus">
                    <%--<li class="nav-header hidden-tablet" onclick=$("#test1").toggle(500)>Main</li>--%>
                    <%--<li>--%>
                        <%--<ul class="nav nav-tabs nav-stacked" id="test1">--%>
                            <%--<li><a class="ajax-link" href="index.html"><i class="icon-home"></i><span class="hidden-tablet"> Dashboard</span></a></li>--%>
                            <%--<li><a class="ajax-link" href="ui.html"><i class="icon-eye-open"></i><span class="hidden-tablet"> UI Features</span></a></li>--%>
                            <%--<li><a class="ajax-link" href="form.html"><i class="icon-edit"></i><span class="hidden-tablet"> Forms</span></a></li>--%>
                            <%--<li><a class="ajax-link" href="chart.html"><i class="icon-list-alt"></i><span class="hidden-tablet"> Charts</span></a></li>--%>
                            <%--<li><a class="ajax-link" href="typography.html"><i class="icon-font"></i><span class="hidden-tablet"> Typography</span></a></li>--%>
                            <%--<li><a class="ajax-link" href="gallery.html"><i class="icon-picture"></i><span class="hidden-tablet"> Gallery</span></a></li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                </ul>
            </div><!--/.well -->
        </div><!--/span-->
        <!-- left menu ends -->

        <noscript>
            <div class="alert alert-block span10">
                <h4 class="alert-heading">Warning!</h4>
                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="span10">
            <!-- content starts -->