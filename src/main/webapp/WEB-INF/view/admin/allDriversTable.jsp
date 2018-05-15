<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12 col-sm-9">
    <div class="row">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Driving Licence Number</th>
                    <th>Driving Experience</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="allBuses">
                <c:forEach var="driver" items="${drivers}">
                    <tr>
                        <td>${driver.id}</td>
                        <td>${driver.firstName}</td>
                        <td>${driver.lastName}</td>
                        <td>${driver.drivingLicenceNumber}</td>
                        <td>${driver.drivingExperience}</td>
                        <td><a class="btn btn-primary"
                               href="${pageContext.request.contextPath}/bus-fleet/admin/set_driver?tripId=${tripId}&driverId=${bus.id}&page=${page}"
                               role="button">Add To Trip</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>