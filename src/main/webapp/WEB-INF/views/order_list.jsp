<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
<table id="orderList" class="display " style="width:100%">
    <thead class="thead-dark">
    <tr>
        <th></th>
        <th><fmt:message key="label.order-number" bundle="${ rb }"/></th>
        <th><fmt:message key="label.price" bundle="${ rb }"/></th>
        <th><fmt:message key="label.status" bundle="${ rb }"/></th>
    </tr>
    </thead>
    <tbody id="orderBody">
    <c:import url="order_element.jsp"/>
    </tbody>

</table>
</body>