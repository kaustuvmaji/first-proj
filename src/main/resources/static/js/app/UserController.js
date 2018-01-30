'use strict';

angular.module('crudApp').controller('UserController',
    ['UserService', '$scope',  function( UserService, $scope) {

        var self = this;
        self.user = {};
        self.users=[];

        self.submit = submit;
        self.getAllUsers = getAllUsers;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.removeUser = removeUser;
     
        self.editUser = editUser;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            if (self.user.employeeId === undefined || self.user.employeeId === null) {
                console.log('Saving New User', self.user);
                createUser(self.user);
            } else {
                updateUser(self.user);
                console.log('User updated with id ', self.user.id);
            }
        }

        function createUser(user) {
            console.log('About to create user');
            UserService.createUser(user)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        self.successMessage = 'User created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.user={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        self.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateUser(user){
            console.log('About to update user');
            UserService.updateUser(user)
                .then(
                    function (response){
                        console.log('User updated successfully');
                        self.successMessage='User updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating User');
                        self.errorMessage='Error while updating User '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeUser(firstname,secondname){
            console.log('About to remove User with id '+firstname+secondname);
            UserService.removeUser(firstname,secondname)
                .then(
                    function(){
                        console.log('User '+firstname+secondname + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing user '+firstname+secondname +', Error :'+errResponse.data);
                    }
                );
        }


        function getAllUsers(){
            return UserService.getAllUsers();
        }

        function editUser(id,firstname,lastname) {
            self.successMessage='';
            self.errorMessage='';
            UserService.getUser(firstname,lastname).then(
                function (user) {
                    self.user = user;
                },
                function (errResponse) {
                    console.error('Error while removing user ' + id + ', Error :' + errResponse.data);
                }
            );
        }
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.user={};
            $scope.myForm.$setPristine(); //reset Form
        }
        
    }


    ]);