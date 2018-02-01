<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">User </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.user.employeeId" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="firstName">First Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.firstName" id="firstName" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
	               <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="secondName">Last Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.secondName" id="secondName" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="salary">Salary</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.user.salary" id="salary" class="form-control input-sm" placeholder="Enter your Salary." required ng-pattern="ctrl.onlyNumbers"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="ContactDetails">ContactDetails</label>
	                        <div class="row">
	                        	<div class="form-group col-md-12">
	                             <label class="col-md-4 control-lable" for="streets">streets</label> 
	                             <div class="col-md-7">
	                           		<input type="text" ng-model="ctrl.user.salary" id="streets" class="form-control input-sm" placeholder="Enter your address."/>
								</div>
	                        </div>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.user.employeeId ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Users </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>NAME</th>
		                <th>DATEOFBIRTH</th>
		                <th>DEPARTMENT</th>
		                <th>SALARY</th>
		                <th>CONTACTS</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllUsers()">
		                <td>{{u.employeeId}}</td>
		                <td>{{u.firstName}} {{u.secondName}} </td>
		                <td>{{u.dateOfBirth}}</td>
		                <td>{{u.department}}</td>
		                <td>{{u.salary}}</td>
		                <td> 
		                	<table class="table-condensed">
			                	<thead class="tfoot">
		          				  <tr>
									<th><h6><i>ADDRESS</i></h6></th>
									<th><h6><i>EMAILID</h6></i></th>
									<th><h6><i>PHONENUMBER</h6></i></th>
									<th><h6><i>MOBILENUMBER</h6></i></th>
		          				  </tr>
			          			<thead>	  
			                	<tbody>
			                		<tr ng-repeat="cd in u.contactDetails">
			                			<td>{{cd.address.addressLine1}}</td>
			                			<td>{{cd.emailId}}</td>
			                			<td>{{cd.phoneNumber}}</td>
			                			<td>{{cd.mobileNumber}}</td>
			                		</tr>
			               	 	</tbody>	
		   					</table>
		                </td>
		                <td><button type="button" ng-click="ctrl.editUser(u.employeeId,u.firstName,u.secondName)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeUser(u.firstName,u.secondName)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>