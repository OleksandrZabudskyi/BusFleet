<%@ include file="../shared/tags.jsp" %>
<div class="col-xs-12 col-sm-9">
    <div class="row">
        <div class="table-responsive">

            <table class="table table-hover w-auto">
                <thead>
                <tr>
                    <th><fmt:message key="route"/></th>
                    <th><fmt:message key="from"/></th>
                    <th><fmt:message key="to"/></th>
                    <th><fmt:message key="trip"/></th>
                    <th><fmt:message key="start.time"/></th>
                    <th><fmt:message key="end.time"/></th>
                    <th><fmt:message key="confirmed"/></th>
                    <th><fmt:message key="bus"/></th>
                    <th><fmt:message key="driver"/></th>
                    <th><fmt:message key="bus.driver"/></th>
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
                        <td>${trip.confirmation}</td>
                        <c:choose>
                            <c:when test="${trip.bus.id  == 0}">
                                <td><a class="btn btn-primary btn-sm"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/all_buses?tripId=${trip.id}&page=${currentPage}"
                                       role="button"><fmt:message key="add"/></a></td>
                            </c:when>
                            <c:otherwise>
                                <td>${trip.bus.id}
                                    <a class="btn btn-primary btn-sm"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/delete_bus?tripId=${trip.id}&page=${currentPage}"
                                       role="button"><fmt:message key="delete"/></a></td>
                                </td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${trip.driver.id  == 0}">
                                <td><a class="btn btn-primary btn-sm"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/all_drivers?tripId=${trip.id}&page=${currentPage}"
                                       role="button"><fmt:message key="add"/></a></td>
                            </c:when>
                            <c:otherwise>
                                <td>${trip.driver.id}
                                    <a class="btn btn-primary btn-sm"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/delete_driver?tripId=${trip.id}&page=${currentPage}"
                                       role="button"><fmt:message key="delete"/></a></td>
                                </td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${trip.driver.id  == 0 and trip.bus.id  == 0}">
                                <td><a class="btn btn-primary btn-sm"
                                       href="${pageContext.request.contextPath}/bus-fleet/admin/buses_with_drivers?tripId=${trip.id}&page=${currentPage}"
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
            <jsp:include page="../shared/pagination.jsp"/>
        </div>
    </div>
</div>