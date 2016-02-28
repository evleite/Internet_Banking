'use strict';

var cpApp = angular.module('corePortalApp', ['ngResource', 'ngRoute', 'ui.bootstrap']);

cpApp.config(function($routeProvider){
	$routeProvider
	.when(
			'/about',
			{
				template: 'views/cp-about.html'
			}
		)
		.when(
			'/login',
			{
				template: 'views/cp-login-form.html',
				controller: 'cpLoginCtrl'
			}
		)
		.when(
			'/errorPage',
			{
				template: 'views/cp-error-page.html'
			}
		);

	$routeProvider.otherwise({redirectTo: '/errorPage'});
});