<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <th>Action</th>
                </tr>
                </thead>
                <tbody id="allBuses">
                <c:forEach var="bus" items="${buses}">
                    <tr>
                        <td>${bus.id}</td>
                        <td>${bus.model}</td>
                        <td>${bus.licensePlate}</td>
                        <td>${bus.manufactureYear}</td>
                        <td>${bus.parkingSpot}</td>
                        <td><a class="btn btn-primary"
                               href="${pageContext.request.contextPath}/bus-fleet/admin/set_bus?tripId=${tripId}&busId=${bus.id}"
                               role="button">Add To Trip</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>