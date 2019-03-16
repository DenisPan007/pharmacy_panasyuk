<%@ page  contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle  basename="pagecontent" var="rb" scope="request" />
<fmt:setBundle  basename="messages" var="messageRb" scope="request" />
<head>
    <title><fmt:message key="sign-up.title" bundle="${ rb }" /></title>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
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
    <form method="POST" action="${pageContext.request.contextPath}/?command=signUp" >
        <div class="form-group col-md-6 ">
            <label for="inputLogin"><fmt:message key="label.login" bundle="${ rb }" /></label>
            <input type="text" name="login" class="form-control" id="inputLogin" placeholder=<fmt:message key="placeholder.login" bundle="${ rb }"/> required/>
            <c:choose>
                <c:when test="${error!=null && error=='invalidLogin'}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="alert.invalid-login" bundle="${ messageRb }" />
                    </div>
                </c:when>
                <c:when test="${error!=null && error=='reservedLogin'}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="alert.reserved-login" bundle="${ messageRb }" />
                    </div>
                </c:when>
            </c:choose>
        </div>
        <div class="form-group col-md-6">
            <label for="inputPassword"><fmt:message key="label.password" bundle="${ rb }" /></label>
            <fmt:message key="placeholder.password" bundle="${ rb }" var="passwordPlaceholder"/>
            <input type="password" name="password" class="form-control" id="inputPassword" placeholder=${passwordPlaceholder} required/>
            <c:if test="${error!=null && error=='invalidPassword'}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="alert.invalid-password" bundle="${ messageRb }" />
            </div>
            </c:if>
        </div>
        <div class="form-group col-md-6" >
            <label for="inputName"><fmt:message key="label.name" bundle="${ rb }" /></label>
            <input type="text" name="name" class="form-control" id="inputName"
                   placeholder=<fmt:message key="placeholder.name" bundle="${ rb }" /> required/>
        </div>
        <div class="form-group col-md-6">
            <label for="inputLastName"><fmt:message key="label.lastname" bundle="${ rb }" /></label>
            <input type="text" name="lastName" class="form-control" id="inputLastName"
                   placeholder=<fmt:message key="placeholder.lastname" bundle="${ rb }" /> required/>
        </div>
        <div class="form-group col-md-6">
            <label for="inputAddress"><fmt:message key="label.address" bundle="${ rb }" /></label>
            <input type="text" name="address" class="form-control" id="inputAddress"
                   placeholder=<fmt:message key="placeholder.address" bundle="${ rb }" /> required/>
        </div>
        <div class="form-group col-md-6">
            <label for="inputEmail"><fmt:message key="placeholder.email" bundle="${ rb }" /></label>
            <input type="email" name="email" class="form-control" id="inputEmail" aria-describedby="emailHelp"
                   placeholder="Enter email" required/>
            <c:choose>
                <c:when test="${error!=null && error=='invalidEmail'}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="alert.invalid-email" bundle="${ messageRb }" />
                    </div>
                </c:when>
                <c:when test="${error!=null && error=='reservedEmail'}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="alert.reserved-email" bundle="${ messageRb }" />
                    </div>
                </c:when>
            </c:choose>
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="button.sign-up" bundle="${ rb }" /></button>
    </form>

</main>
</body>
