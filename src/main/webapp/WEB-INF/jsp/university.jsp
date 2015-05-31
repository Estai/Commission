<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Университет</title>
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

            <p style="text-align: center;">
                <a href="${pageContext.request.contextPath}/download/?file=kstu.docx"><img class="alignnone wp-image-81495" src="http://www.kstu.kz/wp-content/uploads/2014/06/button-1_11-300x213.png" alt="" width="100" height="71"></a>

                <a href="${pageContext.request.contextPath}/download/?file=Tipovye_pravila_priema_v_vuzy2012.docx"><img class="alignnone wp-image-81493" src="http://www.kstu.kz/wp-content/uploads/2014/06/button-3_1-300x213.png" alt="" width="100" height="71"></a>

                <a href="${pageContext.request.contextPath}/download/?file=Downloads.rar"><img class="alignnone wp-image-81490" src="http://www.kstu.kz/wp-content/uploads/2014/06/button-5_1-300x213.png" alt="" width="100" height="71"></a></p>
        </section>
    </div>
</div>
<footer></footer>


</body>
</html>