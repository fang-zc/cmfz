<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/bs/css/bootstrap.min.css" rel="stylesheet">
    <%--jqgrid--%>
    <!--引入样式文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bs/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/jqgrid/css/trirand/ui.jqgrid-bootstrap.css"
          type="text/css">

    <!--引入js功能文件-->
    <script src="${pageContext.request.contextPath}/static/bs/js/jquery-2.2.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/bs/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/jqgrid/js/trirand/i18n/grid.locale-cn.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/jqgrid/js/trirand/jquery.jqGrid.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/jqgrid/js/trirand/ajaxfileupload.js"
            type="text/javascript"></script>
    <%--echart js--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>

</head>
<body>
<%--导航栏--%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持明法洲后台管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎：${sessionScope.loginAdmin.nickname}</a></li>
                <li class="dropdown"><%--class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"--%>
                    <a href="${pageContext.request.contextPath}/login/login.jsp">安全退出 </a>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<%--栅格系统--%>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            <%--手风琴--%>
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" align="center">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title" align="center">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               aria-expanded="true" aria-controls="collapseOne">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne" align="center">
                        <div class="panel-body">
                            <a class="btn btn-success"
                               href="javascript:$('#content').load('${pageContext.request.contextPath}/banner/slideshow.jsp')">所有轮播图</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <a class="btn btn-success"
                               href="javascript:$('#content').load('${pageContext.request.contextPath}/album/album.jsp')">所有专辑</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body">

                            <a class="btn btn-success"
                               href="javascript:$('#content').load('${pageContext.request.contextPath}/article/article.jsp')">所有文章</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingFour">
                        <div class="panel-body">
                            <a class="btn btn-success"
                               href="javascript:$('#content').load('${pageContext.request.contextPath}/user/user.jsp')">所有用户</a>
                        </div>
                    </div>
                </div>
                <%--<div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                文章
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                        <div class="panel-body">
                            <a class="btn btn-success" href="javascript:$('#content').load('${pageContext.request.contextPath}/article/editArticle.jsp')">文章编辑</a>
                        </div>
                    </div>
                </div>--%>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingSix">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                                用户注册图
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                        <div class="panel-body">
                            <a class="btn btn-success"
                               href="javascript:$('#content').load('${pageContext.request.contextPath}/echarts/userecharts.jsp')">注册图</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-10" id="content">
            <%--巨幕--%>
            <div class="jumbotron">
                <h3>欢迎来到持明法洲后台管理系统!</h3>
                <%--<p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>--%>
            </div>
            <img src="${pageContext.request.contextPath}/image/shouye.jpg" alt="" width="100%">
        </div>
    </div>
    <div class="panel panel-footer text-center" style="font-family: 宋体;position: absolute;bottom: 0px;width: 100%">
        持明法洲后台管理系统@百知教育2019.7.8
    </div>
</div>
<br>
<br>
<script>

</script>
</body>
</html>
