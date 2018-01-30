'use strict';

angular.module('crudApp').factory('UserService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllUsers: loadAllUsers,
                getAllUsers: getAllUsers,
                getUser: getUser,
                createUser: createUser,
                updateUser: updateUser,
                removeUser: removeUser
            };

            return factory;

            function loadAllUsers() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                console.log(urls.LISTALL);
                $http.get(urls.LISTALL)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all users');
                            $localStorage.users = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading users');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllUsers(){
                return $localStorage.users;
            }

            function getUser(firstname,lastname) {
                console.log('Fetching User with id :'+firstname);
                var deferred = $q.defer();
                $http.get(urls.GETEMPLOYEE + firstname)
                    .then(
                        function (response) {
                            console.log('Fetched successfully User with id :'+firstname);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading user with id :'+firstname);
                            deferred.reject(errResponse);
                        }
                    );
              
                return deferred.promise;
            }

            function createUser(user) {
                console.log('Creating User');
                var deferred = $q.defer();
                $http.post(urls.ADDEMPLOYEE, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating User : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateUser(user) {
                console.log('Updating User'+user);
                let jsonData = JSON.parse(user);
                console.log('Updating User'+jsonData);
                var deferred = $q.defer();
                $http.put(urls.UPDATEEMPLOYEE + user.employeeId, user)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating User with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeUser(firstname,secondname) {
                console.log('Removing User with id '+firstname+secondname);
                var deferred = $q.defer();
                $http.delete(urls.DELETEEMPLOYEE +'?firstName='+firstname+'&lastName='+secondname)
                    .then(
                        function (response) {
                            loadAllUsers();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing User with id :'+firstname+secondname);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]);