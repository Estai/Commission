<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Приемная комиссия</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/static/www/css/styles.css" rel="stylesheet">

    <script language="JavaScript">
//        function rewrite_days()
//        {
//            var days = document.getElementById("day");
//            var month = document.getElementById("month");
//            var year = document.getElementById("year");
//            var days_in_month = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
//            if (month.value != 0)
//            {
//                if ((year.value % 4 == 0) && (month.value == 2))
//                {
//                    days.length = 30;
//                    days.item(29).value = 29;
//                    days.item(29).text = 29;
//                }
//                else
//                {
//                    days.length = days_in_month[month.value - 1] + 1;
//                    for (var i = 29; i < days.length; i++)
//                    {
//                        days.item(i).value = i;
//                        days.item(i).text = i;
//                    }
//                }
//            }
//        }
        function add() {
            var node = document.createElement('div');
            var elem=document.getElementById('divNone').cloneNode(true);
            elem.style.display='block';
            node.innerHTML = "<span style='color: #00b3ee' >Добавить группу:</span>"
            document.getElementById('id').appendChild(node);
            document.getElementById('id').appendChild(elem);
        }
    </script>
</head>
<body>
<div class="wrapper container">
    <%@ include file="header.jspf" %>

    <div class="heading"></div>
    <div class="row">
        <aside class="col-md-7">
            <fmt:setBundle basename="app" var="rb"/>
            <p style="color:#011b6a;">© Мурых Е.Л.,Боранов Е.Т.,Паршина.,Әбеуов Е.Б.</p>
            <p style="color:#011b6a;">Творческие экзамены</p>
            <p style="color:#011b6a;">Специальность "Дизайн": <fmt:message key="examDesign" bundle="${rb}"/> </p>
            <p style="color:#011b6a;">Специальность "Начальная военная подготовка": <fmt:message key="examNVP" bundle="${rb}"/> </p>
        </aside>
        <section class="col-md-17 mainer">

            <fieldset>
            <legend >Персональные данные</legend>
        </fieldset>

            <c:if test="${not empty dataError }" >
                <span style="color: red; font-size: medium" >${dataError}<br></span>
            </c:if>
            <form action="${pageContext.request.contextPath}/do/comission" method="post">
                <div class="rows">
                    <label for="lastname">Фамилия:
                        <span class="required">*</span>
                    </label>
                    <input id="lastname" name="lastname" type="text" size="30" maxlength="50">
                </div>

                <div class="rows">
                    <label for="firstname">Имя:
                        <span class="required">*</span>
                    </label>
                    <input id="firstname" name="firstname" type="text" size="30" maxlength="50">
                </div>
                <div class="rows">
                    <label>Отчество:
                        <span class="required">*</span>
                    </label>
                    <input name="middlename" type="text" size="30" maxlength="15">
                </div>
                <div class="rows">
                    <label>ИИН:
                        <span class="required">*</span>
                    </label>
                    <input name="iin" type="text" size="30" maxlength="15">
                </div>
                <div class="rows">
                    <label>Дата рождения: <span class="required">*</span></label>
                    <select name="year" id="year" >

                        <option selected="" value="0">—</option>
                        <c:forEach items="${years}" var="year">
                            <option  value="${year}">${year}</option>
                        </c:forEach>

                    </select>
                    <select name="month" id="month" >
                        <option value=“″ selected>—</option>
                        <option value="January">Январь</option>
                        <option value="February">Февраль</option>
                        <option value="March">Март</option>
                        <option value="April">Апрель</option>
                        <option value="May">Май</option>
                        <option value="June">Июнь</option>
                        <option value="July">Июль</option>
                        <option value="August">Август</option>
                        <option value="September">Сентябрь</option>
                        <option value="October">Октябрь</option>
                        <option value="November">Ноябрь</option>
                        <option value="December">Декабрь</option>
                    </select>
                    <select name="day" id="day" >
                        <%--onChange="rewrite_days();"--%>
                        <option selected>—</option>
                        <c:forEach items="${days}" var="dayss">
                            <option  value="${dayss}">${dayss}</option>
                        </c:forEach>

                    </select>
                </div>
                <div class="rows">
                    <label>Пол:<span class="required">*</span></label>
                    <select name="gender" size="1">
                        <option selected value="MALE">Мужской</option>
                            <option value="FEMALE">Женский</option>
                    </select>
                </div>



                <div class="rows">
                    <label>Гражданство: <span class="required">*</span></label>
                    <select name="nationality" size="1" onchange='add();'>
                        <option selected value="Казахстан">Казахстан</option>
                        <option value="Россия">Россия</option>
                        <option value="Украина">Украина</option>
                        <option value="Узбекистан">Узбекистан</option>
                        <option value="Китай">Китай</option>
                        <option value="Таждикистан">Таждикистан</option>
                        <option value="Туркменистан">Туркменистан</option>
                        <option value="Азербайжан">Азербайжан</option>
                        <option value="Другое" >Другое</option>
                    </select>
                </div>
                <div id='id'></div>
                <div id="divNone" style="display: none">dvdfdfd</div>
                <div class="rows">
                    <label>Контактный телефон:</label>
                    <input class="input" type="text" name="contactNumber" size="30" maxlength="15">
                </div>
                <div class="rows">
                    <label>Email<span class="required">*</span></label>
                    <input class="input" type="email" name="email" size="30" maxlength="45">
                </div>
                <div class="rows">
                    <label>Адрес проживания: <span class="required">*</span></label>
                   <textarea name="address" style="width: 350; height: 150;"></textarea>
                </div>
                <div class="rows">
                    <label>Наименование окончившего учебного заведения: <span class="required">*</span></label>
                    <input class="input" type="text" name="educationalInstitution" size="50" maxlength="150">
                    <select name="region" size="1">
                        <option selected value="городская">городская</option>
                        <option value="сельская">сельская</option>
                    </select>
                </div>
                <div class="rows">
                    <label>Обладатель "Алтын белгі":<span class="required">*</span></label>
                    <select name="ownerGoldMedal" size="1">
                        <option  value="true">Да</option>
                        <option selected value="false">Нет</option>
                    </select>
                </div>
                <div class="rows">
                    <label>Отличник:<span class="required">*</span></label>
                    <select name="excellentPupil" size="1">
                        <option  value="true">Да</option>
                        <option selected value="false">Нет</option>
                    </select>
                </div>
                <div class="rows">
                    <label>Форма обучения :<span class="required">*</span></label>
                    <select name="formStudy" size="1">
                        <option  value="Сокращенная">Сокращенная</option>
                        <option selected value="Очная/полная">Очная/полная</option>
                        <option value="Заочная/сокращенная">Заочная/сокращенная</option>
                    </select>
                </div>
                <div class="rows">
                    <label>Язык обучения:<span class="required">*</span></label>
                    <select name="language" size="1">
                        <option  value="KAZAKH">казахский</option>
                        <option selected value="RUSSIAN">русский</option>
                    </select>
                </div>
                <div class="rows">
                    <label>Номер сертификатов(ЕНТ/КТА):
                        <span class="required">*</span>
                    </label>
                    Серия <input name="seria" type="text" size="2" maxlength="3">
                    Номер <input name="number" type="text" size="7" maxlength="7">
                </div>


                <c:forEach var="subject" items="${subjects}">
                <c:if test="${subject.main}">
                <div class="rows">
                    <label>Предмет: <br>${subject.name}
                        <span class="required">*</span>
                    </label>
                    Баллы(оценки) <span class="required">*</span>
                    <select name="score[${subject.id}]" size="1">
                        <option disabled selected>Выберите оценку</option>
                        <c:forEach var="score" items="${scores}">
                            <option value="${score.id}">${score.score}</option>
                        </c:forEach>
                    </select>
                </div>
                </c:if>
                </c:forEach>

                <div class="rows">
                    <label>Предмет:
                        <span class="required">*</span>
                    </label>

                        <select name="subj" size="1">
                        <option disabled selected>Выберите Предмет</option>
                        <c:forEach var="subject" items="${subjects}">
                            <c:if test="${not subject.main}">
                        <option value="${subject.id}">${subject.name}</option>
                            </c:if>
                        </c:forEach>
                        </select>
                    Баллы(оценки) <span class="required">*</span>
                    <select name="score" size="1">
                        <option disabled selected>Выберите оценку</option>
                        <c:forEach var="score" items="${scores}">
                            <option value="${score.id}">${score.score}</option>
                        </c:forEach>
                    </select>
                </div>

                <p>
                    <input type="hidden" name="user.id" value="${user.id}">
                    <input type="submit" name="submit" value="Сохранить">
                </p></form>

        </section>
    </div>
</div>
<footer></footer>


</body>
</html>
