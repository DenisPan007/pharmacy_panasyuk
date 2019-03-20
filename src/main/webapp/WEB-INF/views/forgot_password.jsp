<%@ page  contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle  basename="pagecontent" var="rb" scope="request" />
<fmt:setBundle  basename="messages" var="messageRb" scope="request" />
<head>
    <title><fmt:message key="forgot-password.title" bundle="${ rb }" /></title>
    <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/project.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<main role="main" class="container">
    <form method="POST" action="${pageContext.request.contextPath}/?command=changePassword">
        <div class="form-group col-md-6 " >
            <label for="inputLogin"><fmt:message key="placeholder.login" bundle="${ rb }" /></label>
            <input type="text" name="login" class="form-control" id="inputLogin" placeholder="login" required/>
        </div>
        <div class="form-group col-md-6">
            <label for="inputEmail"><fmt:message key="placeholder.email" bundle="${ rb }" /></label>
            <input type="email" name="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="email" required/>

        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="button.send-new-password" bundle="${ rb }" /></button>
    </form>
    <c:if test="${error=='incorrectData'}">
    <div class="alert alert-danger" role="alert">
        <fmt:message key="alert.incorrect-login-or-email" bundle="${ messageRb }" />
    </div>
    </c:if>
</main>
</body>
