<%--
  Created by IntelliJ IDEA.
  User: fzc
  Date: 2019-12-25
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <%--<script type="text/javascript" src="echarts.min.js"></script>
    &lt;%&ndash;<script src="http://cdn-hangzhou.goeasy.io/goeasy.js​"></script>&ndash;%&gt;
    <script src="${pageContext.request.contextPath}/static/bs/js/jquery-2.2.1.min.js" type="text/javascript"></script>--%>

</head>
<body>
<div id="main" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data: ['男性', '女性']
        },
        xAxis: {
            data: ["第一周", "第二周", "第三周"]
        },
        yAxis: {},
        series: [],

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    $.ajax({
        url: '${pageContext.request.contextPath}/user/showCount',
        type: 'post',
        dataType: 'json',
        success: function (data) {
            console.log("data" + data);
            console.log("data.boy" + data.boy);
            console.log("data.girl" + data.girl);
            myChart.setOption({
                series: [{
                    name: '男性',
                    type: 'line',
                    data: data.boy
                }, {
                    name: '女性',
                    type: 'line',
                    data: data.girl
                }]
            })
        }
    });
    /*var goEasy = new GoEasy({
        appkey: "BS-22c56e52e2a34f4b906046e27283ed2c"
    });
    goEasy.subscribe({
        channel: "my_channel",
        onMessage: function (message) {
            console.log(message)
           var data= message.content;
           console.log("data"+data);
           var map = JSON.parse(data);
            console.log("map"+map);
            console.log("map.boy"+map.boy);
            myChart.setOption({
               series:[{
                   name: '男性',
                   type: 'bar',
                   data:map.boy
               },{
                   name: '女性',
                   type: 'bar',
                    data:map.girl
               }]
           })
            /!*alert("Channel:" + message.channel + " content:" + message.content);*!/
        }
    });*/
</script>

</body>
</html>
