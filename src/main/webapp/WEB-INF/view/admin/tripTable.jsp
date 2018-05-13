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
                        <c:choose>
                            <c:when test="${trip.bus.id  == 0}">
                                <td><a class="btn btn-primary"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/free-buses?route"
                                       role="button">Add Bus</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>${trip.bus.licensePlate}</td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${trip.driver.id  == 0}">
                                <td><a class="btn btn-primary"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/free-drivers"
                                       role="button">Add Driver</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>${trip.driver.lastName}</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <jsp:include page="../shared/pagination.jsp"/>
        </div>
    </div>
</div>