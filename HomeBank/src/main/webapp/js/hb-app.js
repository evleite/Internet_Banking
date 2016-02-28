'use strict';

var hbApp = angular.module('homeBankApp', ['ngResource', 'ngRoute', 'ui.bootstrap']);

hbApp.config(function($routeProvider){
	$routeProvider
	.when(
		'/about',
		{
			template: 'views/hb-about.html'
		}
	)
	.when(
		'/login',
		{
			template: 'views/hb-login-form.html',
			controller: 'hbLoginCtrl'
		}
	)
	.when(
		'/errorPage',
		{
			template: 'views/hb-error-page.html'
		}
	);

	$routeProvider.otherwise({redirectTo: '/errorPage'});
});