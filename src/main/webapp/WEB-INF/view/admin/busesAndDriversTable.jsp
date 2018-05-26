<%@ include file="../shared/tags.jsp" %>
<div class="row">
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="bus.model"/></th>
                <th><fmt:message key="license.plate"/></th>
                <th><fmt:message key="produce.year"/></th>
                <th><fmt:message key="parking.spot"/></th>
                <th><fmt:message key="first.name"/></th>
                <th><fmt:message key="last.name"/></th>
                <th><fmt:message key="licence.number"/></th>
                <th><fmt:message key="skill.year"/></th>
                <th><fmt:message key="to.trip"/></th>
            </tr>
            </thead>
            <tbody id="allBuses">
            <c:forEach var="bus" items="${buses}">
                <c:forEach var="driver" items="${bus.drivers}">
                    <tr>
                        <td>${bus.model}</td>
                        <td>${bus.licensePlate}</td>
                        <td>${bus.manufactureYear}</td>
                        <td>${bus.parkingSpot}</td>
                        <td>${driver.firstName}</td>
                        <td>${driver.lastName}</td>
                        <td>${driver.drivingLicenceNumber}</td>
                        <td>${driver.drivingExperience}</td>
                        <c:choose>
                            <c:when test="${bus.used == false and driver.assigned == false}">
                                <td><a class="btn btn-primary"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/set_bus_driver?tripId=${tripId}&busId=${bus.id}&driverId=${driver.id}&page=${page}"
                                       role="button"><fmt:message key="add"/></a></td>
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