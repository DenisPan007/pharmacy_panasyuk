<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <script>
        function go(id, button) {
            var tdTag = button.parentElement;
            var trTag = tdTag.parentElement;
            var tbodyTag = trTag.parentElement;
            tbodyTag.removeChild(trTag);
        }
    </script>
</head>
<body>
<c:set var="commandFix" value="getUserList" scope="page"/>
<form method="POST" action="${pageContext.request.contextPath}/start">
    <input type="hidden" name="command" value=${commandFix}>
    <button type="submit" >Refresh</button>
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

<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" charset="utf8"
        src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#example').DataTable({
            "order": [[3, "desc"]]
        });
    });
</script>
</body>
        </html>