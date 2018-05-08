<%@ include file="tags.jsp" %>
<nav class="navbar navbar-default mega-nav navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <span class="navbar-brand" style="color:#c1e2b3">Bus Fleet Orchestration</span>
            </span>
        </div>
        <div class="collapse navbar-collapse" id="MainMenu">
            <ul class="nav navbar-nav menu-list">
                <li><a href="${contextPath}/bus-fleet/index_page">Home</a></li>
                <li><a href="${contextPath}/bus-fleet/contacts_page">Contacts</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${sessionScope.role eq 'ADMIN' or sessionScope.role eq 'DRIVER'}">
                        <li><a href="${pageContext.request.contextPath}/bus-fleet/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/bus-fleet/login_page"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
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
