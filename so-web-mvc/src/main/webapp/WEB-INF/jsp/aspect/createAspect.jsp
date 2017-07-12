<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <!--<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />-->

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!--
    <spring:url value="/css/main.css" var="springCss" />
    <link href="${springCss}" rel="stylesheet" />
     -->
    <%--<c:url value="/css/main.css" var="jstlCss" />--%>
    <%--<link href="${jstlCss}" rel="stylesheet" />--%>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">SODA</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <%--<li><a href="#about">About</a></li>--%>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <div class="starter-template">
    <h1>Create Aspect</h1>

        <form:form class="form-horizontal" method="post"
                   modelAttribute="aspectForm" action="/so/ui/aspect/create">

            <form:hidden path="id" />

            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <form:input path="name" type="text" class="form-control"
                                    id="name" placeholder="Name" />
                        <form:errors path="name" class="control-label" />
                    </div>
                </div>
            </spring:bind>

            <spring:bind path="uri">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <label class="col-sm-2 control-label">uri</label>
                    <div class="col-sm-10">
                        <form:input path="uri" class="form-control"
                                    id="uri" placeholder="Email" />
                        <form:errors path="uri" class="control-label" />
                    </div>
                </div>
            </spring:bind>



            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${userForm['new']}">
                            <button type="submit" class="btn-lg btn-primary pull-right">Add
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn-lg btn-primary pull-right">Update
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>

    </div>

</div>

</body>

</html>