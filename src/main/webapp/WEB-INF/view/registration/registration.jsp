<%@ include file="../shared/tags.jsp" %>
<jsp:include page="../shared/header.jsp"/>
<jsp:include page="../shared/navigation.jsp"/>
<div class="container">
    <form method="POST" name="registrationForm" class="form-signin" action="${contextPath}/bus-fleet/registration">
        <h2 class="form-signin-heading">Driver Registration</h2>
        <div class="form-group">
            <input type="text" class="form-control" name="firstName" placeholder="First Name"/>
            <input type="text" class="form-control" name="lastName" placeholder="Last Name"/>
            <input type="text" class="form-control" name="email" placeholder="Email"/>
            <span class="text-danger">${emailError}</span>
            <input type="text" class="form-control" name="phoneNumber" placeholder="Phone Number"/>
            <input type="text" class="form-control" name="drivingLicenceNumber" placeholder="Driving Licence Number"/>
            <input type="text" class="form-control" name="drivingExperience" placeholder="Driving Experience"/>
            <input type="password" class="form-control" name="password" placeholder="Password"/>
            <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm your password"/>
            <span class="text-danger">${passwordError}</span>
            <span class="text-danger">${errorMessage}</span>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
