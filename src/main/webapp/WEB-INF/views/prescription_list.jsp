<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<div class="modal fade " id="prescriptionDetails" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title"><fmt:message key="prescription.prescription-details" bundle="${ rb }"/></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <label for="inputDescription"> <fmt:message key="prescription.description" bundle="${ rb }"/></label>
                <textarea id="inputDescription" cols="40" rows="5"></textarea>
                <label for="inputDate"> <fmt:message key="prescription.validity-date" bundle="${ rb }"/></label>
                <input type="date" id="inputDate">

            </div>
            <button class="btn-dark btn" id="buttonGive"><fmt:message key="button.give" bundle="${ rb }"/></button>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal"><fmt:message key="button.close" bundle="${ rb }"/></button>
            </div>

        </div>
    </div>
</div>
<table id="prescriptionList" class="display " style="width:100%">
    <thead class="thead-dark">
    <tr>
        <th></th>
        <th><fmt:message key="label.prescription-number" bundle="${ rb }"/></th>
        <th><fmt:message key="label.user" bundle="${ rb }"/></th>
        <th><fmt:message key="label.drug" bundle="${ rb }"/></th>
        <th><fmt:message key="label.status" bundle="${ rb }"/></th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${prescriptionList}" varStatus="status">
        <tr>
            <c:choose>
                <c:when test="${elem.issueDate == null}">
            <td><button class="btn-dark btn" onclick="showPrescriptionDetails(${elem.id},this)"> <fmt:message key="button.details" bundle="${ rb }"/></button></td>
                </c:when>
                <c:when test="${elem.issueDate != null}">
                    <td></td>
                </c:when>
            </c:choose>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.userId}"></c:out></td>
            <td><c:out value="${elem.drugId}"></c:out></td>
            <c:choose>
                <c:when test="${elem.issueDate == null}">
                    <td> <fmt:message key="prescription.requested" bundle="${ rb }"/></td>
                </c:when>
                <c:when test="${elem.issueDate != null}">
                    <td> <fmt:message key="prescription.given" bundle="${ rb }"/></td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>