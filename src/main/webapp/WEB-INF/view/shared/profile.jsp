<%@ include file="../shared/tags.jsp" %>
<div class="col-md-7 ">
    <div class="panel panel-default">
        <div class="panel-heading"><h4>Employee Profile</h4></div>
        <div class="panel-body">

            <div class="box box-info">

                <div class="box-body">
                    <div class="col-sm-6">
                        <h4 style="color:#00b1b1;">${activeUser.employee.firstName}</h4>
                        <span>${role}</span>
                    </div>
                    <div class="clearfix"></div>
                    <hr style="margin:5px 0 5px 0;">

                    <div class="col-sm-5 col-xs-6 tital ">First Name:</div>
                    <div class="col-sm-7 col-xs-6 ">${activeUser.employee.firstName}</div>

                    <div class="col-sm-5 col-xs-6 tital ">Last Name:</div>
                    <div class="col-sm-7">${activeUser.employee.lastName}</div>

                    <div class="col-sm-5 col-xs-6 tital ">Email</div>
                    <div class="col-sm-7">${activeUser.employee.email}</div>

                    <div class="col-sm-5 col-xs-6 tital ">Phone Number</div>
                    <div class="col-sm-7">${activeUser.employee.phoneNumber}</div>
                </div>
            </div>
        </div>
    </div>
</div>