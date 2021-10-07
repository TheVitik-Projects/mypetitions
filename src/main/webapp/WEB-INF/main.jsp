<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>MyPetition | Active</title>
    <script src="https://kit.fontawesome.com/b2700bfb4b.js" crossorigin="anonymous"></script>
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
                    <c:if test="${sessionScope.id!=null}">
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
                <c:choose>
                    <c:when test="${requestScope.page == 'petition'}">
                        <h2 class="page-title"><c:out value="${requestScope.page}"/></h2>
                    </c:when>
                    <c:otherwise>
                        <h2 class="page-title"><c:out value="${requestScope.page}"/> PETITIONS</h2>
                    </c:otherwise>
                </c:choose>

                        <c:if test="${empty requestScope.petitions}">
                            <h2 align="center">The petition does not exist.</h2>
                        </c:if>
                        <c:forEach items="${requestScope.petitions}" var="petition" varStatus="loop">
                        <article class="petition">
                            <div class="row">
                                <div class="col-8">
                                    <p class="number">#<c:out value="${petition.id}"/></p>
                                    <p class="petition-title"><c:out value="${petition.title}"/></p>
                                    <div class="petition-date">
                                        <i class="fas fa-clock text-primary"></i>
                                        <span class="creation-date"><c:out value="${petition.date}"/></span>
                                    </div>
                                </div>
                                <div class="col-4">
                                    <div class="voting">
                                        <h1><c:out value="${petition.votes}"/></h1><p>votes</p>
                                    </div>
                                    <div class="vote-progress">
                                        <div class="progress-bar" role="progressbar" style="width: <c:out value="${petition.votes}"/>%" aria-valuenow="<c:out value="${petition.votes}"/>" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                    <div class="vote-status">
                                        <c:choose>
                                            <c:when test="${petition.active==1}">
                                                <i class="fas fa-check-circle <c:out value="${'text-success'}"/>"></i>
                                                <span><c:out value="${'Active'}"/></span>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fas fa-check-circle <c:out value="${'text-danger'}"/>"></i>
                                                <span><c:out value="${'Closed'}"/></span>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <c:choose>
                                        <c:when test="${requestScope.page == 'my'}">
                                                <input type="text" class="url-text" id="invite" readonly value="${requestScope.path}?page=petition&id=${petition.id}">
                                            <a class="create-button " href="delete?id=${petition.id}">Delete</a>
                                        </c:when>
                                        <c:when test="${requestScope.page == 'closed'}">
                                        </c:when>
                                        <c:otherwise>
                                            <a class="create-button " href="vote?id=${petition.id}">Vote</a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </article>
                        </c:forEach>

                        <!--<div class="page-counter">
                            <a class="current-disabled">&lt;</a>
                            <a class="current-count" href="#page1">1</a>
                            <a href="#page2">2</a>
                            <a href="#page3">3</a>
                            <a class="current-disabled">...</a>
                            <a href="#page4">4</a>
                            <a href="#page5">5</a>
                            <a href="#page6">6</a>
                            <a class="current-disabled">&gt;</a>
                        </div>-->
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
</html>