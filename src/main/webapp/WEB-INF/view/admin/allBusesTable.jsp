<%@ include file="../shared/tags.jsp" %>
<div class="row">
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="bus.id"/></th>
                <th><fmt:message key="bus.model"/></th>
                <th><fmt:message key="license.plate"/></th>
                <th><fmt:message key="produce.year"/></th>
                <th><fmt:message key="parking.spot"/></th>
                <th><fmt:message key="route"/></th>
                <th><fmt:message key="from"/></th>
                <th><fmt:message key="to"/></th>
                <th><fmt:message key="to.trip"/></th>
            </tr>
            </thead>
            <tbody id="allBuses">
            <c:forEach var="bus" items="${buses}">
                <tr>
                    <td>${bus.key.id}</td>
                    <td>${bus.key.model}</td>
                    <td>${bus.key.licensePlate}</td>
                    <td>${bus.key.manufactureYear}</td>
                    <td>${bus.key.parkingSpot}</td>
                    <td>${bus.value.name}</td>
                    <td>${bus.value.destinationFrom}</td>
                    <td>${bus.value.destinationTo}</td>
                    <c:choose>
                        <c:when test="${bus.key.used == false}">
                            <td><a class="btn btn-primary"
                                   href="${pageContext.request.contextPath}/bus-fleet/admin/set_bus?tripId=${tripId}&busId=${bus.key.id}&page=${page}"
                                   role="button"><fmt:message key="add"/></a></td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>