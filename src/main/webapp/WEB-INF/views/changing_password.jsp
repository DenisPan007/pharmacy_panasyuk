<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${message=='successChange'}">
    <div class="alert alert-success" role="alert">
        <fmt:message key="alert.successfully-changing" bundle="${ messageRb }"/>
    </div>
</c:if>
<form method="POST" action="${pageContext.request.contextPath}/">
    <input type="hidden" name="command" value="changeUserPassword">
    <fmt:message key="placeholder.old-password" bundle="${ rb }" var="passwordPlaceholder"/>
    <div class="form-group col-md-6">
        <input type="password" name="oldPassword" class="form-control"
               placeholder=${passwordPlaceholder} required/>
    </div>
    <c:if test="${error=='incorrectPassword'}">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="alert.incorrect-password" bundle="${ messageRb }"/>
        </div>
    </c:if>
    <fmt:message key="placeholder.new-password" bundle="${ rb }" var="passwordPlaceholder"/>
    <div class="form-group col-md-6">
        <input type="password" name="newPassword" class="form-control"
               placeholder=${passwordPlaceholder} required/>
    </div>
    <c:if test="${error=='incorrectAuthentication'}">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="alert.invalid-password" bundle="${ messageRb }"/>
        </div>
    </c:if>
    <button type="submit" class="btn btn-primary"><fmt:message key="button.change"
                                                               bundle="${ rb }"/></button>

</form>