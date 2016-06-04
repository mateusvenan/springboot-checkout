angular.module("checkout-app").controller("purchase-controller",
		function($scope, purchases) {
			$scope.purchases = purchases.data;
		});