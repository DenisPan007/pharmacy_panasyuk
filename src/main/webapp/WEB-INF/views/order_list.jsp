<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<table id="orderList" class="display " style="width:100%">
    <thead class="thead-dark">
    <tr>
        <th></th>
        <th>Number</th>
        <th>Price</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${orderList}" varStatus="status">
        <tr>
            <td>
                <button class="btn btn-primary" onclick="details(${elem.id},this)">Details</button>
            </td>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.price}"></c:out></td>
            <td><c:out value="${elem.status}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>