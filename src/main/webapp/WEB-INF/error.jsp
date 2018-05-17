<%@ include file="view/shared/tags.jsp" %>
<jsp:include page="view/shared/header.jsp"/>
<jsp:include page="view/shared/navigation.jsp"/>
<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
</head>
<body>
<h2>
    Error Page<br/>
    <span style="font-style: italic;">Error <%--<%= Exception %>--%><%=exception.getMessage()%>
    </span>
</h2>
<br>
<a class="btn btn-primary"
       href="${pageContext.request.contextPath}/bus-fleet/index_page"
       role="button">Index Page</a>
</body>
</html>