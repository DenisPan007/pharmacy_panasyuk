<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<head>
    <title><fmt:message key="home.title" bundle="${ rb }"/></title>
    <!-- css for cart img -->
    <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
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

<main role="main" class="container">
    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col"><fmt:message key="label.item" bundle="${ rb }"/></th>
                <th scope="col"><fmt:message key="label.release-form" bundle="${ rb }"/></th>
                <th scope="col"><fmt:message key="label.manufacturer" bundle="${ rb }"/></th>
                <th scope="col" class="text-center"><fmt:message key="label.quantity" bundle="${ rb }"/></th>
                <th scope="col" class="text-right"><fmt:message key="label.price" bundle="${ rb }"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="elem" items="${order.itemList}" varStatus="status">
                <tr>

                    <td><c:out value="${elem.drug.name}"></c:out></td>
                    <td><c:out value="${elem.drug.releaseForm.description}"></c:out></td>
                    <td><c:out value="${elem.drug.manufacturer.name}"></c:out></td>
                    <td><c:out value="${elem.amount}"></c:out></td>
                    <td><c:out value="${elem.drug.price}"></c:out></td>

                </tr>
            </c:forEach>
            <fmt:message key="label.total" bundle="${ rb }"/>  ${order.price}
            </tbody>
        </table>
    </div>
    <div class="row" >
        <div class="col-6">
            <form class="row form-check" action="${pageContext.request.contextPath}/">
                <input type="hidden" name="command" value="pay">
                <div class="form-group col-sm-7">
                    <label for="card-holder"><fmt:message key="pay.label.cart-holder" bundle="${ rb }"/></label>
                    <input id="card-holder" type="text" class="form-control" placeholder="Card Holder" aria-label="Card Holder" aria-describedby="basic-addon1">
                </div>
                <div class="form-group col-sm-5">
                    <label ><fmt:message key="pay.label.expiration-date" bundle="${ rb }"/></label>
                    <div class="input-group expiration-date">
                        <input type="text" class="form-control" placeholder="MM" aria-label="MM" aria-describedby="basic-addon1">
                        <span class="date-separator">/</span>
                        <input type="text" class="form-control" placeholder="YY" aria-label="YY" aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label for="card-number"><fmt:message key="pay.label.cart-number" bundle="${ rb }"/></label>
                    <input id="card-number" type="text" class="form-control" placeholder="Card Number" aria-label="Card Holder" aria-describedby="basic-addon1">
                </div>
                <div class="form-group col-sm-4">
                    <label for="cvc">CVC</label>
                    <input id="cvc" type="text" class="form-control" placeholder="CVC" aria-label="Card Holder" aria-describedby="basic-addon1">
                </div>

                    <button type="submit" class="btn btn-primary btn-block">Proceed</button>
                </form>
            </div>
        </div>
    </div>


</main>
</body>
</html>
