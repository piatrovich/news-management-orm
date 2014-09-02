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
        <spring:message code="title.index"/>
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
                <c:import url="common/lang.jsp"/>
            </div>
        </div>
    </div>

    <!-- Body -->
    <div class="row">
        <!-- Sidebar-->
        <div class="col-md-3" id="sidebar">
            <div class="list-group">
                <a href="add" class="list-group-item">
                    <spring:message code="menu.add.news"/>
                </a>
            </div>
        </div>
        <!-- end Sidebar -->

        <!-- Content -->
        <div id="news-body" class="col-md-9">
                <div id="article-block-template" class="row articles">
                        <div class="col-md-9">
                            <h3 id="article-title"></h3>
                        </div>
                        <div class="col-md-3 date">
                            <p id="article-date"></p>
                        </div>
                        <div class="col-md-12">
                            <p id="article-description"></p>
                        </div>
                        <div class="col-md-5 col-md-offset-7">
                            <div class="row">
                                <div class="col-md-4">
                                    <p class="right-position">
                                        <a id="article-view" href="view">
                                            <spring:message code="action.view"/>
                                        </a>
                                    </p>
                                </div>
                                <div class="col-md-4">
                                    <p class="right-position">
                                        <a id="article-edit" href="edit">
                                            <spring:message code="action.edit"/>
                                        </a>
                                    </p>
                                </div>
                                <div class="col-md-4">
                                    <p class="right-position">
                                        <a id="article-delete" href="delete">
                                            <spring:message code="action.delete"/>
                                        </a>
                                    </p>
                                </div>
                            </div>
                        </div>
                </div>
        </div>
        <div id="paging" class="col-md-9 col-md-offset-3 text-center">
            <a id="previous-page" href="">
                <span class="glyphicon glyphicon-circle-arrow-left"></span>
            </a>
            <span id="current-page-badge" class="badge">1</span>
            <a id="next-page" href="">
                <span class="glyphicon glyphicon-circle-arrow-right"></span>
            </a>
        </div>
        <!-- end Content -->
    </div>
    <c:import url="common/footer.jsp"/>
    <c:import url="confirmation/deletion.jsp"/>
</div>
<script src="<spring:url value="/js/jquery-2.1.1.js"/>"></script>
<script src="<spring:url value="/js/api.js"/>"></script>
<script type="text/javascript">
    window.onload = loadNewsPage(1);
</script>
</body>
</html>