<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>MyPetition | Register</title>
    <style><%@include file="/css/form.css"%></style>
</head>
<body>
<div id="login-form">
    <h1>Register</h1>
    <fieldset>
        <form action="register" method="post">
            <input type="text" name="name" placeholder="Name" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" value="Register">
            <footer class="clearfix">
                <p><span class="info">L</span><a href="main?page=login">Already registered?</a></p>
            </footer>
            <p><c:out value="${requestScope.message}"/></p>
        </form>
    </fieldset>

</div>
</body>
</html>