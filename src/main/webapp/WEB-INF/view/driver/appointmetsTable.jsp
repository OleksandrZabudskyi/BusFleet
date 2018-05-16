<%@ include file="../shared/tags.jsp" %>
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
                    <th>Bus Model</th>
                    <th>Parking Spot</th>
                    <th>Confirmation</th>
                </tr>
                </thead>
                <tbody id="allBuses">
                <c:forEach var="trip" items="${trips}">
                    <tr>
                        <td>${trip.route.name}</td>
                        <td>${trip.route.destinationFrom}</td>
                        <td>${trip.route.destinationTo}</td>
                        <td>${trip.number}</td>
                        <td>${trip.startTime}</td>
                        <td>${trip.endTime}</td>
                        <td>${trip.bus.model}</td>
                        <td>${trip.bus.parkingSpot}</td>
                        <c:choose>
                            <c:when test="${trip.confirmation == false}">
                                <td><a class="btn btn-primary"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/confirm_trip?tripId=${tripId}"
                                       role="button">Confirm</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>Confirmed</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
