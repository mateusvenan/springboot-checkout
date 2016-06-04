angular.module("checkout-app").config(function ($routeProvider) {
	$routeProvider.otherwise({
		redirectTo:"/store"
	});
	
	$routeProvider.when("/store",{
		templateUrl: "templates/store.html",
		controller: "store-controller",
		resolve: {
			products: function(productService) {
				return productService.get();
			},
			offers: function(offerService) {
				return offerService.get();
			}
		}
	});
	
	$routeProvider.when("/products",{
		templateUrl: "templates/products.html",
		controller: "product-controller",
		resolve: {
			products: function(productService) {
				return productService.get();
			}
		}
	});
	
	$routeProvider.when("/purchases",{
		templateUrl: "templates/purchases.html",
		controller: "purchase-controller",
		resolve: {
			purchases: function(checkoutService) {
				return checkoutService.get();
			}
		}
	});
	
	$routeProvider.when("/new-product",{
		templateUrl: "templates/form-product.html",
		controller: "product-form-controller"
	});
	
	$routeProvider.when("/edit-product/:id",{
		templateUrl: "templates/form-product.html",
		controller: "product-form-controller"
	});
	
	$routeProvider.when("/offers",{
		templateUrl: "templates/offers.html",
		controller: "offer-controller",
		resolve: {
			offers: function(offerService) {
				return offerService.get();
			}
		}
	});
	
	$routeProvider.when("/new-offer",{
		templateUrl: "templates/form-offer.html",
		controller: "offer-form-controller",
		resolve: {
			products: function(productService) {
				return productService.get();
			}
		}
	});
	
	$routeProvider.when("/edit-offer/:id",{
		templateUrl: "templates/form-offer.html",
		controller: "offer-form-controller",
		resolve: {
			products: function(productService) {
				return productService.get();
			}
		}
	});
});
