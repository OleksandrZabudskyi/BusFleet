<%@ include file="../shared/tags.jsp" %>
<jsp:include page="../shared/header.jsp"/>
<jsp:include page="../shared/navigation.jsp"/>
<div class="container">
    <form method="POST" name="registrationForm" class="form-signin" action="${contextPath}/bus-fleet/registration">
        <h2 class="form-signin-heading"><fmt:message key="driver.registration"/></h2>
        <span class="text-danger">${infoMessage}</span>
        <div class="form-group">
            <input type="text" class="form-control" name="firstName" placeholder="<fmt:message key="first.name"/>"/>
            <span class="text-danger">${firstNameError}</span>
            <input type="text" class="form-control" name="lastName" placeholder="<fmt:message key="last.name"/>"/>
            <span class="text-danger">${lastNameError}</span>
            <input type="text" class="form-control" name="email" placeholder="<fmt:message key="email"/>"/>
            <span class="text-danger">${emailError}</span>
            <input type="text" class="form-control" name="phoneNumber" placeholder="<fmt:message key="phone.number"/>+38(044)234-44-33"/>
            <span class="text-danger">${phoneNumberError}</span>
            <input type="text" class="form-control" name="drivingLicenceNumber" placeholder="<fmt:message key="driving.licence"/>"/>
            <span class="text-danger">${drivingLicenceNumberError}</span>
            <input type="text" class="form-control" name="drivingExperience" placeholder="<fmt:message key="driving.experience"/>"/>
            <span class="text-danger">${drivingExperienceError}</span>
            <input type="password" class="form-control" name="password" placeholder="<fmt:message key="password"/>"/>
            <span class="text-danger">${passwordError}</span>
            <span class="text-danger">${errorMessage}</span>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="registration.submit"/></button>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
