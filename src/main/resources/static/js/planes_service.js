var planes = angular.module('planes', []);
planes.controller('savePlane', ['$scope','$http', function($scope,$http) {
  console.log("jestem");
    $scope.submitForm = function () {
       $http.post("/plane",$scope.plane)
       .then(function successCallback(response) {
            console.log("success " +response);
       }, function errorCallback(response) {
            console.log("error "+response);
       });
    }
}]);
