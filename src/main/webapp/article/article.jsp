<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%--富文本编辑器js--%>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<script type="text/javascript">
    $(function () {
        $("#article_table").jqGrid(
            {
                url: '${pageContext.request.contextPath}/article/showAllArticle',
                datatype: "json",
                height: 190,
                colNames: ['文章编号', '文章标题', '文章内容', '创建时间', '文章作者', '操作'],
                editurl: '${pageContext.request.contextPath}/article/editArticle',
                prmNames: {id: 'article_id'},
                colModel: [
                    {
                        name: 'article_id',
                        key: true,
                        hidden: true,
                        align: 'center'
                    },
                    {
                        name: 'article_title',
                        align: 'center',
                        editable: true
                    },
                    {
                        name: 'article_content',
                        align: 'center',
                        editable: true
                    },
                    {
                        name: 'article_create_date',
                        align: 'center'
                    },
                    {
                        name: 'article_author',
                        align: 'center',
                        editable: true
                    },
                    {
                        name: 'option',
                        align: 'center',
                        //属性内部自定义方法
                        formatter: function (value, options, rows) {
                            return "<a class='btn btn-success' onclick=\"openModal('edit','" + rows.article_id + "')\">修改文章</a>"
                        }
                    }
                ],
                styleUI: 'Bootstrap',
                height: 300,
                autowidth: true,
                rowNum: 3,
                rowList: [3, 5, 10, 20],
                pager: '#article_pager',
                viewrecords: true,
                multiselect: false,

                caption: "展示文章",
            }).navGrid("#article_pager",
            {
                edit: false, add: false, del: true, search: false
            },
            {
                //控制修改的相关操作
                closeAfterEdit: close
            },
            {
                //控制添加的相关操作
                closeAfterAdd: close
            }, {});
        ;
    })

    function openModal(oper, article_id) {
        KindEditor.html('#editor_id', "");
        console.log(oper)
        console.log(article_id)
        var article = $('#article_table').jqGrid("getRowData", article_id); //从jq表中根据id获取特定行的对象信息
        //将文章的数据赋值给form表单
        $('#article_id').val(article.article_id);
        $('#article_title').val(article.article_title);
        $('#article_author').val(article.article_author);
        KindEditor.html("#editor_id", article.article_content);  //id名错误不会添加内容
        $('#article_oper').val(oper);
        $('#article_modal').modal('show');
    }

    KindEditor.create('#editor_id', {
        allowFileManager: true,      //显示图片空间按钮
        uploadJson: '${pageContext.request.contextPath}/article/upload',      //图片上传路径
        filePostName: 'imgFile',      //设置后台接收参数名
        fileManagerJson: "${pageContext.request.contextPath}/article/getAll",    //图片空间获取图片信息
        resizeType: 1,
        //将文本域中的值同步到form当中
        afterBlur: function () {
            this.sync();
        }
    })

    function dealSave() {
        $.ajax({
            url: '${pageContext.request.contextPath}/article/editArticle',
            type: 'post',
            data: {
                article_id: $('#article_id').val(),
                oper: $('#article_oper').val(),
                article_title: $('#article_title').val(),
                article_author: $('#article_author').val(),
                article_content: $(document.getElementsByTagName('iframe')[0].contentWindow.document.body).html()
            },
            dataType: 'json',
            success: function (data) {
                $('#article_modal').modal('hide');
                $('#article_table').trigger('reloadGrid');
            }
        })
    }
</script>
<%--标签页--%>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">展示文章</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" onclick="openModal('add')"
                                   data-toggle="tab">添加文章</a></li>
    </ul>
</div>
<table id="article_table">

</table>
<div id="article_pager" style="height: 80px">

</div>
<%--模态框--%>         <%--tabindex="-1" 该属性的存在会富文本中的网络图片的大小设置功能不一样--%>
<div class="modal fade" role="dialog" id="article_modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 702px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">请输入文章信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="article_form">
                    <input type="hidden" id="article_id" name="article_id">
                    <input type="hidden" id="article_oper" name="oper">
                    <div class="form-group">
                        <label for="article_title" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="article_title" placeholder="Title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="article_author" class="col-sm-2 control-label">作者</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="article_author" placeholder="Author">
                        </div>
                    </div>
                    <%--添加富文本编辑器--%>
                    <div class="form-group">
                        <textarea name="article_content" id="editor_id" style="width:700px;height:300px;"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="dealSave()">Save changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
