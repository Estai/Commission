<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Университет</title>
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

            <h3>Кафедры</h3>
            <tr>
                <td style='padding-top:5px'><a href='#' title=''>
                    <div class='profile'>
                        <img src='' style='margin:0px 10px 0px 0px;float: left;display:block;' title=''>
                        <b>Название</b> <br>
                        <b>Количество кафедр</b> <br>
                        <b>Декан</b><br> <br>
                    </div>
                </a></td>
            </tr>


        </section>
    </div>
</div>
<footer></footer>


</body>
</html>