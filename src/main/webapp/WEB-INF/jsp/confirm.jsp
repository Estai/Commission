<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Приемная комиссия</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/static/www/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="wrapper container">
    <%@ include file="header.jspf" %>
    <div class="heading"></div>
    <div class="row">
        <aside class="col-md-7"></aside>
        <section class="col-md-17 mainer">
            <%--<c:set var="groupName" value="${group}"/>--%>
            <%--<c:set var="message" value="${message}"/>--%>
            <c:out value="${message}"/><c:out value="${group.name}"/><br>
            <form action="${pageContext.request.contextPath}/do/send" method="post">
                <input type="hidden" name="id_group" value="${group.name}">
                <input type="hidden" name="id_enrollee" value="${enrollee.id}">
                <input type="submit" name="submit" value="Да,заявку принять"/>
                </form>
        </section>
    </div>
</div>
<footer></footer>


</body>
</html>

