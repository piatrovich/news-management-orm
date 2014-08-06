<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div id="deleteDialog" style="display: none">
    <p>
        <spring:message code="confirm.delete.msg"/>
    </p>
</div>
<div id="warningDialog" style="display: none">
    <p>
        <spring:message code="confirm.discard.changes"/>
    </p>
</div>
