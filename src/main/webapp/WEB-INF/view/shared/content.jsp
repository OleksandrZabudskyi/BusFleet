<%@ include file="tags.jsp" %>
<div class="container">
    <div class="row my-4">
        <div class="col-lg-8">
            <img class="img-fluid rounded" src="${contextPath}/resources/img/bus-depot.jpg" alt="">
        </div>
        <div class="col-lg-4">
            <h1>Comfort Busfleet</h1>
            <p>Comfort Busfleet comprehensive and well maintained fleet comprises more than 100 buses, providing you
                with flexible, safe and reliable options for a range of trips in a lot of direction in city and suburb. There are several
                types of vehicles available from our seven depots</p>
            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/bus-fleet?act=reg_page">Driver registration</a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">Medium Passenger</h2>
                    <img class="img-fluid rounded" src="${contextPath}/resources/img/medium-bus.jpg" alt="">
                </div>
                <div class="card-footer">
                    <a href="#" class="btn btn-primary">More Info</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">Luxury 5 Star Coaches</h2>
                    <img class="img-fluid rounded" src="${contextPath}/resources/img/luxury-bus.png" alt="">
                </div>
                <div class="card-footer">
                    <a href="#" class="btn btn-primary">More Info</a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title">Route Service Fleet</h2>
                    <img class="img-fluid rounded" src="${contextPath}/resources/img/route-bus.jpg" alt="">
                </div>
                <div class="card-footer">
                    <a href="#" class="btn btn-primary">More Info</a>
                </div>
            </div>
        </div>
    </div>
</div>
