<%--
  Created by IntelliJ IDEA.
  User: Den
  Date: 27.02.2019
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="/pharmacy/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <title>error</title>
</head>
<body>
<h1>Ups!</h1>
    <div class="alert alert-danger" role="alert">
       ${error}
    </div>
</body>
</html>
