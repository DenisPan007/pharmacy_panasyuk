<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctl" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<ctl:cookie name="cart" var="cart"/>
<ctl:json jsonString="${cart}" var="itemList"/>
<ctl:total itemList="${itemList}" var="total"/>
<c:forEach var="elem" items="${itemList}" varStatus="status">
    <tr>

        <td><c:out value="${elem.drug.name}"></c:out></td>
        <td><c:out value="${elem.drug.releaseForm.description}"></c:out></td>
        <td><c:out value="${elem.drug.manufacturer.name}"></c:out></td>
        <td>
            <c:choose>
                <c:when test="${!elem.drug.isPrescriptionRequired}">
                    <fmt:message key="info.non-prescription" bundle="${ rb }" var="nonPrescription"/>
                    <c:out value="${nonPrescription}"></c:out>
                </c:when>
                <c:when test="${elem.drug.isPrescriptionRequired}">
                    <button class="btn btn-primary" onclick="getPrescription(${elem.drug.id},this)"><fmt:message
                            key="button.quire" bundle="${ rb }"/>
                    </button>
                </c:when>
            </c:choose>

        </td>
        <td><c:out value="${elem.amount}"></c:out></td>
        <td><c:out value="${elem.drug.price}"></c:out></td>
        <td>
            <button class="btn btn-primary" onclick="deleteDrugFromCart(${elem.drug.id},this)">
                <fmt:message key="button.delete" bundle="${ rb }"/>
            </button>
        </td>

    </tr>
</c:forEach>