<%@ include file="../shared/tags.jsp" %>
<div class="row">
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="licence.number"/></th>
                <th><fmt:message key="first.name"/></th>
                <th><fmt:message key="last.name"/></th>
                <th><fmt:message key="skill.year"/></th>
                <th><fmt:message key="to.trip"/></th>
            </tr>
            </thead>
            <tbody id="allBuses">
            <c:forEach var="driver" items="${drivers}">
                <tr>
                    <td>${driver.drivingLicenceNumber}</td>
                    <td>${driver.firstName}</td>
                    <td>${driver.lastName}</td>
                    <td>${driver.drivingExperience}</td>
                    <c:choose>
                        <c:when test="${driver.assigned == false}">
                            <td><a class="btn btn-primary"
                                   href="${pageContext.request.contextPath}/bus-fleet/admin/set_driver?tripId=${tripId}&driverId=${driver.id}&page=${page}"
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