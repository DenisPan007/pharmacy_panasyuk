<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
      integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/?command=toStartPage"><fmt:message
            key="link.home" bundle="${rb}"/></a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarsExampleDefault"
            aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse flex-md-column" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="btn btn-success btn-sm " href="${pageContext.request.contextPath}/?command=toCart">
                    <i class="fa fa-shopping-cart"></i> <fmt:message
                        key="link.cart" bundle="${rb}"/>
                    <span class="badge badge-light" id="itemAmount"></span>
                </a>
            </li>
            <c:if test="${role == 'GUEST'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/?command=toSignUp"><fmt:message
                            key="link.sign-up" bundle="${rb}"/></a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/?command=toAccount"><fmt:message
                        key="link.account" bundle="${rb}"/></a>
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
            <c:if test="${role != 'GUEST'}">
                <li class="nav-item">
                    <a class="nav-link text-primary"
                       href="${pageContext.request.contextPath}/?command=logout"><fmt:message
                            key="link.logout" bundle="${rb}"/></a>
                </li>
            </c:if>
        </ul>
        <ul class="navbar-nav mr-auto col-9">
            <input class="form-control  " id="search" onclick="getDrugsFromBase()" type="text" placeholder=
            <fmt:message
                    key="placeholder.search" bundle="${rb}"/> aria-label="Search">
        </ul>
    </div>

</nav>
<div class="modal fade " id="myModal" role="dialog">
    <div class="modal-dialog modal-result-in-table">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="label.item" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.release-form" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.manufacturer" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.prescription" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.available-amount" bundle="${ rb }"/></th>
                        <th scope="col" class="text-center"><fmt:message key="label.quantity" bundle="${ rb }"/></th>
                        <th scope="col" class="text-right"><fmt:message key="label.price" bundle="${ rb }"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody id="tbodyTagModal">

                    </tbody>
                </table>

            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">
                    <th><fmt:message key="button.close" bundle="${ rb }"/></th>
                </button>
            </div>

        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        itemAmountUpdate();
    });
</script>
