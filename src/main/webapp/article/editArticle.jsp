<%--
  Created by IntelliJ IDEA.
  User: fzc
  Date: 2019-12-22
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>富文本编辑器</title>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>

    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <script>

        KindEditor.ready(function (K) {
            K.create('#addeditor_id', {
                allowFileManager: true,      //显示图片空间按钮
                uploadJson: '${pageContext.request.contextPath}/article/upload',      //图片上传路径
                filePostName: 'imgFile',      //设置后台接收参数名
                fileManagerJson: "${pageContext.request.contextPath}/article/getAll"    //图片空间获取图片信息
            });
        });

    </script>
</head>

<body>
<%--<form action="${pageContext.request.contextPath}/article/addNews" method="post">--%>
<div>
    <textarea id="addeditor_id" name="content"
              style="width:100%;height:600px;border: 0 none;visibility:hidden;"></textarea>
    <%--<textarea rows="" cols="" name="ncontent" id="schtmlnr" style="display:none;"></textarea>--%>
</div>
<input type="submit" value="提交"/>
<%--</form>--%>
</body>

</html>
