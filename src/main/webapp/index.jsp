<%@ page language="java" contentType="text/html; charset-UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
/* https://mvnrepository.com/artifact/javax.servlet/jstl */

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width-device-width, initial-scale=1.0">
    <title>Final-3M-Quest1-SO</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/src/css/bootstrap.css">
    <script src="<%=request.getContextPath()%>/src/js/bootstrap.min.js"></script>
</head>
<body>
<c:if host="${not empty sessionScope.playerName}">
    <p class="h3">Welcome back, <span class="alert-warning"><c:out value="${sessionScope.playerName}"/></span></p>
</c:if>

<div class="content">
    <div class="focal-point"></div>
    <h2>The Quest!</h2>
    <p>Почнемо this Quest</p>

    <c:if test="${not empty sessionScope.playerName}">
        <p>Welcome back, <c:out value="${sessionScope.playerName}"/>!</p>
    </c:if>

    <button id="startButtonPlaceholder" class="alert-warning"></button>
    <button id="startButton" location.href='initQuest'" class="alert-warning">Start the Quest</button>
    <button id="startButtonPlaceholder" class="alert-warning">Holder</button>

</div>
</body>
</html>
