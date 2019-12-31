<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<style>
    /*.ui-jqgrid .ui-jqgrid-title{font-size:22px;}  !*修改grid标题的字体大小*!
    .ui-jqgrid-sortable {font-size:32px;}   !*修改列名的字体大小*!
    .ui-jqgrid tr.jqgrow td {font-size:22px; font-family:"楷体"} !*修改表格内容字体*!
    .ui-jqgrid tr.jqgrow td{
        white-space:normal !important;
        height:auto;
        vertical-align:text-top;
        padding-top:2px;
        display:table-cell;
        vertical-align:middle;
    }*/
    /*.ui-th-column ui-th-ltr{
        background: blue;}*/
    /*  表格头部 */
    th {
        text-align: center;
        height: 40px;
        background: greenyellow;
    }

    .ui-jqgrid .ui-jqgrid-title {
        font-size: 10px;
    }

    /*修改grid标题的字体大小*/

    .ui-jqgrid-sortable {
        font-size: 14px;
        font-family: KaiTi;

    }

    /*修改列名的字体大小*/

    .ui-jqgrid tr.jqgrow td {
        font-size: 12px;
        font-family: 宋体;

    }

</style>
<script type="text/javascript">

    $(function () {

        $("#user_table").jqGrid(
            {
                url: '${pageContext.request.contextPath}/user/showAllUser',
                datatype: "json",
                height: 190,
                colNames: ['用户编号', '用户名', '用户性别', '用户法名', '用户省份', '用户城市', '用户签名', '用户创建时间', '用户头像', '用户状态', '用户手机', '用户上师'],
                editurl: '${pageContext.request.contextPath}/user/editUser',
                prmNames: {id: 'c_id'},
                colModel: [
                    {
                        name: 'c_id',
                        key: true,
                        hidden: true,
                        align: 'center'
                    },
                    {
                        name: 'username',
                        align: 'center'
                    },
                    {
                        name: 'c_sex',
                        align: 'center'
                    },
                    {
                        name: 'c_dharma',
                        align: 'center',
                    },
                    {name: 'c_province', align: 'center'},
                    {
                        name: 'c_city',
                        align: 'center'
                    },
                    {
                        name: 'c_sign',
                        align: 'center'
                    },
                    {name: 'c_create_date', align: 'center', sortable: true},
                    {
                        name: 'c_photo',
                        align: 'center',
                        width: 100,
                        edittype: 'file',

                        formatter: function (cellvalue, options, rowObject) {
                            /*return '<img src="

                            ${pageContext.request.contextPath}/image/'+cellvalue+ '"  style="width:50px;height:50px;" />';*/
                            return "<img src=${pageContext.request.contextPath}/user/img/" + cellvalue + " style='width:50px;height:50px;'>"
                        }
                    },
                    {
                        name: 'c_status',
                        align: 'center', editable: true, edittype: 'select', editoptions: {value: "激活:激活;冻结:冻结"}
                    },
                    {
                        name: 'c_phone',
                        align: 'center'
                    },

                    {name: 'u_name', align: 'center'}
                ],
                styleUI: 'Bootstrap',
                height: 300,
                autowidth: true,
                altRows: true,//隔行换色
                rowNum: 3,
                rowList: [3, 5, 10, 20],
                pager: '#user_pager',
                viewrecords: true,
                multiselect: false,

                caption: "展示用户",
            }).navGrid("#user_pager",
            {
                edit: true, add: false, del: false, search: false
            },
            {
                //控制修改的相关操作
                closeAfterEdit: close
            },
            {});
    })

    function userOut() {
        window.location.href = "${pageContext.request.contextPath}/user/userOut";
    }
</script>
<%--标签页--%>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">展示用户</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="userOut()"
                                   data-toggle="tab">用户信息导出</a></li>
    </ul>
</div>
<table id="user_table">

</table>
<div id="user_pager" style="height: 80px">

</div>
