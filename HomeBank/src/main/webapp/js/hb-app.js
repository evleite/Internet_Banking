'use strict';

var hbApp = angular.module('homeBankApp', ['ngRoute', 'ngResource']);

hbApp.config(function($routeProvider){
	$routeProvider
	.when(
		'/about',
		{
			templateUrl: 'views/hb-about.html'
		}
	)
	.when(
		'/login',
		{
			templateUrl: 'views/hb-login-form.html',
			controller: 'HBLoginCtrl'
		}
	)
	.when(
		'/error',
		{
			templateUrl: 'views/hb-error-page.html'
		}
	);

	$routeProvider.otherwise({redirectTo: '/error'});
});