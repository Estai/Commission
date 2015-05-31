<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <link href="${pageContext.request.contextPath}/static/www/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="wrapper container">
  <%@ include file="../headerAdmin.jspf" %>
  <fieldset>
    <legend >Список абитуриентов</legend>
  </fieldset>
  <table class="table table-bordered">
    <caption></caption>
    <thead>
    <tr>
      <th>Id</th>
      <th>ФИО</th>
      <th>Номер сертификата</th>
      <th>Email</th>
      <th>Документы</th>
      <th>Сообщение</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${enrollees}" var="enrollee">

        <tr>
          <td>${enrollee.id}</td>
          <td>${enrollee.lastName} ${enrollee.firstName} ${enrollee.middleName}</td>
          <td>${enrollee.certificateNumber}</td>
          <td>${enrollee.email}</td>

          <td><a href="${pageContext.request.contextPath}/download/?file=${enrollee.lastName}_${enrollee.firstName}.zip"><input type="button" value="Загрузить документы"></a></td>
          <td><textarea name="message" style="width: 250; height: 100;">

          </textarea>
            <form action="${pageContext.request.contextPath}/do/mail" method="post">
              <input type="hidden" name="email" value="${enrollee.email}">
            <input type="submit" value="Отправить сообщение">
           </form>
          </td>

        </tr>

    </c:forEach>
    </tbody></table>

  <a class="btn" href="${pageContext.request.contextPath}/do/admin/saveExcel">Сохранить в Excel</a>
</div>
</body>
</html>