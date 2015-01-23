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
      <h3>Профиль</h3>


        <form action="${pageContext.request.contextPath}/do/changePass" method="post">

          <div class="rows">
            <label>Логин:
              <span class="required">*</span>
            </label>
            <input name="log" type="text" size="30" maxlength="15" value="${user.login}">
          </div>

          <div class="rows">
            <label>Пароль:
              <span class="required">*</span>
            </label>
            <input name="pass" type="password" size="30" maxlength="15">
          </div>
          <div class="rows">
            <label>Новый пароль:
              <span class="required">*</span>
            </label>
            <input name="newPass" type="password" size="30" maxlength="15">
          </div>
          <p>
            <input type="submit" name="submit" value="Изменить">
          </p></form>
      <h3>Список групп</h3>
      <form action="" method="post">
      <table border="1">

        <caption>Список групп </caption>
        <tr>
          <th></th>
          <th>Название группы</th>
          <th>Приоритет</th>
        </tr>
        <c:forEach var="statement" items="${listStatement}">
          <tr><td>
            <input type="checkbox" name="id" >
          </td>
            <td> <c:out value="${statement.groupName}"/></td><td> <c:out value="${statement.priority}"/><br></td></tr>
        </c:forEach>
      </table>

      </form>
        <br>

      <h3>Данные абитуриента</h3>

      <form action="${pageContext.request.contextPath}/do/comission" method="post">
        <div class="rows">
          <label>Фамилия:
            <span class="required">*</span>
          </label>
          <input name="lastname" type="text" size="30" maxlength="50" value="">
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

        <%--<c:forEach var="select" items="${lst}">--%>
        <%--<div class="rows">--%>
          <%--<label>Предмет:--%>
            <%--<span class="required">*</span>--%>
          <%--</label>--%>
          <%--<select name="subj[${select}]" size="1">--%>
            <%--<option disabled>Выберите Предмет</option>--%>
            <%--<c:forEach var="list" items="${subjects}">--%>
              <%--<option value="${list.id}">${list.name}</option>--%>
            <%--</c:forEach>--%>
          <%--</select>--%>
          <%--Баллы(оценки) <span class="required">*</span>--%>
          <%--<select name="score[${select}]" size="1">--%>
            <%--<option disabled>Выберите оценку</option>--%>
            <%--<c:forEach var="list1" items="${scores}">--%>
              <%--<option value="${list1.id}">${list1.score}</option>--%>
            <%--</c:forEach>--%>
          <%--</select>--%>
        <%--</div>--%>
        <%--</c:forEach>--%>
        <p>
          <input type="hidden" name="user.id" value="${user.id}">
          <input type="submit" name="submit" value="Сохранить">
        </p>
      </form>

    </section>
  </div>
</div>
<footer></footer>


</body>
</html>
