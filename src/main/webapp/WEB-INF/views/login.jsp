<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<fmt:setBundle basename="messages" var="messageRb" scope="request"/>
<html>
<head>
    <title>login</title>
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
    <c:if test="${message=='success'}">
        <div class="alert alert-success" role="alert">
            <fmt:message key="alert.successfully-sending" bundle="${ messageRb }"/>
        </div>
    </c:if>
    <form method="POST" action="${pageContext.request.contextPath}/">
        <input type="hidden" name="initialCommand" value=${initialCommand}>
        <input type="hidden" name="command" value="login">
        <fmt:message key="placeholder.login" bundle="${ rb }" var="loginPlaceholder"/>
        <div class="form-group col-md-6 ">
            <input type="text" name="login" class="form-control" id="inputLogin"
                   placeholder=${loginPlaceholder} required/>
        </div>
        <fmt:message key="placeholder.password" bundle="${ rb }" var="passwordPlaceholder"/>
        <div class="form-group col-md-6">
            <input type="password" name="password" class="form-control" id="inputPassword"
                   placeholder=${passwordPlaceholder} required/>
        </div>
        <c:if test="${error=='incorrectAuthentication'}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="alert.incorrect-authentication" bundle="${ messageRb }"/>
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary"><fmt:message key="button.login"
                                                                   bundle="${ rb }"/></button>
        <a class="underlineHover"
           href="${pageContext.request.contextPath}/?command=toForgotPassword"><fmt:message
                key="link.forgot-password" bundle="${ rb }"/></a>
    </form>
    <h1>Login: admin</h1>
    <h1>Password: admin</h1>
</main>
</body>
</html>
