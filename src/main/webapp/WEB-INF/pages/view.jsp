<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <spring:message code="title.view"/>
    </title>
    <!-- Styles -->
    <link href="<spring:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<spring:url value="/css/custom.css"/>" rel="stylesheet">
</head>
<body>
<div class="container">
    <!-- Static navbar -->
    <div class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}">
                            <spring:message code="menu.back"/>
                        </a>
                    </li>
                </ul>
                <c:import url="common/lang.jsp"/>
            </div>
        </div>
    </div>

    <!-- Body -->
    <div class="row">
        <!-- Sidebar-->
        <div class="col-md-3" id="sidebar">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/add" class="list-group-item">
                    <spring:message code="menu.add.news"/>
                </a>
                <a id="article-edit" href="${pageContext.request.contextPath}/edit" class="list-group-item">
                    <spring:message code="menu.edit.news"/>
                </a>
                <a id="article-delete" href="${pageContext.request.contextPath}/delete" class="list-group-item">
                    <spring:message code="menu.delete.news"/>
                </a>
            </div>
        </div>
        <!-- end Sidebar -->

        <!-- Content -->
        <div class="col-md-9">
                <div id="article-block" class="row articles">
                    <div class="col-md-9">
                        <h3 id="article-title"></h3>
                    </div>
                    <div class="col-md-3 date">
                        <p id="article-date"></p>
                    </div>
                    <div class="col-md-12">
                        <p id="article-description"></p>
                    </div>
                    <br />
                    <div class="col-md-12">
                        <p id="article-text"></p>
                    </div>
                </div>
        </div>
        <!-- end Content -->
    </div>
    <c:import url="common/footer.jsp"/>
    <c:import url="confirmation/deletion.jsp"/>
</div>
<script src="<spring:url value="/js/jquery-2.1.1.js"/>"></script>
<script src="<spring:url value="/js/scripts.js"/>"></script>
<script src="<spring:url value="/js/api.js"/>"></script>
<script type="text/javascript">
    window.onload = loadNewsForView();
</script>
</body>
</html>