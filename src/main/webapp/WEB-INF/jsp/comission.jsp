<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Приемная комиссия</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/static/www/css/styles.css" rel="stylesheet">
    <script language="JavaScript">
        function open(id){
            if(document.getElementById(id).style.display=="none"){
                document.getElementById(id).style.display="block";
            }else{
                document.getElementById(id).style.display="none";
            }
        }
    </script>


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
            <h3>Приемная комиссия</h3>
         <c:if test="${not empty DataError }" >
         <span style="color: red; font-size: medium" >${DataError}<br></span>
         </c:if>
            <c:if test="${not empty success}" >
         <span style="color: green; font-size: medium" >
                 ${success}<br></span>
            </c:if>
            Выберите Группу:
            <form action="${pageContext.request.contextPath}/do/group" method="post">
                <c:forEach var="faculty" items="${faculties}">
                    <div onclick="open(${faculty.id})"><span style="color: blue"><c:out value="${faculty.name}"/></span></div>
                    <div id="${faculty.id}" style="display: block">
                <c:forEach var="group" items="${faculty.groups}">
                        <input type="checkbox" name="group"  value="${group.id}"/><c:out value="${group.name}"/><br>
                </c:forEach>
                    </div>
                </c:forEach>
           <input type="submit" value="Подать заявку">
       </form>



                <%--<p><select size="1" name="faculty" onchange="this.form.submit();">--%>
                    <%--<option disabled selected>Выберите факультет</option>--%>
                    <%--<c:forEach var="list" items="${faculties}">--%>
                        <%--<option  value="${list.id}">${list.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select></p>--%>

            <%--Выберите группу:--%>
            <%--<form action="${pageContext.request.contextPath}/do/group" method="post">--%>
                <%--<p><select size="1" name="group" onchange="this.form.submit();">--%>
                    <%--<option disabled selected>Выберите группу</option>--%>
                    <%--<c:forEach var="list" items="${groups}">--%>
                        <%--<option value="${list.id}">${list.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select></p>--%>

            <%--</form>--%>


        </section>
    </div>
</div>
<footer></footer>


</body>
</html>