<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <link href="${pageContext.request.contextPath}/static/www/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="wrapper container">
  <%@ include file="../headerAdmin.jspf" %>
  <c:if test="${not empty success}" >
         <span style="color: green; font-size: medium" >
                 ${success}<br></span>
  </c:if>
  <form action="${pageContext.request.contextPath}/uploads" method="post" enctype="multipart/form-data">
  <div class="rows">

    <label>Документ:</label>
    <input type="file" name="data">
  </div>
    <input type=submit value="Загрузить">
    </form>
</div>
</body>
</html>