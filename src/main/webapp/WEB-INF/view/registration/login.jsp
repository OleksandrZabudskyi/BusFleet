<%@ include file="../shared/tags.jsp" %>
<jsp:include page="../shared/header.jsp"/>
<jsp:include page="../shared/navigation.jsp"/>
<div class="container">
    <form method="POST" action="${contextPath}/bus-fleet/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>
        <span class="text-danger">${infoMessage}</span>
        <div class="form-group">
            <input name="email" type="text" class="form-control" placeholder="Email or phone"/>
            <span class="text-danger">${emailError}</span>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span class="text-danger">${passwordError}</span>
            <span class="text-danger">${errorMessage}</span>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
        </div>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>

