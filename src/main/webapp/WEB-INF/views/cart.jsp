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
    <link href="/pharmacy/css/bootstrap.min.css" rel="stylesheet">
    <link href="/pharmacy/css/starter-template.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        .navbar {
            z-index: inherit;
        }

        .ui-autocomplete {
            max-height: 200px;
            overflow-y: auto;
            /* prevent horizontal scrollbar */
            overflow-x: hidden;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

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
        function deleteDrugFromCart(id, button) {
            var cookieCartString = $.cookie('cart');
            var cookieCartJson = JSON.parse(decodeURIComponent(cookieCartString));
            for (var i = 0; i < cookieCartJson.length; ++i) {
                if (cookieCartJson[i].id === id) {
                    cookieCartJson.splice(i, 1);
                }
            }

            var newCookieString = JSON.stringify(cookieCartJson);
            $.cookie('cart', newCookieString);
            var tdTag = button.parentElement;
            var trTag = tdTag.parentElement;
            trTag.parentElement.removeChild(trTag);
        }
    </script>

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
                        <th scope="col">Item</th>
                        <th scope="col">Release form</th>
                        <th scope="col">Manufacturer</th>
                        <th scope="col" class="text-center">Quantity</th>
                        <th scope="col" class="text-right">Price</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody id="tbodyTagCart">
                    <c:forEach var="elem" items="${drugList}" varStatus="status">
                        <tr>

                            <td><c:out value="${elem.name}"></c:out></td>
                            <td><c:out value="${elem.releaseForm.description}"></c:out></td>
                            <td><c:out value="${elem.manufacturer.name}"></c:out></td>
                            <td><c:out value="6"></c:out></td>
                            <td><c:out value="${elem.price}"></c:out></td>
                            <td>
                                <button class="btn btn-primary" onclick="deleteDrugFromCart(${elem.id},this)">Delete
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
                            Total
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
                    <button class="btn btn-lg btn-block btn-success text-uppercase">Checkout</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>