<%@ include file="tags.jsp" %>
<nav class="navbar navbar-default mega-nav navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <span class="navbar-brand" style="color:#c1e2b3">Bus Fleet Orchestration</span>
            </span>
        </div>
        <div class="collapse navbar-collapse" id="MainMenu">
            <ul class="nav navbar-nav menu-list">
                <li><a href="${contextPath}/bus-fleet?act=index_page">Home</a></li>
                <li><a href="${contextPath}/bus-fleet?act=contacts_page">Contacts</a></li>
            </ul>
            <%--<c:if test="${sessionScope.role == User.ROLE.ADMIN}">--%>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/bus-fleet?act=reg_page"><span class="glyphicon glyphicon-employee"></span> Sign Up</a></li>
                <li><a href="${pageContext.request.contextPath}/bus-fleet?act=login_page"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
            <%--</c:if>--%>
            <ul class="nav navbar-nav navbar-right">
                <li class="nav-item">
                    <form>
                        <select class="nav-select" id="language" name="language" onchange="submit()" title="Language">
                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                            <option value="ua" ${language == 'ua' ? 'selected' : ''}>Ukrainian</option>
                        </select>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
