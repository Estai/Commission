<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <link href="${pageContext.request.contextPath}/static/www/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="wrapper container">
  <%@ include file="../headerAdmin.jspf" %>
  <c:if test="${not empty faculties}">
  <form action="${pageContext.request.contextPath}/do/deleteFaculty" method="post">
    <c:forEach var="faculty" items="${faculties}">

              <input type="checkbox" name="faculty"  value="${faculty.id}"/><c:out value="${faculty.name}"/><br>


    </c:forEach>
    <input class="btn btn-blue" type="submit" value="Удалить">
  </form>

  </c:if>
  <a class="btn" href="${pageContext.request.contextPath}/do/addPage">Добавить</a>
</div>
</body>
</html>
