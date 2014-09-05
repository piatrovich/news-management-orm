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

            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTags">
                                <spring:message code="label.tags"/>
                            </a>
                        </h4>
                    </div>
                </div>
                <div id="collapseTags" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div id="menu-tags-block" class="list-group">
                            <span id="menu-tag-template" hidden="hidden">
                                <a class="tagItem" href="javascript:;" tagId=""></a>
                                <span class="badge"></span>
                            </span>
                            <div id="pagination-items" class="list-group-item">
                                <div class="col-md-4" id="previous-tag-countered-page">
                                    <a>
                                        <span class="glyphicon glyphicon-circle-arrow-left"></span>
                                    </a>
                                </div>
                                <div class="col-md-4">
                                    <span id="current-tag-countered-page-badge" class="badge big-badge">0</span>
                                </div>
                                <div class="col-md-4" id="next-tag-countered-page">
                                    <a>
                                        <span class="glyphicon glyphicon-circle-arrow-right"></span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseAuthors">
                                <spring:message code="label.authors"/>
                            </a>
                        </h4>
                    </div>
                </div>
                <div id="collapseAuthors" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div id="menu-authors-block" class="list-group">
                            <span id="menu-author-template" hidden="hidden">
                                <a class="authorItem" href="javascript:;" authorId=""></a>
                                <span class="badge"></span>
                            </span>
                            <div id="pagination-authors" class="list-group-item">
                                <div class="col-md-4" id="previous-author-countered-page">
                                    <a>
                                        <span class="glyphicon glyphicon-circle-arrow-left"></span>
                                    </a>
                                </div>
                                <div class="col-md-4">
                                    <span id="current-author-countered-page-badge" class="badge big-badge">0</span>
                                </div>
                                <div class="col-md-4" id="next-author-countered-page">
                                    <a>
                                        <span class="glyphicon glyphicon-circle-arrow-right"></span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseComments">
                                <spring:message code="menu.most.commented"/>
                            </a>
                        </h4>
                    </div>
                </div>
                <div id="collapseComments" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div id="menu-comments-block" class="list-group">
                            <a id="top5" class="list-group-item" href="">
                                <spring:message code="menu.top.5"/>
                            </a>
                            <a id="top20" class="list-group-item" href="">
                                <spring:message code="menu.top.20"/>
                            </a>
                            <a id="top100" class="list-group-item" href="">
                                <spring:message code="menu.top.100"/>
                            </a>
                        </div>
                    </div>
                </div>

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
            <span id="current-page-badge" class="badge big-badge">1</span>
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
<script src="<spring:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript">
    window.onload = loadNewsPage(1);
    window.onload = initMenuForIndexPage();
</script>
</body>
</html>