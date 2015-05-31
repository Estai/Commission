<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Приемная комиссия</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/static/www/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="wrapper container">
    <fmt:setBundle basename="app" var="rb"/>
    <%@ include file="header.jspf" %>
    <div class="heading"></div>
    <div class="row">
        <aside class="col-md-7">
            <p style="color:#011b6a;">© Мурых Е.Л.,Боранов Е.Т.,Паршина.,Әбеуов Е.Б.</p>
            <p style="color:#011b6a;">Творческие экзамены</p>
            <p style="color:#011b6a;">Специальность "Дизайн": <fmt:message key="examDesign" bundle="${rb}"/> </p>
            <p style="color:#011b6a;">Специальность "Начальная военная подготовка": <fmt:message key="examNVP" bundle="${rb}"/> </p>
        </aside>
        <section class="col-md-17 mainer">
            <%--<c:set var="groupName" value="${group}"/>--%>
            <%--<c:set var="message" value="${message}"/>--%>
            <c:out value="${message}"/><br>
                <c:forEach var="group" items="${application}">
                <c:out value="${group.name}"/><br>
                </c:forEach><br>
            <form action="${pageContext.request.contextPath}/do/send" method="post">
                <input type="hidden" name="id_enrollee" value="${enrollee.id}">
                <input type="submit" name="submit" value="Да,заявку принять"/>
                </form>
        </section>
    </div>
</div>
<footer></footer>


</body>
</html>

