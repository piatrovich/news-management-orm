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
        <spring:message code="title.add"/>
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
                <a href="${pageContext.request.contextPath}" class="list-group-item">
                    <spring:message code="menu.list.news"/>
                </a>
            </div>
        </div>
        <!-- end Sidebar -->

        <!-- Content -->
        <div class="col-md-9">
            <div id="article-block" class="row articles">
                <form id="addArticle">
                    <div class="form-group">
                        <label for="inputTitle" class="col-md-3 control-label">
                            <spring:message code="page.body.title"/>
                        </label>
                        <div class="col-md-9">
                            <input type="text" name="title" class="form-control" id="inputTitle">
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label id="title-danger" class="text-danger"></label>
                    </div>
                    <div class="form-group">
                        <label for="inputShort" class="col-md-3 control-label">
                            <spring:message code="page.body.description.short"/>
                        </label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="description" rows="4" id="inputShort"></textarea>
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label id="description-danger" class="text-danger"></label>
                    </div>
                    <div class="form-group">
                        <label for="inputLong" class="col-md-3 control-label">
                            <spring:message code="page.body.description.long"/>
                        </label>
                        <div class="col-md-9">
                            <textarea class="form-control" name="text" rows="10" id="inputLong"></textarea>
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label id="text-danger" class="text-danger"></label>
                    </div>
                    <div class="col-md-9 col-md-offset-3 right-position">
                        <input id="createBtn" type="button" class="btn btn-default"
                               value="<spring:message code="page.body.button.save"/>">
                        <input id="delBtn" type="button" class="btn btn-default"
                               value="<spring:message code="page.body.button.cancel"/>"
                               onclick="window.location.href = '${pageContext.request.contextPath}'">
                    </div>
                </form>
            </div>
        </div>
        <!-- end Content -->
    </div>
    <c:import url="common/footer.jsp"/>
</div>
<c:import url="confirmation/deletion.jsp"/>
<script src="<spring:url value="/js/jquery-2.1.1.js"/>"></script>
<script src="<spring:url value="/js/api.js"/>"></script>
<script type="text/javascript">
    window.onload = addingArticle();
</script>
</body>
</html>