angular.module("checkout-app").service("productService", function($http) {
	this.get = function() {
		return $http.get("/api/products")
	}

	this.load = function(id) {
		return $http.get("/api/products/" + id);
	}

	this.update = function(product) {
		return $http.put("/api/products", product)
	}

	this.create = function(product) {
		return $http.post("/api/products", product);
	}

})