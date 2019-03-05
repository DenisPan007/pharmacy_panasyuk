<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/?command=toStartPage"><fmt:message key="link.home" bundle="${rb}"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/?command=toSignUp"><fmt:message key="link.sign-up" bundle="${rb}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/?command=toLogin"><fmt:message key="link.login" bundle="${rb}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/?command=toAccount"><fmt:message key="link.account" bundle="${rb}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/?command=logout"><fmt:message key="link.logout" bundle="${rb}"/></a>
                </li>
                <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/?lang=ru_RU" >Ru</a>
                </li>
                <li class="nav-item">|</li>
                <li class="nav-item">
                    <a class="nav-link"  href="${pageContext.request.contextPath}/?lang=en_US" >En</a>
                </li>

            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" id="search" type="text" placeholder="Search" aria-label="Search">
                <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
            </form>

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