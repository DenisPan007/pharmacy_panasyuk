<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
    .modal-dialog {
        overflow-y: auto;
        width: auto;
        min-width: 200px;
        max-width : 60% ;
    }
</style>
<body>
<div class="modal fade " id="userDetailsModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title"><fmt:message key="user-details.title" bundle="${ rb }"/></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                            <label><fmt:message key="label.login" bundle="${ rb }"/></label>
                        </div>
                        <div class="col-md-9" id="inputLogin">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label><fmt:message key="label.name" bundle="${ rb }"/></label>
                        </div>
                        <div class="col-md-9" id="inputUserName">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label><fmt:message key="label.lastname" bundle="${ rb }"/></label>
                        </div>
                        <div class="col-md-9" id="inputLastName">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label><fmt:message key="label.address" bundle="${ rb }"/></label>
                        </div>
                        <div id="inputAddress" class="col-md-9">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label><fmt:message key="label.email" bundle="${ rb }"/></label>
                        </div>
                        <div id="inputEmail" class="col-md-9">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3">
                            <label><fmt:message key="label.role" bundle="${ rb }"/></label>
                        </div>
                        <div id="inputRole" class="col-md-4">
                        </div>
                        <div  class="col-md-5">
                            <select class="form-control" id="changeRole">
                                <option>CLIENT</option>
                                <option>DOCTOR</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="changeUser()"><fmt:message key="button.apply" bundle="${ rb }"/></button>
                <button type="button" class="btn btn-dark" id="deleteButton"><fmt:message key="button.delete" bundle="${ rb }"/></button>
                <button type="button" class="btn btn-danger" data-dismiss="modal"><fmt:message key="button.close" bundle="${ rb }"/></button>
            </div>

        </div>
    </div>
</div>
<table id="example" class="display" style="width:100%">
    <thead>
    <tr>
        <th></th>
        <th>Id</th>
        <th><fmt:message key="label.login" bundle="${ rb }"/></th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${userList}" varStatus="status">
        <tr>
            <td>
                <button class=" btn-dark" onclick="showUserDetails(${elem.id},this)"><fmt:message key="button.details" bundle="${ rb }"/></button>
            </td>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.login}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>