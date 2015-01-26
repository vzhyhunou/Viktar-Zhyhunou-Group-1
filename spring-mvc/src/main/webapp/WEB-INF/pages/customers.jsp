<%--
  Created by IntelliJ IDEA.
  User: xdar
  Date: 8.1.15
  Time: 18.05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${customers != null}">
    <c:forEach var="customer" items="${customers}">
        <c:out value="${customer.name}"/></br>
    </c:forEach>
</c:if>
