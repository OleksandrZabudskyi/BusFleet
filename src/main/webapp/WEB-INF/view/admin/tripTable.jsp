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
                    <th>Bus Id</th>
                    <th>Driver Id</th>
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
                        <c:choose>
                            <c:when test="${trip.bus.id  == 0}">
                                <td><a class="btn btn-primary btn-sm"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/all_buses?tripId=${trip.id}&page=${currentPage}"
                                       role="button">Add Bus</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>${trip.bus.id}
                                    <a class="btn btn-primary btn-sm"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/delete_bus?tripId=${trip.id}&page=${currentPage}"
                                       role="button">Delete</a></td>
                                </td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${trip.driver.id  == 0}">
                                <td><a class="btn btn-primary"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/all_drivers?tripId=${trip.id}&page=${currentPage}"
                                       role="button">Add Driver</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>${trip.driver.id}
                                    <a class="btn btn-primary btn-sm"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/delete_driver?tripId=${trip.id}&page=${currentPage}"
                                       role="button">Delete</a></td>
                                </td>
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