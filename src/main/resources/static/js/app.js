'use strict';

var app = angular.module('GoogleOAuth2Microservice', ["ngRoute"]);

app.config(function($routeProvider) {
	  $routeProvider
		.when('/', {
		templateUrl : 'pages/home.html',
		controller  : 'AppCtrl'
			  })
		   .when('/apidoc', {
		templateUrl : 'pages/apidoc.html',
		controller  : 'ApiDocCtrl'
		  })
		  .when('/blog', {
		templateUrl : 'pages/blog.html',
		controller  : 'BlogCtrl'
			  })
		  .when('/contact', {
		templateUrl : 'pages/contact.html',
		controller  : 'ContactCtrl'
		  })
	    .otherwise({redirectTo: '/'});
});