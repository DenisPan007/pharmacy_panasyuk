<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
    <title>Register</title>
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
<jsp:include page="header.jsp"></jsp:include>
<main role="main" class="container">
    <h3>Register for fun</h3>
    <form method="POST" action="${pageContext.request.contextPath}/start?command=signUp">
        <div class="form-group col-md-6 ">
            <label for="inputLogin">Login</label>
            <input type="text" name="login" class="form-control" id="inputLogin" placeholder="login" required/>
            <c:choose>
                <c:when test="${error!=null && error=='invalidLogin'}">
                    <div class="alert alert-danger" role="alert">
                        login must begin from letter
                    </div>
                </c:when>
                <c:when test="${error!=null && error=='reservedLogin'}">
                    <div class="alert alert-danger" role="alert">
                        this login is reserved
                    </div>
                </c:when>
            </c:choose>
        </div>
        <div class="form-group col-md-6">
            <label for="inputPassword">Password</label>
            <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password"
                   required/>
            <c:if test="${error!=null && error=='invalidPassword'}">
            <div class="alert alert-danger" role="alert">
                password length must be >=8
            </div>
            </c:if>
        </div>
        <div class="form-group col-md-6">
            <label for="inputEmail">Email address</label>
            <input type="email" name="email" class="form-control" id="inputEmail" aria-describedby="emailHelp"
                   placeholder="Enter email" required/>
            <small id="emailHelp" class="form-text text-muted">We'll send you a message.</small>
            <c:choose>
                <c:when test="${error!=null && error=='invalidEmail'}">
                    <div class="alert alert-danger" role="alert">
                        please, enter correct email
                    </div>
                </c:when>
                <c:when test="${error!=null && error=='reservedEmail'}">
                    <div class="alert alert-danger" role="alert">
                        this login is reserved
                    </div>
                </c:when>
            </c:choose>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</main>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
