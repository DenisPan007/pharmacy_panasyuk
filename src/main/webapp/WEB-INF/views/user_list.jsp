<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <script>function getXMLHttpRequest() {
        var xmlHttpReq;
        // to create XMLHttpRequest object in non-Microsoft browsers
        if (window.XMLHttpRequest) {
            xmlHttpReq = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            try {
                //to create XMLHttpRequest object in later versions of Internet Explorer
                xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (exp1) {
                try {
                    //to create XMLHttpRequest object in later versions of Internet Explorer
                    xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (exp2) {
                    //xmlHttpReq = false;
                    alert("Exception in getXMLHttpRequest()!");
                }
            }
        }
        return xmlHttpReq;
    }
    </script>
    <script>
        function go(id, button) {
            var body = 'command=' + encodeURIComponent("deleteUser") + '&id=' + encodeURIComponent(id);
            var req = getXMLHttpRequest();
            req.onreadystatechange = function () {
                if (req.readyState === 4) {
                    if (req.status === 200) {
                        alert(req.responseText);
                        var tdTag = button.parentElement;
                        var trTag = tdTag.parentElement;
                        var tbodyTag = trTag.parentElement;
                        tbodyTag.removeChild(trTag);
                    }
                }
            };
            req.open('POST', '/pharmacy/ajax', true);
            req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            req.send(body);
        }
    </script>
</head>
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
</html>