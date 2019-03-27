<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<c:forEach var="elem" items="${orderList}" varStatus="status">
    <tr>


        <td>
            <c:choose>
                <c:when test="${elem.status=='AT_WORK'}">
                    <button type="button" class="btn btn-dark" onclick="confirmReceipt(${elem.id},this)"><fmt:message key="button.confirm" bundle="${ rb }"/></button>
                </c:when>

                <c:when test="${elem.status=='NEW'}">
                    <form method="POST" action="${pageContext.request.contextPath}/?command=toOrder">
                        <input type="hidden" name="orderId" value="${elem.id}">
                        <button type="submit" class="btn btn-dark"><fmt:message key="button.checkout" bundle="${ rb }"/></button>
                    </form>
                </c:when>
            </c:choose>
        </td>
        <td><c:out value="${elem.id}"></c:out></td>
        <td><c:out value="${elem.price}"></c:out></td>
        <c:choose>
            <c:when test="${elem.status=='AT_WORK'}">
                <td><fmt:message key="label.payed" bundle="${ rb }"/></td>
            </c:when>

            <c:when test="${elem.status=='NEW'}">
                <td><fmt:message key="label.new" bundle="${ rb }"/></td>
            </c:when>
            <c:when test="${elem.status=='COMPLETED'}">
                <td><fmt:message key="label.compleated" bundle="${ rb }"/></td>
            </c:when>
        </c:choose>
    </tr>
</c:forEach>