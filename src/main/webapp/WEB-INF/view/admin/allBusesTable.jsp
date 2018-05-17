<%@ include file="../shared/tags.jsp" %>
<div class="col-xs-12 col-sm-9">
    <div class="row">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>id</th>
                    <th>Bus Model</th>
                    <th>License Plate</th>
                    <th>Manufacture Year</th>
                    <th>Parking Spot</th>
                    <th>Route Name</th>
                    <th>Destination From</th>
                    <th>Destination To</th>
                    <th>Action</th>
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
                                       role="button">Add To Trip</a></td>
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
</div>