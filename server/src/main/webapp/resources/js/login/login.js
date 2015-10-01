var mymodal = angular.module('mymodal', ['ui.bootstrap']);

mymodal.controller('MainCtrl', function ($scope) {
	$scope.showModal = false;
	$scope.toggleModal = function(){
		$scope.showModal = !$scope.showModal;
	};
});

mymodal.controller('RegisterController', function($scope) {
	$scope.activeState = [true, false];
	$scope.activeIndex = 0;

	$scope.nextSlide = function ($event) {
		$event.stopPropagation();
		$event.preventDefault();
		var activeIndex = $scope.activeState.indexOf(true);

		if (activeIndex >= $scope.activeState.length - 1) {
			$scope.activeIndex =  $scope.activeState.indexOf(true);
			return;
		}

		$scope.activeState[activeIndex] = false;
		$scope.activeState[activeIndex + 1] = true;
		$scope.activeIndex =  $scope.activeState.indexOf(true);
	};
	$scope.prevSlide = function () {
		var activeIndex = $scope.activeState.indexOf(true);

		if (activeIndex <= 0) {
			$scope.activeIndex =  $scope.activeState.indexOf(true);
			return; // already reached the first slide
		}

		$scope.activeState[activeIndex] = false;
		$scope.activeState[activeIndex - 1] = true;
		$scope.activeIndex =  $scope.activeState.indexOf(true);
	};
	$scope.$watch("activeIndex", function (value) {
		var stateSize = $scope.activeState.length -1
		if (value === stateSize) {
			$(".next-button").removeClass("btn-warning").addClass("my-button-hidden");
			$(".subbmit-button").removeClass("my-button-hidden").addClass("btn-warning");
		} else if (value < stateSize) {
			$(".next-button").removeClass( "my-button-hidden" ).addClass( "btn-warning" );
			$(".subbmit-button").removeClass( "btn-warning" ).addClass( "my-button-hidden" );
		}
	});
});

mymodal.directive('modal', function () {
	return {
		template: '<div class="modal fade">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
		'<h4 class="modal-title">{{ title }}</h4>' +
		'</div>' +
		'<div class="modal-body" ng-transclude></div>' +
		'</div>' +
		'</div>' +
		'</div>',
		restrict: 'E',
		transclude: true,
		replace:true,
		scope:true,
		link: function postLink(scope, element, attrs) {
			scope.title = attrs.title;

			scope.$watch(attrs.visible, function(value){
				if(value == true)
					$(element).modal('show');
				else
					$(element).modal('hide');
			});

			$(element).on('shown.bs.modal', function(){
				scope.$apply(function(){
					scope.$parent[attrs.visible] = true;
				});
			});

			$(element).on('hidden.bs.modal', function(){
				scope.$apply(function(){
					scope.$parent[attrs.visible] = false;
				});
			});
		}
	};
});




mymodal.controller('LoginSubmitController',[ '$scope', '$http', function($scope, $http) {

	$scope.list = [];
	$scope.submit = function() {
		var formData = {
			"j_username" : $scope.email,
			"j_password" : $scope.password
		};
		var loginUrl ="http://localhost:8080/server-0.1/authentication";
		$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
		$http({
			url: loginUrl,
			method: "POST",
			params: { j_username: $scope.email, j_password : $scope.password}
		})
			.then(function(response) {

			},
			function(response) { // optional
				// failed
			});

		var logoutURL ="http://localhost:8080/server-0.1/app/logout";
		$http({
			url: logoutURL,
			method: "POST",
			params: { j_username: $scope.email, j_password : $scope.password}
		})
			.then(function(response) {
				// success
			},
			function(response) { // optional
				// failed
			});
	};
}]);

mymodal.controller('NewOrderController', ['$scope', '$http', function($scope) {
		$scope.state = {};
		$scope.state.submitted = false;
		$scope.submit = function () {
			$scope.state.submitted = true;
		};
	}])
	.directive('checkForm', ['$http',
		function($http) {
			return {
				template: '',
				scope: {
					'submitted': '=',
					'test1': '=',
					'test2': '=',
					'test3': '=',
					'test4': '='
				},
				restrict: 'E',
				link: function(scope, element, attr) {
					scope.$watch('submitted', function(newVal, oldVal) {
						if (newVal) {
							var userOrder = {
								name : scope.test1,
								phone : scope.test2,
								location : scope.test3,
								carclass : scope.test4
							};

							var userOrderUrl = "http://localhost:8080/server-0.1/userOrder";
							$http({
								url: userOrderUrl,
								dataType: 'json',
								method: 'POST',
								data: JSON.stringify(userOrder),
								headers: {
									'Accept': 'application/json',
									'Content-Type': 'application/json'
								}

							}).success(function(response){
								//$scope.response = response;
								var data = "Your taxi: " + response.carRegistrationSign + ", it'll be through "+ response.estimationTime;
								$("#getCode").html(data);
								$("#getCodeModal").modal('show');
							}).error(function(error){
								var data = "Your put wrong data ";
								$("#getCode").html(data);
								$("#getCodeModal").modal('show');
							});
							scope.submitted = false;
						}
						scope.test1 = "";
						scope.test2 = "";
						scope.test3 = "";
						scope.test4 = "";
					});


				}
			};
		}
	]);

