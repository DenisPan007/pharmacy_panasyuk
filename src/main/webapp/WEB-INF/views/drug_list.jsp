<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<body>
<table id="example1" class="display" style="width:100%">
    <thead>
    <tr>
        <th></th>
        <th></th>
        <th>Id</th>
        <th><fmt:message key="label.name-drug" bundle="${ rb }"/></th>
        <th><fmt:message key="label.prescription" bundle="${ rb }"/></th>
        <th><fmt:message key="label.available-amount" bundle="${ rb }"/></th>
        <th><fmt:message key="label.price" bundle="${ rb }"/></th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${drugList}" varStatus="status">
        <tr>
            <td>
                <button class="btn btn-dark" onclick="changeDrugMenu(${elem.id})"><fmt:message key="button.edit"
                                                                                                bundle="${ rb }"/></button>
            </td>
            <td>
                <button class="btn btn-dark" onclick="deleteDrug(${elem.id},this)"><fmt:message key="button.delete"
                                                                                                bundle="${ rb }"/></button>
            </td>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.name}"></c:out></td>

            <td>
                <c:choose>
                    <c:when test="${!elem.isPrescriptionRequired}">
                        <fmt:message key="info.non-prescription" bundle="${ rb }"/>
                    </c:when>
                    <c:when test="${elem.isPrescriptionRequired}">
                        <fmt:message key="info.required" bundle="${ rb }"/>
                    </c:when>
                </c:choose>

            </td>

            <td><c:out value="${elem.availableAmount}"></c:out></td>
            <td><c:out value="${elem.price}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>