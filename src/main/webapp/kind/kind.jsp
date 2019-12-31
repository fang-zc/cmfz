<%--
  Created by IntelliJ IDEA.
  User: fzc
  Date: 2019-12-23
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script charset="UTF-8" src="kindeditor-all-min.js"></script>
    <script charset="UTF-8" src="lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function (K) {
            K.create('#editor_id', {
                allowFileManager: true,
                uploadJson: '${pageContext.request.contextPath}/article/upload',
                filePostName: 'imgFile'
            })
        })
    </script>
</head>
<body>
<textarea name="" id="editor_id" cols="30" rows="10"></textarea>
</body>
</html>
