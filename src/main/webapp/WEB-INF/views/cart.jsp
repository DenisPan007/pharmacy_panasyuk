<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctl" uri="/WEB-INF/tld/custom.tld" %>
<html>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="pagecontent" var="rb" scope="request"/>
<ctl:cookie name="cart" var="cart"/>
<ctl:json jsonString="${cart}" var="drugList"/>
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

<div class="container mb-4 starter-template">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="label.item" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.release-form" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.manufacturer" bundle="${ rb }"/></th>
                        <th scope="col"><fmt:message key="label.prescription" bundle="${ rb }"/></th>
                        <th scope="col" class="text-center"><fmt:message key="label.quantity" bundle="${ rb }"/></th>
                        <th scope="col" class="text-right"><fmt:message key="label.price" bundle="${ rb }"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody id="tbodyTagCart">
                    <c:forEach var="elem" items="${drugList}" varStatus="status">
                        <tr>

                            <td><c:out value="${elem.drug.name}"></c:out></td>
                            <td><c:out value="${elem.drug.releaseForm.description}"></c:out></td>
                            <td><c:out value="${elem.drug.manufacturer.name}"></c:out></td>
                            <td>
                                <c:choose>
                                <c:when test="${!elem.drug.isPrescriptionRequired}">
                                    <fmt:message key="info.non-prescription" bundle="${ rb }" var="nonPrescription"/>
                                    <c:out value="${nonPrescription}"></c:out>
                                </c:when>
                                <c:when test="${elem.drug.isPrescriptionRequired}">
                                <button class="btn btn-primary" onclick="getPrescription(${elem.drug.id},this)"><fmt:message key="button.quire" bundle="${ rb }"/>
                                    </c:when>
                                    </c:choose>

                            </td>
                            <td><c:out value="${elem.amount}"></c:out></td>
                            <td><c:out value="${elem.drug.price}"></c:out></td>
                            <td>
                                <button class="btn btn-primary" onclick="deleteDrugFromCart(${elem.drug.id},this)">
                                    <fmt:message key="button.delete" bundle="${ rb }"/>
                                </button>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="row">
                <div class="col-4 ml-auto">
                    <div class="row">
                        <div class="col-6">
                            <fmt:message key="label.total" bundle="${ rb }" var="nonPrescription"/>
                        </div>
                        <div class="col-6">
                            346,90 €
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12 col-md-6 text-right">
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                    <button class="btn btn-lg btn-block btn-success text-uppercase" onclick="checkout()">Checkout
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        itemAmountUpdate();
    });
</script>
</body>
</html>