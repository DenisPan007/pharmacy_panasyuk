<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctl" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<ctl:toJson itemList="${itemList}" var="itemListJson"/>
<c:forEach var="elem" items="${itemList}" varStatus="status">
    <tr>

        <td><c:out value="${elem.drug.name}"></c:out></td>
        <td><c:out value="${elem.drug.releaseForm.description}"></c:out></td>
        <td><c:out value="${elem.drug.manufacturer.name}"></c:out></td>
        <td>
            <c:choose>
            <c:when test="${!elem.drug.isPrescriptionRequired}">
                <fmt:message key="info.non-prescription" bundle="${ rb }" var="nonPrescription"/>
                <c:out value="${nonPrescription}"/>
            </c:when>
            <c:when test="${elem.drug.isPrescriptionRequired}">
                <fmt:message key="info.required" bundle="${ rb }"/>
                </c:when>
                </c:choose>

        </td>
        <td>
            <div class="col-12 m-0">
                <input id="${elem.drug.id}" type="number" class="count form-control"  value="1" max="100" min="1" >
            </div>
        </td>
        <td><c:out value="${elem.drug.price}"></c:out></td>
        <td>
            <button class="btn btn-primary " onclick="buffer(this)">
                <fmt:message key="button.add" bundle="${ rb }"/>
            </button>
        </td>
        <script>
            function buffer(button) {
                addItemToCart(button,'${itemListJson}',${status.count-1});
            }
        </script>

    </tr>
</c:forEach>
