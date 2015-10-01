'use strict';
var App = angular.module('myApp',[]);
App.factory('AdminService', function($http, $q){

    return {

        fetchAllManagers: function() {
            return $http.get('http://localhost:8080/server-0.1/admin/all')
                .then(
                function(response){
                    return response.data;
                }
            );
        },

        createManager: function(manager){
            return $http.post('http://localhost:8080/server-0.1/manager/', manager.id)
                .then(
                function(response){
                    return response.data;
                }
            );
        },

        updateManager: function(manager, id){
            return $http.put('http://localhost:8080/server-0.1/admin/'+id, manager)
                .then(
                function(response){
                    return response.data;
                }
            );
        },

        deleteManager: function(id){
            return $http.delete('http://localhost:8080/server-0.1/admin/'+id)
                .then(
                function(response){
                    return response.data;
                }
            );
        }

    };

});


App.controller('ManagerController', function($scope, AdminService) {
    var self = this;
    self.managers={id:null,username:'',password:''};
    self.managers=[];

    self.fetchAllManagers = function(){
        AdminService.fetchAllManagers()
            .then(
            function(d) {
                self.managers = d;
            }
        );
    };

    self.createManager = function(manager){
        AdminService.createManager(manager)
            .then(
            self.fetchAllManagers
        );
    };

    self.updateManager = function(manager, id){
        AdminService.updateManager(manager, id)
            .then(
            self.fetchAllManagers
        );
    };

    self.deleteManager = function(id){
        AdminService.deleteManager(id)
            .then(
            self.fetchAllManagers
        )
    };

    self.fetchAllManagers();

    self.submit = function() {
        if(self.manager.id==null){
            self.createManager(self.manager);
        }else{
            self.updateManager(self.manager, self.manager.id);

        }
        self.reset();
    };

    self.edit = function(id){
        for(var i = 0; i < self.managers.length; i++){
            if(self.managers[i].id == id) {
                self.manager = angular.copy(self.managers[i]);
                break;
            }
        }
    };

    self.remove = function(id){
        for(var i = 0; i < self.managers.length; i++){
            if(self.managers[i].id == id) {
                self.reset();
                break;
            }
        }
        self.deleteManager(id);
    };


    self.reset = function(){
        self.managers={username:'',password:''};
        $scope.myForm.$setPristine(); //reset Form
    };

});

