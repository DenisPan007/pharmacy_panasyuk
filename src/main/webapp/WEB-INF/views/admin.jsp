<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
БЛЕТКИ
<body>
<form method="POST" action="${pageContext.request.contextPath}/start">
    <c:set var="commandFix" value="getUserList" scope="page"/>
    <input type="hidden" name="command" value=${commandFix}>
<button type="submit" class="btn btn-primary">Refresh</button>
</form>

<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
        </thead>
    <c:forEach var="elem" items="${list}" varStatus="status">
        <tbody>
            <tr>
                <td>${elem.id}</td>
                <td>${elem.login}</td>
                <td>${elem.firstName}</td>
                <td>${elem.lastName}</td>
                <td>${elem.email}</td>
                <td>${elem.role}</td>
            </tr>
        </tbody>
    </c:forEach>
        <tfoot>
            <tr>
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
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
    $('#example').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );
</script>
</body>
</html>