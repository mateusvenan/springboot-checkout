angular.module("checkout-app").service("checkoutService", function($http){
	this.create = function(shoppingCart) {
		return $http.post("/api/checkout", shoppingCart);
	}
	
	this.get = function() {
		return $http.get("/api/checkout");
	}
});