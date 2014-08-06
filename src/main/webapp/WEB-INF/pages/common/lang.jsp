<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<ul class="nav navbar-nav navbar-right">
    <li>
        <a href="<c:url value="?lang=en"/>">
            <spring:message code="lang.title.en"/>
        </a>
    </li>
    <li>
        <a href="?lang=ru">
            <spring:message code="lang.title.ru"/>
        </a>
    </li>
</ul>
