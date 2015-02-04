<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/static/www/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="wrapper container">
    <%@ include file="../headerAdmin.jspf" %>
    <h3></h3>
<c:forEach items="${faculties}" var="faculty">
    <div class="apps">


        <div class="inner">
            <p><b>Название факультета:</b> ${faculty.name}</p>
            <p><b>Декан:</b> ${faculty.decan}</p>
            <h4>Группы</h4>
            <c:forEach var="group" items="${faculty.groups}">
            <tr><td>
                    <p><b>Название группы:</b> ${group.name}</p>
                    <p><b>Количество студентов:</b> ${group.numberStudent}</p>
            </td></tr>
              <tr> <td> <a class="btn btn-blue" href="${pageContext.request.contextPath}/do/edit?id=${group.id}">Изменить</a></td></tr>
              <tr> <td> <a class="btn btn-red" href="#">Удалить</a></td></tr>

            </c:forEach>
        </div>
        <a class="btn btn-blue" href="#">Изменить</a>
        <a class="btn btn-red" href="#">Удалить</a>
    </div>
</c:forEach>
    </div>
</body>
</html>
