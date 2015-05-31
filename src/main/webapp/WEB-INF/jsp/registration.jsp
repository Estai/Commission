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
    <%@ include file="header.jspf" %>
    <fmt:setBundle basename="app" var="rb"/>
    <div class="heading"></div>
    <div class="row">
        <aside class="col-md-7">
            <p style="color:#011b6a;">© Мурых Е.Л.,Боранов Е.Т.,Паршина.,Әбеуов Е.Б.</p>
            <p style="color:#011b6a;">Творческие экзамены</p>
            <p style="color:#011b6a;">Специальность "Дизайн": <fmt:message key="examDesign" bundle="${rb}"/> </p>
            <p style="color:#011b6a;">Специальность "Начальная военная подготовка": <fmt:message key="examNVP" bundle="${rb}"/> </p>
        </aside>
        <section class="col-md-17 mainer">

            <h3>Регистрация</h3>



            <form action="${pageContext.request.contextPath}/do/registration" method="post">
                <div class="rows">
                    <label>Логин:
                        <span class="required">*</span>
                    </label>
                    <input name="login" type="text" size="30" maxlength="50" value="${login}"><c:out value="${loginError}"/>
                </div>

                <div class="rows">
                    <label>Пароль:
                        <span class="required">*</span>
                    </label>
                    <input name="password" type="password" size="30" maxlength="50"> <c:out value="${passError}"/>
                </div>
                <div class="rows">
                    <label>Повтор пароля:
                        <span class="required">*</span>
                    </label>
                    <input name="confirmPassword" type="password" size="30" maxlength="15">
                </div>
                <p>
                    <input type="submit" name="submit" value="Зарегистрироваться">
                </p></form>
            <div style="margin:auto;width:55%;float:left;">
                <h3>Войти</h3>

                <form action="${pageContext.request.contextPath}/do/login" method="post">

                    <div class="rows">
                        <label>Логин:
                            <span class="required">*</span>
                        </label>
                        <input name="log" type="text" size="30" maxlength="15" value="${log}"><c:out value="${credentialsError}"/>
                    </div>

                    <div class="rows">
                        <label>Пароль:
                            <span class="required">*</span>
                        </label>
                        <input name="pass" type="password" size="30" maxlength="15">
                    </div>


                    <p>
                        <input type="submit" name="submit" value="Войти">
                    </p></form>

            </div>
        </section>
    </div>
</div>
<footer></footer>


</body>
</html>
