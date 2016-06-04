angular.module("checkout-app").controller("store-controller", function($scope,$location, products, offers, checkoutService) {
	$scope.products = products.data;
	$scope.offers = offers.data;
	$scope.showSummary = function () {
		var selected = [];
		$scope.products.forEach( function (product) {
			if(product.amount && parseInt(product.amount) > 0) {
				selected.push(calculateTotalPrice(product));
			}
		})
		$scope.selected = selected;
		$scope.isSummary = true;		
	}
	
	$scope.checkout = function() {
		var items = []
		$scope.selected.forEach(function(product) {
			items.push({
				product: product,
				amount: product.amount
			});
		});
		if (items.length > 0){
			checkoutService.create({items: items}).success(function(response) {
				$location.path("/purchases")
			});
		}
	}
	$scope.back= function() {
		delete $scope.isSummary;
	}
	function calculateTotalPrice(product) {
		var amount = parseInt(product.amount);
		var priceWithDiscount = parseFloat(product.price);
		product.totalPrice = amount * priceWithDiscount;
		return product
	}
})