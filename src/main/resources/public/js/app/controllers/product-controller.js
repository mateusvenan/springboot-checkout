angular.module("checkout-app").controller("product-controller", function($scope, products) {
	$scope.products = products.data;
})