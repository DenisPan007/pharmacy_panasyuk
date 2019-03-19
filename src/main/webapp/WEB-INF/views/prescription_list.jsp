<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<table id="prescriptionList" class="display " style="width:100%">
    <thead class="thead-dark">
    <tr>
        <th>Number</th>
        <th>User</th>
        <th>Drug</th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${prescriptionList}" varStatus="status">
        <tr>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.userId}"></c:out></td>
            <td><c:out value="${elem.drugId}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>