<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<fmt:setBundle basename="messages" var="messageRb" scope="request"/>
<html>
<head>
    <title>login</title>
    <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<main role="main" class="container">
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
