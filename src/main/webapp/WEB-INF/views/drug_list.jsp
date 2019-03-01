<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body >
<table id="example1" class="display" style="width:100%">
    <thead>
    <tr>
        <th></th>
        <th>Id</th>
        <th>Name</th>
        <th>Prescription</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody id="body">
    <c:forEach var="elem" items="${drugList}" varStatus="status">
        <tr>
            <td>
                <button class="btn btn-primary" onclick="deleteDrug(${elem.id},this)">Delete</button>
            </td>
            <td><c:out value="${elem.id}"></c:out></td>
            <td><c:out value="${elem.name}"></c:out></td>
            <td><c:out value="${elem.isPrescriptionRequired}"></c:out></td>
            <td><c:out value="${elem.price}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr>
        <th></th>
        <th>Id</th>
        <th>Name</th>
        <th>Prescription</th>
        <th>Price</th>
    </tr>
    </tfoot>
</table>
</body>
</html>