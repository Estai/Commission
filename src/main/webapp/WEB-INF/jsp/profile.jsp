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
      <h3>Профиль</h3>
      <c:out value="${success}"/>
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
      <c:if test="${not empty applications}">
      <h3>Список групп</h3>
      <form action="${pageContext.request.contextPath}/do/removeApplication" method="post">
      <table border="1">

        <caption>Список групп </caption>
        <tr>
          <th></th>
          <th>Название группы</th>
          <th>Приоритет</th>
        </tr>
        <c:forEach var="statement" items="${applications}">
          <tr><td class="checkbox">
            <input type="checkbox" name="${statement.id}" value="${statement.id}">
          </td>
            <td> <c:out value="${statement.groupName}"/></td><td>
            ${statement.priority}
            </td>
            <td style="border: hidden; border-left:thick">
            <a class="" href="${pageContext.request.contextPath}/do/getPriority?id=${statement.id}">Изменить приоритет</a>
            </td>
          </tr>
        </c:forEach>
      </table>
        <p>
          <input type="submit" name="submit" value="Удалить">
        </p>
      </form>
        <br>
      </c:if>
<c:if test="${not empty enrollee}">
  <h3>Данные абитуриента</h3>
      <form action="${pageContext.request.contextPath}/do/changeInfoEnrollee" method="post">
        <div class="rows">
          <label>Фамилия:
            <span class="required">*</span>
          </label>
          <input name="lastname" type="text" size="30" maxlength="50" value="${enrollee.lastName}">
        </div>

        <div class="rows">
          <label>Имя:
            <span class="required">*</span>
          </label>
          <input name="firstname" type="text" size="30" maxlength="50" value="${enrollee.firstName}">
        </div>
        <div class="rows">
          <label>Отчество:
            <span class="required">*</span>
          </label>
          <input name="middlename" type="text" size="30" maxlength="15"value="${enrollee.middleName}">
        </div>
        <div class="rows">
          <label>Номер аттестата:
            <span class="required">*</span>
          </label>
          <input name="number" type="text" size="30" maxlength="15" value="${enrollee.certificateNumber}">
        </div>
        <div class="rows">
          <label>Email:
            <span class="required">*</span>
          </label>
          <input name="email" type="text" size="30" maxlength="15" value="${enrollee.email}">
        </div>
        <div class="rows">
          <label>Адрес проживания :
            <span class="required">*</span>
          </label>
          <textarea  name="address"  style="width: 150; height: 150;">${enrollee.address}</textarea>
        </div>
        <div class="rows">
          <label>Контактный телефон::
            <span class="required">*</span>
          </label>
          <input name="mobileNumber" type="text" size="30" maxlength="15" value="${enrollee.mobileNumber}">
        </div>
        <c:forEach var="subjectsMap" items="${subjectsMap}">
         <c:if test="${subjectsMap.key.main}">
          <div class="rows">
            <label>Предмет: <br>${subjectsMap.key.name}
              <span class="required">*</span>
            </label>
            Баллы(оценки) <span class="required">*</span>
            <select name="scoreID[${subjectsMap.key.id}]" size="1">

              <c:forEach var="score" items="${scores}">
                <option value="${score.id}"<c:if test="${score.id eq subjectsMap.value.id}"> selected</c:if>>${score.score}</option>
              </c:forEach>
            </select>
          </div>
         </c:if>
        </c:forEach>


        <c:forEach var="subjectsMap" items="${subjectsMap}">
          <c:if test="${not subjectsMap.key.main}">
            <div class="rows">
                <label>Предмет:
                <span class="required">*</span>
                </label>
                <select name="subj" size="1">
                <c:forEach var="subject" items="${subjects}">
                  <c:if test="${not subject.main}">
                  <option value="${subject.id}" <c:if test="${subject.name eq subjectsMap.key.name}"> selected</c:if>>${subject.name}</option>
                  </c:if>
                </c:forEach>
                </select>
              Баллы(оценки) <span class="required">*</span>
              <select name="score[]" size="1">

                <c:forEach var="score" items="${scores}">
                  <option value="${score.id}"<c:if test="${score.id eq subjectsMap.value.id}"> selected</c:if>>${score.score}</option>
                </c:forEach>
              </select>
            </div>
          </c:if>
        </c:forEach>

        <p>
          <input type="submit" name="submit" value="Сохранить">
        </p>
      </form>
</c:if>
    </section>
  </div>
</div>
<footer></footer>


</body>
</html>
