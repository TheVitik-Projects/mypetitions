<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>MyPetition | Login</title>
    <style><%@include file="/css/form.css"%></style>
</head>
<body>
<div id="login-form">
    <h1>Login</h1>
    <fieldset>
        <form action="login" method="post">
            <input type="text" name="name" placeholder="Name" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" value="Login">
            <footer class="clearfix">
                <p><span class="info">R</span><a href="main?page=register">Don't have an account?</a></p>
            </footer>
            <p><c:out value="${requestScope.message}"/></p>
        </form>
    </fieldset>

</div>
</body>
</html>