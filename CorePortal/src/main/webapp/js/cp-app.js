'use strict';

var cpApp = angular.module('corePortalApp', ['ngRoute']);

cpApp.config(function($routeProvider){
	$routeProvider
	.when(
			'/about',
			{
				templateUrl: 'views/cp-about.html'
			}
		)
		.when(
			'/login',
			{
				templateUrl: 'views/cp-login-form.html',
				controller: 'cpLoginCtrl'
			}
		)
		.when(
			'/errorPage',
			{
				templateUrl: 'views/cp-error-page.html'
			}
		);

	$routeProvider.otherwise({redirectTo: '/errorPage'});
});