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
                <br/>

                <div class="col-md-12">
                    <p id="article-text"></p>
                </div>
                <div id="tags-block" class="col-md-12 text-right">
                    <span id="tags-span" class="text-danger" hidden="hidden"></span>
                </div>
                <div class="col-md-12">
                    <div>
                        <textarea id="comment-message" class="form-control" rows="3"></textarea>
                    </div>
                </div>
                <div class="col-md-12">
                    <button id="add-comment-btn" class="btn btn-default" type="button">
                        <spring:message code="btn.add.comment"/>
                    </button>
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
            </div>
        </div>
        <!-- end Content -->
    </div>
    <c:import url="common/footer.jsp"/>
    <c:import url="confirmation/deletion.jsp"/>
</div>
<script src="<spring:url value="/js/jquery-2.1.1.js"/>"></script>
<script src="<spring:url value="/js/api.js"/>"></script>
<script type="text/javascript">
    window.onload = loadNewsForView();
</script>
</body>
</html>