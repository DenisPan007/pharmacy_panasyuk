<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
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
    function givePrescription(id,button) {
        var description = document.getElementById('inputDescription').value;
        var date = document.getElementById('inputDate').value;
        alert(date);
        alert(description);
        var body = 'command=' + encodeURIComponent("givePrescription") + '&id=' + encodeURIComponent(id)
        + '&description=' + encodeURIComponent(description) + '&date=' + encodeURIComponent(date);
        var req = getXMLHttpRequest();
        req.onreadystatechange = function () {
            if (req.readyState === 4) {
                if (req.status === 200) {
                    alert(req.responseText);

                    var parent = button.parentElement;
                    parent.removeChild(button);
                } else {
                    alert("can't give prescription");
                }
            }
        };
        req.open('POST', '/pharmacy/ajax', true);
        req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        req.send(body);
    }
</script>
<script>
    function showPrescriptionDetails(id,button) {
        document.getElementById('buttonGive').onclick=function(){givePrescription(id,button)};
        $('#prescriptionDetails').modal();
    }
</script>
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