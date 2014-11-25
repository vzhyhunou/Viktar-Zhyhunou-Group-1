<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="AddCustomerServlet.do">
    <label for="name" id="nameLabel">Company Name:</label>
    <input name="name" type="text" id="nameInput"/>
    <input type="submit" value="Add customer"/>
</form>
