<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<head>

    <title>Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
    <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/project.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class="modal fade " id="addDrugMenu" role="dialog">
    <div class="modal-dialog modal-result-in-table">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Modal Heading</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <table class="table ">
                    <thead>
                    <tr>
                        <th> Name</th>
                        <th>Release form</th>
                        <th>Manufacturer</th>
                        <th>Prescription</th>
                        <th class="text-right">Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input id="inputName"></td>
                        <td>
                            <select id="releaseForm">
                            </select>
                        </td>
                        <td>
                            <select id="manufacturer">
                            </select>
                        </td>
                        <td>
                            <select id="inputPrescription">
                                <option>true</option>
                                <option>false</option>
                            </select>
                        </td>
                        <td><input id="inputPrice" type="number" min="0"></td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <button type="button" class="btn btn-dark" onclick="addDrugToBase()" >Add</button>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
<div class=container>
    <div class="row">
        <div class="col-3">
            <div class="nav flex-column nav-tabs" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab"
                   aria-controls="v-pills-home" aria-selected="true">Users</a>
                <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab"
                   aria-controls="v-pills-profile" aria-selected="false">Drugs</a>
                <a class="nav-link" id="v-pills-messages-tab" data-toggle="pill" href="#v-pills-messages" role="tab"
                   aria-controls="v-pills-messages" aria-selected="false">Messages</a>
                <a class="nav-link" id="v-pills-settings-tab" data-toggle="pill" href="#v-pills-settings" role="tab"
                   aria-controls="v-pills-settings" aria-selected="false">Settings</a>
            </div>
        </div>
        <div class="col-9">
            <div class="tab-content" id="v-pills-tabContent">
                <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
                     aria-labelledby="v-pills-home-tab">
                    <c:import url="user_list.jsp"/>
                </div>
                <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                    <div class="row">
                        <button class="btn-dark" onclick="addDrugMenu()">add Drug</button>
                    </div>
                    <c:import url="drug_list.jsp"/>
                </div>
                <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">
                    Yes
                </div>
                <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">
                    another message
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#example').DataTable({
            "order": [[2, "desc"]]
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#example1').DataTable({
            language: {
                search: "Локализации быть!"
            },
            "order": [[2, "desc"]]
        });
    });
</script>
</body>
</html>