<%@ page  contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle  basename="pagecontent" var="rb" scope="request" />
<fmt:setBundle  basename="messages" var="messageRb" scope="request" />
<head>
    <title><fmt:message key="sign-up.title" bundle="${ rb }" /></title>
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
    <form method="POST" action="${pageContext.request.contextPath}/?command=signUp" >
        <div class="form-group col-md-6 ">
            <label for="inputLogin"><fmt:message key="label.login" bundle="${ rb }" /></label>
            <input type="text" name="login" class="form-control" id="inputLogin" placeholder=<fmt:message key="placeholder.login" bundle="${ rb }"/> required/>
            <c:choose>
                <c:when test="${error=='invalidLogin'}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="alert.invalid-login" bundle="${ messageRb }" />
                    </div>
                </c:when>
                <c:when test="${error=='reservedLogin'}">
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
            <c:if test="${error=='invalidPassword'}">
            <div class="alert alert-danger" role="alert">
                <fmt:message key="alert.invalid-password" bundle="${ messageRb }" />
            </div>
            </c:if>
        </div>
        <div class="form-group col-md-6" >
            <label for="inputName"><fmt:message key="label.name" bundle="${ rb }" /></label>
            <input type="text" name="name" class="form-control" id="inputName"
                   placeholder=<fmt:message key="placeholder.name" bundle="${ rb }" /> required/>
            <c:if test="${error=='invalidName'}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.invalid-name" bundle="${ messageRb }" />
                </div>
            </c:if>
        </div>
        <div class="form-group col-md-6">
            <label for="inputLastName"><fmt:message key="label.lastname" bundle="${ rb }" /></label>
            <input type="text" name="lastName" class="form-control" id="inputLastName"
                   placeholder=<fmt:message key="placeholder.lastname" bundle="${ rb }" /> required/>
            <c:if test="${error=='invalidLastName'}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.invalid-surname" bundle="${ messageRb }" />
                </div>
            </c:if>
        </div>
        <div class="form-group col-md-6">
            <label for="inputAddress"><fmt:message key="label.address" bundle="${ rb }" /></label>
            <input type="text" name="address" class="form-control" id="inputAddress"
                   placeholder=<fmt:message key="placeholder.address" bundle="${ rb }" /> required/>
            <c:if test="${error=='invalidAddress'}">
                <div class="alert alert-danger" role="alert">
                    <fmt:message key="alert.invalid-address" bundle="${ messageRb }" />
                </div>
            </c:if>
        </div>
        <div class="form-group col-md-6">
            <label for="inputEmail"><fmt:message key="placeholder.email" bundle="${ rb }" /></label>
            <input type="email" name="email" class="form-control" id="inputEmail" aria-describedby="emailHelp"
                   placeholder="Enter email" required/>
            <c:choose>
                <c:when test="${error=='invalidEmail'}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="alert.invalid-email" bundle="${ messageRb }" />
                    </div>
                </c:when>
                <c:when test="${error=='reservedEmail'}">
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
