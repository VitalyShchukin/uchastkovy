<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<%!
String getFormattedDate()
{
SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
return sdf.format(new Date());
}
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Добро пожаловать, JSP!</title>
</head>
<body>
<h1>Добро пожаловать!</h1>
<i>Сегодня <%= getFormattedDate() %></i>
</body>
</html>





<!--        "Та самая страница, которую неизвестно куда пихать"-->
<!--        <p><a href="http://www.yandex.ru">Поисковая система Яндекс</a></p>-->


<!--        <input type="button" onclick="count_rabbits()" value="Считать кролей!" src="/js/rabbits.js"/>-->


<!--        <div style="width: 300px;">-->
<!--            <canvas id="myChart"></canvas>-->
<!--        </div>-->

<!--        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>-->

<!--        <script src="/js/first-script.js"></script>-->

<!--        <script type="text/javascript" >-->
<!--            console.log("Добавили вот какой-то JavaScript-код")-->
<!--        </script>-->

<!--        <p><a>Конец блока</a></p>-->



<!--{:resources}-->
<!--{@js src="js/" /}-->
<!--&lt;!&ndash;{@css src="inventory/n2o/layout/filter" /}&ndash;&gt;-->
<!--{/view}-->