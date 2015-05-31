<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <link href="${pageContext.request.contextPath}/static/www/css/styles.css" rel="stylesheet">
  <script language="JavaScript">
    function add() {
      var node = document.createElement('div');
      var elem=document.getElementById('divNone').cloneNode(true);
      elem.style.display='block';
      node.innerHTML = "<span style='color: #00b3ee' >Добавить группу:</span> <div class='rows'> <label>Название группы: <span class='required'>*</span>" +
      "</label> <input name='group' type='text' size='30' maxlength='50' > </div>" +
      "<div class='rows'> <label>Количество студентов: <span class='required'>*</span> </label> " +
      "<input name='number' type='text' size='30' maxlength='50'></div> " +
      "<div class='rows'> <label>Творческий экзамен: <span class='required'>*</span> </label> " +
      "<select name='exam' size='1'> <option selected value='true'>Да</option> <option value='false'>Нет</option> </select></div> " +
      "<div class='rows' style='float: left'> <label> Профильный предмет: <span class='required'>*</span></label>" +
      "</div> </div>"
      document.getElementById('id').appendChild(node);
      document.getElementById('id').appendChild(elem);
    }
  </script>
</head>
<body>
<div class="wrapper container">
  <%@ include file="../headerAdmin.jspf" %>
  <c:if test="${not empty Parameter}">
   ${Parameter}</c:if>
  <form  action="${pageContext.request.contextPath}/do/addFaculty" method="post">
    <div class="rows">
      <label>Название факультета:
        <span class="required">*</span>
      </label>
      <input name="name" type="text" size="30" maxlength="50" >
    </div>
    <div class="rows">
      <label>Декан:
        <span class="required">*</span>
      </label>
      <input name="decan" type="text" size="30" maxlength="50">
    </div>
    <div id='id'>
    </div>
    <br>
    <input type='button' value='Добавить группу' onClick='add();'>
    <p>
      <input type="submit" name="submit" value="Добавить факультет">
    </p>
  </form>
 <div id="divNone" style="display: none"> <select name="subj" size="1">
    <c:forEach var="subject" items="${subjects}">
      <c:if test="${not subject.main}">
        <option value="${subject.id}" <c:if test="${subject.name}">selected</c:if>>${subject.name}</option>
      </c:if>
    </c:forEach>
  </select></div>




</div>
</body>
</html>
