<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12 col-sm-9">
    <div class="row">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Bus Id</th>
                    <th>Bus Model</th>
                    <th>License Plate</th>
                    <th>Manufacture Year</th>
                    <th>Parking Spot</th>
                    <th>Driver Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Driving Licence Number</th>
                    <th>Driving Experience</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="allBuses">
                <c:forEach var="bus" items="${buses}">
                    <c:forEach var="driver" items="${bus.drivers}">
                        <tr>
                            <td>${bus.id}</td>
                            <td>${bus.model}</td>
                            <td>${bus.licensePlate}</td>
                            <td>${bus.manufactureYear}</td>
                            <td>${bus.parkingSpot}</td>
                            <td>${driver.id}</td>
                            <td>${driver.firstName}</td>
                            <td>${driver.lastName}</td>
                            <td>${driver.drivingLicenceNumber}</td>
                            <td>${driver.drivingExperience}</td>
                            <c:choose>
                                <c:when test="${bus.used == false}">
                                    <td><a class="btn btn-primary"
                                           href="${pageContext.request.contextPath}
                                       /bus-fleet/admin/set_bus_driver?tripId=${tripId}&busId=${bus.id}&driverId=${driver.id}&page=${page}"
                                           role="button">Add Bus With Driver</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>