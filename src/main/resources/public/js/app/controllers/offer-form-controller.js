angular.module("checkout-app").controller(
		"offer-form-controller",
		function($scope, $routeParams, $location, products, offerService) {
			$scope.products = products.data;
			$scope.types = [ "ABSOLUTE_VALUE", "DISCOUNT_PERCENTAGE",
					"BUY_AND_WIN" ];
			$scope.showGifts = function() {
				if ($scope.offer) {
					var show = $scope.offer.type == "BUY_AND_WIN";
					console.log(show)
					return show;
				}
				return false;
			}
			
			$scope.verifyGift = function() {
				if ($scope.offer) {
					var shoulHaveGift = $scope.showGifts();
					if (!shoulHaveGift) {
						delete $scope.offer.gift;
					} else {
						delete $scope.offer.value;
					}
				}
			}

			if ($routeParams.id) {
				offerService.load($routeParams.id).success(function(response) {
					$scope.offer = response;
				});
			}
			$scope.save = function(offer) {
				if (offer.id) {
					offerService.update(offer).success(function(response) {
						$location.path("/offers");
					});
				} else {
					offerService.create(offer).success(function(response) {
						$location.path("/offers");
					});
				}
			}
		})