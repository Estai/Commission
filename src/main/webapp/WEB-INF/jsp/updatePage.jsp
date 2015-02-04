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


        <label>Название группы: ${application.groupName}</label>
        <label>Приоритет: ${application.priority}</label>

      <div class="rows">
        <label>Новый приоритет:</label>
        <form action="" method="post" onchange="this.form.submit()">
        <select size="1" name="priority">
          <c:forEach var="priority" items="${priorities}">
          <option value="${priority}"<c:if test="${priority eq application.priority}"> selected</c:if>>${priority}</option>
          </c:forEach>
   </select>
        </form>
</div>

</section>
</div>
</div>
<footer></footer>


</body>
</html>