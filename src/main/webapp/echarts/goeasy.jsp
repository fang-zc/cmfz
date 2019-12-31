<%--
  Created by IntelliJ IDEA.
  User: fzc
  Date: 2019-12-25
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Title</title>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js​"></script>
    <script src="${pageContext.request.contextPath}/static/bs/js/jquery-2.2.1.min.js" type="text/javascript"></script>
</head>
<body>
<script>
    var goEasy = new GoEasy({
        appkey: "BS-22c56e52e2a34f4b906046e27283ed2c"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            alert("Channel:" + message.channel + " content:" + message.content);
        }
    });
</script>
</body>
</html>
