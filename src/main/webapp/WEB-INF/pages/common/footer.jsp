<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="navbar navbar-default">
    <div class="container-fluid">
        <div class="news-footer">
            <div class="stat text-center">
                <span>
                    <spring:message code="stat.news.count"/>
                </span>
                <span id="news-count"></span>
                <span> | </span>
                <span>
                    <spring:message code="stat.comments.count"/>
                </span>
                <span id="comments-count"></span>
                <span> | </span>
                <span>
                    <spring:message code="stat.tags.count"/>
                </span>
                <span id="tags-count"></span>
                <span> | </span>
                <span>
                    <spring:message code="stat.authors.count"/>
                </span>
                <span id="authors-count"></span>
            </div>
            <div>
                <p><spring:message code="footer.name"/> &#64 2014</p>
            </div>
        </div>
    </div>
</div>

