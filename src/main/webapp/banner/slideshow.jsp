<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%--BootStrap--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bs/css/bootstrap.min.css">
    <%--jqgrid--%>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/static/bs/css/bootstrap.min.css" rel="stylesheet">
    <%--jqgrid--%>
    <!--引入样式文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bs/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/jqgrid/css/trirand/ui.jqgrid-bootstrap.css"
          type="text/css">
    <%--引入js功能文件--%>
    <script src="${pageContext.request.contextPath}/static/bs/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/bs/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/jqgrid/js/trirand/i18n/grid.locale-cn.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/jqgrid/js/trirand/jquery.jqGrid.min.js"
            type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/jqgrid/js/trirand/ajaxfileupload.js"
            type="text/javascript"></script>
    <title>Document</title>
</head>
<body>
<div>
    <%--轮播图--%>
    <table id="slide_table" class="table table-hover">

    </table>
    <!--分页-->
    <div id="slide_table_page" style="height: 80px">

    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#slide_table').jqGrid({
            styleUI: 'Bootstrap', //整合bootstrap需要提供该参数
            url: '${pageContext.request.contextPath}/slide/showAllSlide',//首次访问获取资源的路径
            datatype: 'json',//传输json字符串格式
            colNames: ['轮播图ID', '轮播图名称', '轮播图图片', '轮播图描述', '轮播图状态', '轮播图创建时间'],//展示表格时表头信息
            editurl: '${pageContext.request.contextPath}/slide/editSlide',//进行编辑是访问后台的路径
            prmNames: {id: 'slide_id'},//自定义id名称
            colModel: [
                {
                    name: 'slide_id',
                    key: true,
                    align: 'center'
                },
                {
                    name: 'slide_name',
                    editable: true,
                    align: 'center'
                },
                {
                    name: 'slide_cover',
                    align: 'center',
                    index: 'slide_cover',
                    width: 100,
                    editable: true,
                    edittype: 'file',
                    enctype: "multipart/form-data",
                    formatter: function (cellvalue, options, rowObject) {
                        /*return '<img src="

                        ${pageContext.request.contextPath}/image/'+cellvalue+ '"  style="width:50px;height:50px;" />';*/
                        return "<img src=${pageContext.request.contextPath}/image/" + cellvalue + " style='width:50px;height:50px;'>"
                    }
                },
                {
                    name: 'slide_description',
                    editable: true,
                    align: 'center'
                },
                {
                    name: 'slide_status',
                    editable: true,
                    align: 'center'
                },
                {
                    name: 'slide_create_date',
                    align: 'center'
                }
            ],
            autowidth: true,
            rownumbers: true,
            pager: '#slide_table_page',
            rowList: [5, 10, 20],
            rowNum: 5,
            viewrecords: true,
            height: '300px',
            page: 1,
            hidegrid: true,
            multiselect: true
        }).navGrid('#slide_table_page',
            {
                add: true, edit: true, del: true, search: true, refresh: true
            },
            {
                beforeShowForm: function (frm) {
                    frm.find('#slide_cover').attr("disabled", true);
                    frm.find("#tr_slide_cover").css("display", "none");
                },
                //编辑的控制
                closeAfterEdit: true,//编辑后自动关闭窗口
                afterSubmit: function (response) {
                    /* var status=response.reponseJSON.status;*/    //返回的map中的status
                    var mes = response.responseJSON.message;              //返回map中的message，保存的是插入到banner的id
                    console.log(mes)
                    //文件上传      ajaxFileUpload
                    $.ajaxFileUpload({
                        url: '${pageContext.request.contextPath}/slide/upload',
                        type: 'post',
                        fileElementId: 'slide_cover',
                        data: {slide_id: mes},
                        success: function () {
                            //刷新页面
                            $("#slide_table").trigger("reloadGrid");
                        }
                    })

                    return "123";
                }
            },
            {
                //添加的控制
                closeAfterAdd: true,//添加后自动关闭添加框
                editData: {slide_id: ''}, //发送空串，而不是框架默认的_empt，便于，如果有需求时进行处理
                afterSubmit: function (response) {
                    /* var status=response.reponseJSON.status;*/    //返回的map中的status
                    var mes = response.responseJSON.message;              //返回map中的message，保存的是插入到banner的id
                    console.log(mes)
                    //文件上传      ajaxFileUpload
                    $.ajaxFileUpload({
                        url: '${pageContext.request.contextPath}/slide/upload',
                        type: 'post',
                        fileElementId: 'slide_cover',
                        data: {slide_id: mes},
                        success: function () {
                            //刷新页面
                            $("#slide_table").trigger("reloadGrid");
                        }
                    })

                    return "123";
                }
            },
            {
                //删除的控制

            },
            {
                //搜索的控制
                closeAfterSearch: true//搜索后自动关闭搜索框
            }
        );
    })
</script>
</body>
</html>
<%--<div class="container">
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="${pageContext.request.contextPath}/image/f99.jpg" alt="...">
                <div class="carousel-caption">
                    ...
                </div>
            </div>
            <div class="item">
                <img src="${pageContext.request.contextPath}/image/q4.jpg" alt="...">
                <div class="carousel-caption">
                    ...
                </div>
            </div>
            <div class="item">
                <img src="${pageContext.request.contextPath}/image/q11.jpg" alt="...">
                <div class="carousel-caption">
                    ...
                </div>
            </div>
            ...
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>--%>