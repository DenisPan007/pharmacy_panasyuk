<%@ page  contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle  basename="pagecontent" var="rb" scope="request" />
<fmt:setBundle  basename="messages" var="messageRb" scope="request" />
<head>
    <title><fmt:message key="forgot-password.title" bundle="${ rb }" /></title>
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
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
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
