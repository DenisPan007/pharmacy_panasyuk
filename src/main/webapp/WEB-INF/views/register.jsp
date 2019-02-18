<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <title>Register</title>
</head>
<body>

<jsp:include page="../../mypage.jsp"></jsp:include>

<h3>Register for fun</h3>
<div>
  <div class="form">
    <form  method="POST" action="${pageContext.request.contextPath}/register">
      <input type="text" name="login"  placeholder="login"/>
      <input type="password" name="password"  placeholder="password"/>
      <input type="text" name="email"  placeholder="email"/>
      <button>create</button>
    </form>
  </div>
</div>