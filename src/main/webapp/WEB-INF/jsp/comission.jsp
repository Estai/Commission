<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Приемная комиссия</title>

    <script type="text/javascript">
        function open(id){
            display = document.getElementById(id).style.display;
            if(display=="none"){
                document.getElementById(id).style.display="block";
            }else{
                document.getElementById(id).style.display="none";
            }
        }
    </script>
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

            Выберите Группу:

       <form action="${pageContext.request.contextPath}/do/group" method="post">
                <c:forEach var="faculty" items="${faculties}">
                    <div onclick="open('${faculty.id}')"><c:out value="${faculty.name}"/></div>
                    <div id="${faculty.id}">
                <c:forEach var="group" items="${groups}">
                <c:choose>
                    <c:when test="${group.idFaculty==faculty.id}">
                        <input type="checkbox" name="${group.name}" id="${group.id}" value="${group.id}"/><c:out value="${group.name}"/><br>
                    </c:when>
                </c:choose>
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