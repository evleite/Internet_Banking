'use strict';

var cpApp = angular.module('corePortalApp', ['ngRoute','ngResource', 'ui.bootstrap']);

cpApp.config(function($routeProvider){
	$routeProvider
		.when(
			'/login',
			{
				templateUrl: 'views/cp-login-form.html',
				controller: 'CPLoginCtrl'
			}
		)
		.when(
			'/main',
			{
				templateUrl: 'views/cp-main.html',
				controller: 'CPMainCtrl'
			}
		)
		.when(
			'/error',
			{
				templateUrl: 'views/cp-error-page.html'
			}
		);

	$routeProvider.otherwise({redirectTo: '/error'});
});

