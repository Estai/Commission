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

            <h3>Приемная комиссия</h3>
            <c:out value="${DataError}"/><br>
            Выберите факультет:
            <form action="${pageContext.request.contextPath}/do/faculty" method="post">
                <p><select size="1" name="faculty" onchange="this.form.submit();">
                    <option disabled>Выберите факультет</option>
                    <c:forEach var="list" items="${faculties}">
                        <option value="${list.id}">${list.name}</option>
                    </c:forEach>
                </select></p>
            </form>
            Выберите группу:
            <form action="${pageContext.request.contextPath}/do/group" method="post">
                <p><select size="1" name="group" onchange="this.form.submit();">
                    <option disabled>Выберите группу</option>
                    <c:forEach var="list" items="${groups}">
                        <option value="${list.id}">${list.name}</option>
                    </c:forEach>
                </select></p>

            </form>


        </section>
    </div>
</div>
<footer></footer>


</body>
</html>