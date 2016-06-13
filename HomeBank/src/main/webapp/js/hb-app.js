'use strict';

var cpApp = angular.module('homeBankApp', ['ngRoute','ngResource', 'ui.bootstrap']);

cpApp.config(function($routeProvider){
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
			'/main',
			{
				templateUrl: 'views/hb-main.html',
				//controller: 'HBMainCtrl'
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

