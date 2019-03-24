<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
     aria-labelledby="v-pills-home-tab">
    <div class="row">
        <div class="col-md-6">
            <label><fmt:message key="label.login" bundle="${ rb }"/></label>
        </div>
        <div class="col-md-6">
            <c:out value="${user.login}"></c:out>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <label><fmt:message key="label.name" bundle="${ rb }"/></label>
        </div>
        <div class="col-md-6">
            <c:out value="${user.firstName}"></c:out>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <label><fmt:message key="label.lastname" bundle="${ rb }"/></label>
        </div>
        <div class="col-md-6">
            <c:out value="${user.lastName}"></c:out>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <label><fmt:message key="label.email" bundle="${ rb }"/></label>
        </div>
        <div class="col-md-6">
            <p><c:out value="${user.email}"></c:out> </p>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <label><fmt:message key="label.address" bundle="${ rb }"/></label>
        </div>
        <div class="col-md-6">
            <%--  <p><c:out value="${user.address}"></c:out></p> --%>
        </div>
    </div>
</div>