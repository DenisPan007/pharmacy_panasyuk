<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
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
        function deleteDrug(id, button) {
            var body = 'command=' + encodeURIComponent("deleteDrug") + '&id=' + encodeURIComponent(id);
            var req = getXMLHttpRequest();
            req.onreadystatechange = function () {
                if (req.readyState === 4) {
                    if (req.status === 200) {
                        alert(req.responseText);
                        var tdTag = button.parentElement;
                        var trTag = tdTag.parentElement;
                        trTag.parentElement.removeChild(trTag);
                    }
                    else
                        {
                            alert("can'not delete user");
                        }
                    }
                };
            req.open('POST', '/pharmacy/ajax', true);
            req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            req.send(body);
        }
    </script>

    <script>
        function deleteUser(id, button) {
            var body = 'command=' + encodeURIComponent("deleteUser") + '&id=' + encodeURIComponent(id);
            var req = getXMLHttpRequest();
            req.onreadystatechange = function () {
                if (req.readyState === 4) {
                    if (req.status === 200) {
                        alert(req.responseText);
                        var tdTag = button.parentElement;
                        var trTag = tdTag.parentElement;
                      trTag.parentElement.removeChild(trTag);
                    }
                    else {
                        alert("can'not delete user");
                    }
                }
            };
            req.open('POST', '/pharmacy/ajax', true);
            req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            req.send(body);
        }
    </script>
    <title>Bootstrap Admin Theme v3</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class=container>
    <div class="row">
        <div class="col-3">
            <div class="nav flex-column nav-tabs" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-home" role="tab"
                   aria-controls="v-pills-home" aria-selected="true">Home</a>
                <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile" role="tab"
                   aria-controls="v-pills-profile" aria-selected="false">Profile</a>
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
                    <jsp:include page="user_list.jsp"></jsp:include>
                </div>
                <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                    <jsp:include page="drug_list.jsp"></jsp:include>
                </div>
                <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">
                    Yes
                </div>
                <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">
                    Damn
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" charset="utf8"
        src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#example').DataTable({
            "order": [[3, "desc"]]
        });
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#example1').DataTable({
            "order": [[3, "desc"]]
        });
    });
</script>
</body>
</html>