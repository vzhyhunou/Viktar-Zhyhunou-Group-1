<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Customers</title>
</head>
<body>
   <form action="InputCustomerServlet.do">
       <input type="submit" value="Add new customer"/>
   </form>
   <c:forEach items="${customers}" var="customer">
       <c:out value="${customer.name}"/><br/>
   </c:forEach>
  
</body>
</html>
