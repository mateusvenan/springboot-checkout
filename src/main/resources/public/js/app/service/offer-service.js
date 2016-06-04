angular.module("checkout-app").service("offerService", function($http) {
	this.get = function() {
		return $http.get("/api/offers")
	}

	this.load = function(id) {
		return $http.get("/api/offers/" + id);
	}

	this.update = function(product) {
		return $http.put("/api/offers", product)
	}

	this.create = function(product) {
		return $http.post("/api/offers", product);
	}

})