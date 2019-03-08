<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/?command=toStartPage"><fmt:message
            key="link.home" bundle="${rb}"/></a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                    aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse flex-md-column" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li  class="nav-item">
                        <a class="btn btn-success btn-sm " href="${pageContext.request.contextPath}/?command=toCart">
                            <i class="fa fa-shopping-cart"></i> Cart
                            <span class="badge badge-light">3</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/?command=toSignUp"><fmt:message
                                key="link.sign-up" bundle="${rb}"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/?command=toLogin"><fmt:message
                                key="link.login" bundle="${rb}"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/?command=toAccount"><fmt:message
                                key="link.account" bundle="${rb}"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/?command=logout"><fmt:message
                                key="link.logout" bundle="${rb}"/></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            <fmt:message
                                    key="lable.language" bundle="${rb}"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/?command=doInitialRedirectCommand&lang=ru_RU">Ru</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/?command=doInitialRedirectCommand&lang=en_US">En</a>
                        </div>
                    </li>
                </ul>
                <ul class="navbar-nav m-auto col-9">
                        <input class="form-control  " id="search" type="text" placeholder="Search" aria-label="Search">
                </ul>
            </div>

</nav>

<script>
    $(function () {
        var body = 'command=' + encodeURIComponent("getDrugList");
        var req = getXMLHttpRequest();
        req.onreadystatechange = function () {
            if (req.readyState === 4) {
                if (req.status === 200) {
                    var jsonText = req.responseText;
                    var json = JSON.parse(jsonText);
                    mySearch(json);
                } else {
                    alert("can'not get drugs");
                }
            }
        };
        req.open('POST', '/pharmacy/ajax', true);
        req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        req.send(body);
    });
</script>
<script>
    function mySearch(drugList) {
        $("#search").autocomplete({
            source: drugList,
            select: function (event, ui) {
                alert("Selected: " + ui.item.value);
                return false;
            }

        });
    }
</script>