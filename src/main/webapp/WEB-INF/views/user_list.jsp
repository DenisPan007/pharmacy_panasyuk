<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<c:set var="commandFix" value="getUserList" scope="page"/>
<form method="POST" action="${pageContext.request.contextPath}/start">
    <input type="hidden" name="command" value=${commandFix}>
    <button type="submit">Refresh</button>
</form>


<table id="example" class="display" style="width:100%">
    <thead>
    <tr>
        <th></th>
        <th>Id</th>
        <th>Login</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Email</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${list}" varStatus="status">
        <tr>
            <td>
                <button class="btn btn-primary" onclick="go(${elem.id},this)">Delete</button>
            </td>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.login}"></c:out></td>
            <td><c:out value="${elem.firstName}"></c:out></td>
            <td><c:out value="${elem.lastName}"></c:out></td>
            <td><c:out value="${elem.email}"></c:out></td>
            <td><c:out value="${elem.role}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <th></th>
        <th>Id</th>
        <th>Login</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Email</th>
        <th>Role</th>
    </tr>
    </tfoot>
</table>
</body>