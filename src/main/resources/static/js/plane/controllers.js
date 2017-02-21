planes.controller('listPlanes', ['$scope', 'getAllPlanesDataService', function ($scope, getAllPlanesDataService) {

    getAllPlanesDataService.allPlanes(function (data) {
        $scope.planeList = data;
    });

    $scope.$on('planesDataPassed', function () {

            var isNotEmpty = getAllPlanesDataService.values;
            if (isNotEmpty) {
                $scope.planeList = getAllPlanesDataService.values;
            };
        });

}]);

planes.controller('savePlane', ['$scope','$http','getAllPlanesDataService', function($scope, $http, getAllPlanesDataService) {

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
                getAllPlanesDataService.allPlanes(function (data) {
                        getAllPlanesDataService.values = data;
                    });
            }, function errorCallback(response) {
                $scope.planeModified=false;
            });

       }
    }
}]);
