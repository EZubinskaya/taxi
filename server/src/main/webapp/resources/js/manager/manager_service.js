'use strict';
var App = angular.module('myApp',[]);
App.factory('ManagerService', function($http, $q){

    return {

        fetchAllOrders: function() {
            return $http.get('http://localhost:8080/server-0.1/manager/all')
                .then(
                function(response){
                    return response.data;
                }
            );
        },

        createOrder: function(order){
            return $http.post('http://localhost:8080/server-0.1/manager/', order)
                .then(
                function(response){
                    return response.data;
                }
            );
        },

        updateOrder: function(order, orderId){
            return $http.put('http://localhost:8080/server-0.1/manager/'+orderId, order)
                .then(
                function(response){
                    return response.data;
                }
            );
        },

        deleteOrder: function(orderId){
            return $http.delete('http://localhost:8080/server-0.1/manager/'+orderId)
                .then(
                function(response){
                    return response.data;
                }
            );
        }

    };

});


App.controller('ManagerController', function($scope, ManagerService) {
    var self = this;
    self.orders={orderId:null,price:'',carRegistrationSign:'',estimationTime:''};
    self.orders=[];

    self.fetchAllOrders = function(){
        ManagerService.fetchAllOrders()
            .then(
            function(d) {
                self.orders = d;
            }
        );
    };

    self.createOrder = function(order){
        ManagerService.createOrder(order)
            .then(
            self.fetchAllOrders
        );
    };

    self.updateOrder = function(order, orderId){
        ManagerService.updateOrder(order, orderId)
            .then(
            self.fetchAllOrders
        );
    };

    self.deleteOrder = function(orderId){
        ManagerService.deleteOrder(orderId)
            .then(
            self.fetchAllOrders
        );
    };

    self.fetchAllOrders();

    self.submit = function() {
        if(self.order.orderId==null){
            self.createOrder(self.order);
        }else{
            self.updateOrder(self.order, self.order.orderId);

        }
        self.reset();
    };

    self.edit = function(id){
        for(var i = 0; i < self.orders.length; i++){
            if(self.orders[i].orderId == id) {
                self.order = angular.copy(self.orders[i]);
                break;
            }
        }
    };

    self.remove = function(id){
        for(var i = 0; i < self.orders.length; i++){
            if(self.orders[i].orderId == id) {
                self.reset();
                break;
            }
        }
        self.deleteOrder(id);
    };


    self.reset = function(){
        self.orders={price:'',carRegistrationSign:'',estimationTime:'',status:''};
        $scope.myForm.$setPristine(); //reset Form
    };

});

