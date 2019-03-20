<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<div class="modal fade " id="userDetailsModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Modal Heading</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <label>Login</label>
                    </div>
                    <div class="col-md-6" id="inputLogin">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label>Name</label>
                    </div>
                    <div class="col-md-6" id="inputUserName">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label>lastname</label>
                    </div>
                    <div class="col-md-6" id="inputLastName">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label>Email</label>
                    </div>
                    <div id="inputEmail" class="col-md-6">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label>Role</label>
                    </div>
                    <div id="inputRole" class="col-md-6">
                    </div>
                </div>
            </div>
            <button type="button" class="btn btn-dark" id="deleteButton">Delete</button>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
<table id="example" class="display" style="width:100%">
    <thead>
    <tr>
        <th></th>
        <th>Id</th>
        <th>Login</th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${userList}" varStatus="status">
        <tr>
            <td>
                <button class=" btn-dark" onclick="showUserDetails(${elem.id},this)">Details</button>
            </td>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.login}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <th></th>
        <th>Id</th>
        <th>Login</th>
    </tr>
    </tfoot>
</table>
</body>