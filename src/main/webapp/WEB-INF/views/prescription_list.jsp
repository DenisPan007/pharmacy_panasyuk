<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<div class="modal fade " id="prescriptionDetails" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Modal Heading</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <label for="inputDescription"> Description</label>
                <textarea id="inputDescription" cols="40" rows="5"></textarea>
                <label for="inputDate"> Validity date</label>
                <input type="date" id="inputDate">

            </div>
            <button class="btn-dark btn" id="buttonGive">Give</button>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
<table id="prescriptionList" class="display " style="width:100%">
    <thead class="thead-dark">
    <tr>
        <th></th>
        <th>Number</th>
        <th>User</th>
        <th>Drug</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${prescriptionList}" varStatus="status">
        <tr>
            <c:choose>
                <c:when test="${elem.issueDate == null}">
            <td><button class="btn-dark btn" onclick="showPrescriptionDetails(${elem.id},this)">Details</button></td>
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
                    <td>Requested</td>
                </c:when>
                <c:when test="${elem.issueDate != null}">
                    <td>Given</td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>