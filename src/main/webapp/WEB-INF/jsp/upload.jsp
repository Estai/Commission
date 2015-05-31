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
      <fieldset>
        <legend >Сканированные документы</legend>
      </fieldset>

   <form action="${pageContext.request.contextPath}/uploads" method="post" enctype="multipart/form-data">
     <div class="rows">
       <label>Удостоверение личности:
         <span class="required">*</span>
       </label>
     <input type="file" name="data">
       </div>

     <%--<div class="rows">--%>
       <%--<label>Документа об образовании(подлинник):--%>
         <%--<span class="required">*</span>--%>
       <%--</label>--%>
       <%--<input type="file" name="data">--%>
     <%--</div>--%>
     <%--<div class="rows">--%>
       <%--<label>Сертификат ЕНТ/КТА:--%>
         <%--<span class="required">*</span>--%>
       <%--</label>--%>
       <%--<input type="file" name="data">--%>
     <%--</div>--%>
     <%--<div class="rows">--%>
       <%--<label>Медицинская справка формы 086-У:--%>
         <%--<span class="required">*</span>--%>
       <%--</label>--%>
       <%--<input type="file" name="data">--%>
     <%--</div>--%>
     <%--<div class="rows">--%>
       <%--<label>4 фотокарточки размером 3x4:--%>
         <%--<span class="required">*</span>--%>
       <%--</label>--%>
       <%--<input type="file" name="data">--%>
     <%--</div>--%>
     <input type=submit value="Загрузить">
   </form>

    </section>
  </div>
</div>
<footer></footer>


</body>
</html>
