<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<div id="content" style="width: 100%; height: 500px; margin:0 auto;">
  <c:if test="${message != null}">
    <c:out value="${message}"/>
  </c:if><br>
  <a href="customers">List of customers</a><br>
</div>

