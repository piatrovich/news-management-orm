<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <spring:message code="error.404.title"/>
    </title>
    <!-- Styles -->
    <link href="<spring:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/css/custom.css"/>" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="error-template center">
                <h1>
                    <spring:message code="error.404.title"/>
                </h1>
                <h2>
                    <spring:message code="error.404.message"/>
                </h2><br />
                <div class="error-actions">
                    <a href="${pageContext.request.contextPath}" class="btn btn-primary btn-lg">
                        <span class="glyphicon glyphicon-home"></span>
                        <spring:message code="error.go.home"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
