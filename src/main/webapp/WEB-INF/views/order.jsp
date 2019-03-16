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
    
	<link href="/pharmacy/css/bootstrap.min.css" rel="stylesheet">
    <link href="/pharmacy/css/starter-template.css" rel="stylesheet">
	<!--for checkout form -->
	<link href="/pharmacy/css/checkout_form.css" rel="stylesheet">
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
        .modal-dialog {
            position: relative;
            display: table; /* This is important */
            overflow-y: auto;
            overflow-x: auto;
            width: auto;
            min-width: 300px;
            max-width : 80% ;

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
        function pay() {
            alert("stop");

        }
    </script>

</head>
<body>

<c:import url="header.jsp"/>

<main role="main" class="container">
    <div class="row">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Item</th>
                <th scope="col">Release form</th>
                <th scope="col">Manufacturer</th>
                <th scope="col" class="text-center">Quantity</th>
                <th scope="col" class="text-right">Price</th>
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
            total : ${order.price}
            </tbody>
        </table>
    </div>
    <div class="row" >
        <div class="col-6">
            <form class="row form-check" action="${pageContext.request.contextPath}/">
                <input type="hidden" name="command" value="pay">
                <div class="form-group col-sm-7">
                    <label for="card-holder">Card Holder</label>
                    <input id="card-holder" type="text" class="form-control" placeholder="Card Holder" aria-label="Card Holder" aria-describedby="basic-addon1">
                </div>
                <div class="form-group col-sm-5">
                    <label >Expiration Date</label>
                    <div class="input-group expiration-date">
                        <input type="text" class="form-control" placeholder="MM" aria-label="MM" aria-describedby="basic-addon1">
                        <span class="date-separator">/</span>
                        <input type="text" class="form-control" placeholder="YY" aria-label="YY" aria-describedby="basic-addon1">
                    </div>
                </div>
                <div class="form-group col-sm-8">
                    <label for="card-number">Card Number</label>
                    <input id="card-number" type="text" class="form-control" placeholder="Card Number" aria-label="Card Holder" aria-describedby="basic-addon1">
                </div>
                <div class="form-group col-sm-4">
                    <label for="cvc">CVC</label>
                    <input id="cvc" type="text" class="form-control" placeholder="CVC" aria-label="Card Holder" aria-describedby="basic-addon1">
                </div>

                    <button type="submit" class="btn btn-primary btn-block" onclick="pay()">Proceed</button>
                </form>
            </div>
        </div>
    </div>


</main>
</body>
</html>
