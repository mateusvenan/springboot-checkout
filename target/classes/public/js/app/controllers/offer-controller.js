angular.module("checkout-app").controller("offer-controller", function($scope, offers) {
	$scope.offers = offers.data;
})