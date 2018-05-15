<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    <p class="visible-xs">
        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i
                class="glyphicon glyphicon-chevron-left"></i></button>
    </p>
    <div class="well sidebar-nav">
        <ul class="nav">
            <li>Route</li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/admin/routes">Get Routes</a></li>
            <li>Trips</li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/admin/trips_and_routes">Get Trips</a></li>
            <li>Buses and Drivers</li>
            <li class="active"><a href="${pageContext.request.contextPath}/bus-fleet/admin/all_buses">All Buses</a>
            </li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/admin/all_drivers">All Drivers</a></li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/admin/drivers_with_buses">Buses With Drivers</a>
            </li>
            <li>Profile</li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/admin/user_info">Info</a></li>
        </ul>
    </div>
</div>