var planes = angular.module('planes', []);
planes.controller('savePlane', ['$scope','$http', function($scope,$http) {

     $scope.master = {};
     $scope.reset = function(form) {
         if (form) {
           form.$setPristine();
           form.$setUntouched();
         }
         $scope.plane = angular.copy($scope.master);
       };
      $scope.submitForm = function (isValid) {

      if(isValid) {
            $http.post("/plane",$scope.plane)
            .then(function successCallback(response) {
                $scope.planeModified=true;
                $scope.reset($scope.form);
            }, function errorCallback(response) {
                $scope.planeModified=false;
            });

       }
    }
}]);
