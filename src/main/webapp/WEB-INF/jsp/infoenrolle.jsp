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

            <h3>Данные абитуриента:</h3>
             <c:out value="${dataError}"/>
            <form action="${pageContext.request.contextPath}/do/comission" method="post">
                <div class="rows">
                    <label>Фамилия:
                        <span class="required">*</span>
                    </label>
                    <input name="lastname" type="text" size="30" maxlength="50">
                </div>

                <div class="rows">
                    <label>Имя:
                        <span class="required">*</span>
                    </label>
                    <input name="firstname" type="text" size="30" maxlength="50">
                </div>
                <div class="rows">
                    <label>Отчество:
                        <span class="required">*</span>
                    </label>
                    <input name="middlename" type="text" size="30" maxlength="15">
                </div>
                <div class="rows">
                    <label>Номер аттестата:
                        <span class="required">*</span>
                    </label>
                    <input name="number" type="text" size="30" maxlength="15">
                </div>
                <div class="rows">
                    <label>Средний бал аттестата:
                        <span class="required">*</span>
                    </label>
                    <input name="certificate" type="text" size="30" maxlength="15">
                </div>

                <c:forEach var="subject" items="${subjects}">
                <c:if test="${subject.main}">
                <div class="rows">
                    <label>Предмет: <br>${subject.name}
                        <span class="required">*</span>
                    </label>
                    Баллы(оценки) <span class="required">*</span>
                    <select name="score[${subject.id}]" size="1">
                        <option disabled selected>Выберите оценку</option>
                        <c:forEach var="score" items="${scores}">
                            <option value="${score.id}">${score.score}</option>
                        </c:forEach>
                    </select>
                </div>
                </c:if>
                </c:forEach>

                <div class="rows">
                    <label>Предмет:
                        <span class="required">*</span>
                    </label>

                        <select name="subj" size="1">
                        <option disabled selected>Выберите Предмет</option>
                        <c:forEach var="subject" items="${subjects}">
                            <c:if test="${not subject.main}">
                        <option value="${subject.id}">${subject.name}</option>
                            </c:if>
                        </c:forEach>
                        </select>
                    Баллы(оценки) <span class="required">*</span>
                    <select name="score" size="1">
                        <option disabled selected>Выберите оценку</option>
                        <c:forEach var="score" items="${scores}">
                            <option value="${score.id}">${score.score}</option>
                        </c:forEach>
                    </select>
                </div>
                <p>
                    <input type="hidden" name="user.id" value="${user.id}">
                    <input type="submit" name="submit" value="Сохранить">
                </p></form>

        </section>
    </div>
</div>
<footer></footer>


</body>
</html>
