<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12 col-sm-9">
    <div class="row">
        <div class="table-responsive">

            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Route name</th>
                    <th>Destination From</th>
                    <th>Destination To</th>
                    <th>Trip number</th>
                    <th>Trip Start Time</th>
                    <th>Trip End Time</th>
                    <th>Bus License Plate</th>
                    <th>Driver Last Name</th>
                </tr>
                </thead>
                <tbody id="allBuses">
                <c:forEach var="trip" items="${trips}">
                    <tr>
                        <td>${trip.route.routeName}</td>
                        <td>${trip.route.destinationFrom}</td>
                        <td>${trip.route.destinationTo}</td>
                        <td>${trip.tripNumber}</td>
                        <td>${trip.tripStartTime}</td>
                        <td>${trip.tripEndTime}</td>
                        <td>${trip.bus.licensePlate}</td>
                        <td>${trip.driver.lastName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>