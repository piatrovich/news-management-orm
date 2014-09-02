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
        <spring:message code="title.edit"/>
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
                        <a id="backAction" href="javascript:void(0)" >
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
                <a id="toNewsList" href="${pageContext.request.contextPath}" class="list-group-item">
                    <spring:message code="menu.list.news"/>
                </a>
                <a id="article-delete" href="${pageContext.request.contextPath}/delete" class="list-group-item">
                    <spring:message code="menu.delete.news"/>
                </a>
            </div>

            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTags">Tags</a>
                        </h4>
                    </div>
                </div>
                <div id="collapseTags" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div id="menu-tags-block" class="list-group">
                            <span id="menu-tag-template" hidden="hidden">
                                <a class="tagItem" href="javascript:;" tagId=""></a>
                            </span>
                            <div class="list-group-item new-item-form">
                                <div id="new-author">
                                    <div class="col-md-8 item-input">
                                        <input class="newItem" type="text" value="new">
                                    </div>
                                    <div class="col-md-4">
                                        <button>Add</button>
                                    </div>
                                </div>
                            </div>
                            <div id="pagination-items" class="list-group-item">
                                <div class="col-md-4" id="previous-tag-page">
                                    <a>
                                        <span class="glyphicon glyphicon-circle-arrow-left"></span>
                                    </a>
                                </div>
                                <div class="col-md-4">
                                    <span id="current-tag-page-badge" class="badge">0</span>
                                </div>
                                <div class="col-md-4" id="next-tag-page">
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
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseAuthors">Authors</a>
                        </h4>
                    </div>
                </div>
                <div id="collapseAuthors" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="list-group">
                            <a class="list-group-item">Author 1</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="list-group">
                <a id="show-comments" href="" class="list-group-item">View comments</a>
            </div>

        </div>
        <!-- end Sidebar -->

        <!-- Content -->
        <div class="col-md-9">
            <div id="article-block" class="row articles">
                <form id="edit-form">
                    <div class="form-group">
                        <label for="inputTitle" class="col-md-3 control-label">
                            <spring:message code="page.body.title"/>
                        </label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="inputTitle" value="">
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
                            <textarea class="form-control" rows="4" id="inputShort"></textarea>
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
                            <textarea class="form-control" rows="10" id="inputLong"></textarea>
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label class="text-danger"></label>
                    </div>
                    <div class="form-group">
                        <label for="listTags" class="col-md-3 control-label">
                            Tags:
                        </label>
                        <div class="col-md-9">
                            <textarea class="form-control" rows="2" id="listTags" disabled="disabled"></textarea>
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label class="text-danger"></label>
                    </div>
                    <div class="form-group">
                        <label for="listAuthors" class="col-md-3 control-label">
                            Authors:
                        </label>
                        <div class="col-md-9">
                            <textarea class="form-control" rows="2" id="listAuthors" disabled="disabled"></textarea>
                        </div>
                    </div>
                    <div class="col-md-9 col-md-offset-3">
                        <label id="text-danger" class="text-danger"></label>
                    </div>
                    <div class="col-md-9 col-md-offset-3 right-position">
                        <input id="saveBtn" type="submit" class="btn btn-default"
                               value="<spring:message code="page.body.button.save"/>">
                        <input id="cancelBtn" type="button" class="btn btn-default"
                               onclick="window.location.href='${pageContext.request.contextPath}'"
                               value="<spring:message code="page.body.button.cancel"/>">
                    </div>
                    <div id="comments-block">
                        <div id="comment" class="col-md-12 single-comment">
                            <div class="col-md-6">
                                <span class="creation-date"></span>
                                <span class="divider"> | </span>
                            <span class="">
                                <em class="author"></em>
                            </span>
                            </div>
                            <div class="col-md-12">
                                <div class="comment-text"></div>
                            </div>
                        </div>
                    </div>
                    <div id="comment-pagination" class="col-md-12 text-center">
                        <a id="previous-comment-page" href="">
                            <span class="glyphicon glyphicon-circle-arrow-left"></span>
                        </a>
                        <span id="current-comment-page-badge" class="badge">1</span>
                        <a id="next-comment-page" href="">
                            <span class="glyphicon glyphicon-circle-arrow-right"></span>
                        </a>
                    </div>
                </form>
            </div>
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
    window.onload = loadNewsForEdit();
    window.onload = initMenuTags();
    window.onload = initShowComments();
</script>
</body>
</html>