var services = angular.module('demoApp.services', ['ngResource']);

var protocol = window.location.protocol;
var host = window.location.host;
var path = window.location.pathname;
var webProject = path.substring(0, path.indexOf('/', 1));

services.factory('EquipmentListFactory', function($resource) {
	return $resource(protocol + '//' + host + webProject + '/api/equip/list', {}, {
		query: { method: 'GET', isArray: true }
	});
});

services.factory('EquipmentPostFactory', function($resource) {
	return $resource(protocol + '//' + host + webProject + '/api/equip');
});

services.factory('EquipmentDeleteFactory', function($resource) {
	return $resource(protocol + '//' + host + webProject + + '/api/equip/:id', {}, {
		remove: { method: 'DELETE', params: { id: '@id'} }
	});
});

services.factory('EquipmentPutFactory', function($resource) {
	return $resource(protocol + '//' + host + webProject + '/api/equip/:id', {}, {
		query: { method: 'GET', isArray: true },
		update: { method: 'PUT', params: { id: '@id'} }
	});	
});

services.factory('WebsocketFactory', function() {
	
});


