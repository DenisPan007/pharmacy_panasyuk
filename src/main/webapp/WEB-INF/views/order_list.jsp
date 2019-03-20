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
                <c:choose>
                    <c:when test="${elem.status=='AT_WORK'}">
                            <button type="button" class="btn btn-dark" onclick="confirmReceipt(${elem.id},this)">Confirm</button>
                    </c:when>

                <c:when test="${elem.status=='NEW'}">
                    <form method="POST" action="${pageContext.request.contextPath}/?command=toOrder">
                        <input type="hidden" name="orderId" value="${elem.id}">
                        <button type="submit" class="btn btn-dark">Pay</button>
                    </form>
                </c:when>
                    <c:when test="${elem.status=='COMPLETED'}">
                        Confirmed
                    </c:when>
                </c:choose>
            </td>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.price}"></c:out></td>
            <td><c:out value="${elem.status}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>