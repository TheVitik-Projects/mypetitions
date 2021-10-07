<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>MyPetition | Active</title>
    <script src="https://kit.fontawesome.com/b2700bfb4b.js" crossorigin="anonymous"></script>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <style><%@include file="/css/style.css"%></style>
    <style><%@include file="/css/bootstrap.min.css"%></style>

</head>
<body>
<div class="content">
    <header>
        <div class="container">
            <div class="row">
                <div class="col-5">
                    <a class="flex clear" href="active">
                        <div class="logo"></div>
                        <div class="title">
                            <h3>MY PETITION</h3>
                            <h5>Change your future!</h5>
                        </div>
                    </a>
                </div>
                <div class="col-3">
                    <c:if test="${!sessionScope.id}">
                        <a class="create-button" href="main?page=new">New petition</a>
                    </c:if>
                </div>
                <div class="col-4">
                    <div class="d-flex justify-content-center">
                        <div class="search">
                            <form id="search" action="main" method="GET">
                                <input type="hidden" name="page" value="search">
                                <input type="text" class="search-input" placeholder="Search..." name="find">

                                <a href="javascript:;" onclick="document.getElementById('search').submit();" class="search-icon"><i class="fas fa-search"></i></a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <nav class="nav-menu">
        <div class="links">
            <a href="main?page=active" class="nav-link">Active petitions</a>
            <a href="main?page=closed" class="nav-link">Closed petitions</a>
            <c:if test="${sessionScope.id!=null}">
                <a href="main?page=my" class="nav-link">My petitions</a>
            </c:if>
        </div>
        <div class="user">
            <c:choose>
                <c:when test="${sessionScope.id==null}">
                    <a href="main?page=login" class="nav-link">Login</a>
                    <a href="main?page=register" class="nav-link">Register</a>
                </c:when>
                <c:otherwise>
                    <a href="main?page=exit" class="nav-link">Exit</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
        <main>
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h2 class="page-title">NEW PETITION</h2>
                        <article class="new">
                            <form action="create" method="POST">
                                <div class="row">

                                    <div class="col-8">
                                        <textarea class="title-text" maxlength="256" id="text" onchange="count()" name="text" required></textarea>
                                        <span id="symbols" class="count-text">256/256 characters left</span>
                                    </div>
                                    <div class="col-4">
                                        <div class="g-recaptcha" data-sitekey="6LcKCOoaAAAAADNMgERSxmovxGbWPRGC4Bqw7yoR"></div>
                                        <input type="submit" class="create-button" style="outline:none;" value="Create">
                                    </div>

                                </div>
                            </form>
                        </article>
                    </div>

                </div>
            </div>
        </main>
    <footer>
        <div class="footer-info">
            <p class="text-center">&copy; 2021 MyPetition</p>
            <p class="text-center">Created by <a href="https://github.com/TheVitik">Viktor Kobylynskyi</a></p>
        </div>
    </footer>
</div>
</body>
<script>
    document.getElementById('text').onkeyup = function () {
        document.getElementById('symbols').innerHTML = (256 - this.value.length)+"/256 characters left";
    };
</script>
</html>