angular.module("checkout-app").controller("product-form-controller", 
		function($scope, $routeParams, $location, productService) {
	if ($routeParams.id) {
		productService.load($routeParams.id).success( function(response) {
			$scope.product = response;
		});  
	}
	$scope.save = function (product) {
		if (product.id) {
			productService.update(product).success( function(response) {
				$location.path("/products");
			});
		} else {
			productService.create(product).success( function(response) {
				$location.path("/products");
			});
		}
	}
})