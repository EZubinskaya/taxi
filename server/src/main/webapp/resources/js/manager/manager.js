var mymodal = angular.module('manager', []);
mymodal.controller('managerController', function ($scope, $http) {

    $scope.init = function () {
        var managerUrl = "http://localhost:8080/server-0.1/manager/all";
        $http.get(managerUrl).
            success(function (data, status, headers, config) {
                $scope.listOrder = data;
            }).
            error(function (data, status, headers, config) {
                // log error
            });
    };

    $scope.editOrder = function(order_id) {
        var index = getSelectedIndex(order_id);
        var order = $scope.listOrder[index];
        $scope.price = order.price;
        $scope.carRegistrationSign = order.carRegistrationSign;
        $scope.estimationTime = order.estimationTime;
        $scope.status = order.status;
        $scope.phone = order.phone;
    };


    $scope.deleteOrder = function(order_id) {
        var result = confirm("Are you sure?");
        if(result == true) {
            var index = getSelectedIndex(order_id);
            //TODO
            //sent delete to BD
            $scope.listOrder.splice(index, 1);
        }
    };

    $scope.update = function() {
        var index = getSelectedIndex($scope.order_id);
        $scope.listOrder[index].price = $scope.price;
        $scope.listOrder[index].carRegistrationSign = $scope.carRegistrationSign;
        $scope.listOrder[index].estimationTime = $scope.estimationTime;
        $scope.listOrder[index].status = $scope.status;
        $scope.listOrder[index].phone = $scope.phone;
    };

    $scope.save = function() {
        var newOrder = {
            price: $scope.price,
            carRegistrationSign: $scope.carRegistrationSign,
            estimationTime: $scope.estimationTime,
            status: $scope.estimationTime,
            phone: $scope.phone
        };
        $scope.listOrder.push(newOrder);
        var managerUrlSave = "http://localhost:8080/server-0.1/manager/save";
        $http({
            url: managerUrlSave,
            dataType: 'json',
            method: 'POST',
            data: JSON.stringify(newOrder),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }

        }).success(function(response){
            //$scope.response = response;
            var data = "Your taxi: " + response.carRegistrationSign + ", it'll be through "+ response.duration;
            $("#getCode").html(data);
            $("#getCodeModal").modal('show');
        });

        $scope.price = '';
        $scope.carRegistrationSign = '';
        $scope.estimationTime = '';
        $scope.status = '';
        $scope.phone = '';
    };

    function getSelectedIndex(order_id) {
        for(var a =0; a < $scope.listOrder.length; a++) {
            if($scope.listOrder[a].order_id == order_id) {
                return a;
            }
        }
        return -1;
    }
});

