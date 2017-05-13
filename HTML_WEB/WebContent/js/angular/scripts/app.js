// Route provider.
var app = angular.module('demoApp', ['ui.router', 'demoApp.controller', 'demoApp.services']);

app.config(['$urlRouterProvider', '$stateProvider', function($urlRouterProvider, $stateProvider) {
	$urlRouterProvider.otherwise('/');
	
	$stateProvider
	.state('home', {
		 url:'/',
		 templateUrl: 'templates/angular/RestfulURI.html'
	 })
	 
	 .state('list', {
		 url:'/list',
		 templateUrl: 'templates/angular/EquipmentList.html',
		 controller: 'EquipListCtrl'
	 })
	 
	 .state('add', {
		 url:'/add',
		 templateUrl: 'templates/angular/EquipmentAdd.html',
		 controller: 'EquipAddCtrl'
	 })
	 
	 .state('edit', {
		 url:'/edit/:id',
		 templateUrl: 'templates/angular/EquipmentEdit.html',
		 controller: 'EquipEditCtrl'
	 })
	 
	 .state('websocket', {
		 url:'/websocket',
		 templateUrl: 'templates/angular/Websocket.html',
		 controller: 'WebsocketCtrl'
	 });	 
}]);