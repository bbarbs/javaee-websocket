// Controllers
var controller = angular.module('demoApp.controller', []);

controller.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

controller.controller('EquipListCtrl', ['$scope', 'EquipmentListFactory', 'EquipmentDeleteFactory', '$location',
		function ($scope, EquipmentListFactory, EquipmentDeleteFactory, $location){
			
	$scope.newEquip = function () {
		$location.path('/add');
	};
	
	$scope.editEquip = function (equipPk) {
		$location.path('/edit/' + equipPk);
	};
	
	$scope.deleteEquip = function (equipPk) {
		EquipmentDeleteFactory.remove({ id: equipPk});
		$scope.equipments = EquipmentListFactory.query();
	};
	
	$scope.equipments = EquipmentListFactory.query();
	
}]);

controller.controller('EquipEditCtrl', ['$scope', '$stateParams', 'EquipmentPutFactory', '$location',
        function ($scope, $stateParams, EquipmentPutFactory, $location) {
    
	$scope.cancel = function () {
		$location.path('/list');		
	};
	
	$scope.update = function (equipPk, equipName, equipLicense, equipState, equipEfficiency, equipActivity, equipOperator) {
		EquipmentPutFactory.update({id: equipPk}, JSON.stringify({
			'pk': equipPk,
			'equipName': equipName, 
			'license': equipLicense,
			'state': equipState,
			'efficiency': equipEfficiency,
			'activity': equipActivity,
			'operator': equipOperator
			}));
	};
		
		
	$scope.equipments =EquipmentPutFactory.query({ id: $stateParams.id }, function() {
		console.log("Testing");
	}, function(response) {
		
	});
		
}]);

controller.controller('EquipAddCtrl', ['$scope', 'EquipmentPostFactory', '$location',
        function ($scope, EquipmentPostFactory, $location) {
	
	$scope.save = function () {
		EquipmentPostFactory.save(JSON.stringify($scope.equip));
		$location.path('/list');
	};
	
	$scope.cancel = function () {
		$location.path('/list');		
	};
}]);

controller.controller('WebsocketCtrl', ['$scope', 'WebsocketFactory'], 
		function ($scope, WebsFactory) {
	
});