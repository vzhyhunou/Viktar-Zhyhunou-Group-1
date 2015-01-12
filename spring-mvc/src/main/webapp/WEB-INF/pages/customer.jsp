<%--
  Created by IntelliJ IDEA.
  User: xdar
  Date: 8.1.15
  Time: 18.08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:choose>
    <c:when test="${wrongId != null}">
      <c:out value="${wrongId}"/>
    </c:when>
    <c:otherwise>

      <c:choose>
        <c:when test="${customer != null}">
           Customer name with id <c:out value="${customer.getId()}"/>
           is <c:out value="${customer.getName()}"/>
        </c:when>
        <c:otherwise>
           Customer with id <b><c:out value="${customerId}"/></b> is not found!
        </c:otherwise>
      </c:choose>

    </c:otherwise>
</c:choose>
