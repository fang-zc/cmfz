<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<script type="text/javascript">
    $(function () {
        $("#album_table").jqGrid(
            {
                url: '${pageContext.request.contextPath}/album/showAllAlbum',
                datatype: "json",
                height: 190,
                colNames: ['专辑编号', '专辑名称', '专辑作者', '专辑播音', '专辑封面', '专辑集数', '专辑简介', '专辑评分', '专辑创建时间'],
                editurl: '${pageContext.request.contextPath}/album/editAlbum',
                prmNames: {id: 'album_id'},
                colModel: [
                    {
                        name: 'album_id',
                        key: true,
                        hidden: true,
                        align: 'center'
                    },
                    {
                        name: 'album_title',
                        align: 'center',
                        editable: true
                    },
                    {
                        name: 'album_author',
                        align: 'center',
                        editable: true
                    },
                    {
                        name: 'album_beam',
                        align: 'center',
                        editable: true
                    },
                    {
                        name: 'album_cover',
                        editable: true,
                        align: 'center',
                        edittype: 'file',
                        enctype: 'multipart/form-data',
                        formatter: function (cellvalue, options, rowObject) {
                            return "<img src=${pageContext.request.contextPath}/album/img/" + cellvalue + " style='width:80px;height:80px;'>"

                        }
                    },
                    {name: 'album_count'},
                    {
                        name: 'album_intro',
                        align: 'center',
                        editable: true
                    },
                    {
                        name: 'album_score',
                        align: 'center',
                        editable: true
                    },
                    {name: 'album_create_date'}
                ],
                styleUI: 'Bootstrap',
                height: 300,
                autowidth: true,
                rowNum: 3,
                rowList: [3, 5, 10, 20],
                pager: '#album_pager',
                viewrecords: true,
                multiselect: false,
                subGrid: true,
                caption: "展示专辑",
                subGridRowExpanded: function (subgrid_id, row_id) { //当前父容器id  当前专辑id
                    var subgrid_table_id = subgrid_id + "_t";
                    var pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id
                        + "' class='scroll'></table><div id='"
                        + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid(
                        {
                            styleUI: 'Bootstrap',
                            url: "${pageContext.request.contextPath}/chapter/showAllChapter?&album_id=" + row_id,
                            datatype: "json",
                            colNames: ['章节编号', '章节名称', '章节大小', '章节章节时长', '章节文件名', '章节创建时间', '在线播放'],
                            editurl: '${pageContext.request.contextPath}/chapter/editChapter?&album_id=' + row_id,
                            prmNames: {id: 'chapter_id'},
                            colModel: [
                                {name: "chapter_id", key: true, hidden: true},
                                {
                                    name: "chapter_title", align: 'center',
                                    editable: true
                                },
                                {name: "chapter_size"},
                                {name: "chapter_duration"},
                                {
                                    name: "chapter_cover",
                                    editable: true,
                                    align: 'center',
                                    edittype: 'file',
                                    enctype: 'multipart/form-data'
                                },
                                {name: "chapter_create_date"},
                                {
                                    name: "chapter_audio", width: 300, formatter: function (value, options, rows) {
                                        console.log("${pageContext.request.contextPath}/album/music/" + rows.chapter_cover)
                                        return "<audio controls loop>\n" +
                                            "  <source src='${pageContext.request.contextPath}/album/music/" + rows.chapter_cover + "' type=\"audio/ogg\">\n" +
                                            "</audio>"
                                    }
                                }
                            ],
                            rowNum: 2,
                            autowidth: true,
                            pager: pager_id,
                            height: '100%'
                        }).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit: true,
                            add: true,
                            del: false
                        },
                        {
                            //控制章节修改
                            //控制修改的相关操作
                            closeAfterEdit: close,
                            beforeShowForm: function (frm) {
                                frm.find('#chapter_cover').attr("disabled", true);
                                frm.find("#tr_chapter_cover").css("display", "none");
                            }
                        },
                        {
                            //控制章节的添加
                            closeAfterAdd: close,
                            editData: {chapter_id: ''},
                            afterSubmit: function (response) {
                                var status = response.responseJSON.status;
                                var id = response.responseJSON.message;
                                console.log("status" + status)
                                console.log("id" + id)
                                $.ajaxFileUpload({
                                    url: '${pageContext.request.contextPath}/chapter/upload',
                                    type: 'post',
                                    fileElementId: 'chapter_cover',
                                    data: {chapter_id: id},
                                    success: function () {
                                        //刷新页面
                                        $("#subgrid_table_id").trigger("reloadGrid");
                                        //刷新专辑
                                        $("#album_table").trigger("reloadGrid");
                                    }
                                })
                                return "123";
                            }
                        }
                    );
                },
                subGridRowColapsed: function (subgrid_id, row_id) {
                    // this function is called before removing the data
                    //var subgrid_table_id;
                    //subgrid_table_id = subgrid_id+"_t";
                    //jQuery("#"+subgrid_table_id).remove();
                }
            }).navGrid("#album_pager",
            {
                edit: true, add: true, del: true, search: false
            },
            {
                //控制修改的相关操作
                closeAfterEdit: close,
                beforeShowForm: function (frm) {
                    frm.find('#album_cover').attr("disabled", true);
                    frm.find("#tr_album_cover").css("display", "none");
                }
            },
            {
                //控制添加的相关操作
                closeAfterAdd: close,
                editData: {album_id: ''},
                afterSubmit: function (response) {
                    var status = response.responseJSON.status;
                    var id = response.responseJSON.message;
                    console.log("status" + status)
                    console.log("id" + id)
                    $.ajaxFileUpload({
                        url: '${pageContext.request.contextPath}/album/upload',
                        type: 'post',
                        fileElementId: 'album_cover',
                        data: {album_id: id},
                        success: function () {
                            //刷新页面
                            $("#album_table").trigger("reloadGrid");
                        }
                    })

                    return "123";
                }

            });
        ;
    })
</script>
<div class="page-header">
    <h2>展示专辑</h2>
</div>
<table id="album_table">

</table>
<div id="album_pager" style="height: 80px">

</div>
