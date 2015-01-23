<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error/Exception Information</title>
</head>
<body>
<div style="float: left;
    position: relative; top: 70px; left: 500px">
    <c:choose>

        <c:when test="${statusCode!= null}">
            <h2 STYLE="color: red"> Error information </h2>

            <p>The status code : <b> ${statusCode} </b></p>

            <p>The request URI: ${requestUri}</p>
            <a href="/"><img src="${pageContext.request.contextPath}/static/www/image/error.jpg" alt="Logo" width="150"
                             height="150" align="left" class="img-rounded"></a>
        </c:when>
        <c:otherwise>
            <c:out value="<h2>Error information</h2>"/>
        </c:otherwise>
    </c:choose>
</div>


</body>
</html>
