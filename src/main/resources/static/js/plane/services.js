planes.service('getAllPlanesDataService', ['$http', '$log','$rootScope', function ($http, $log, $rootScope) {

    var allPlanesData = {};
    allPlanesData.values = {};

    this.allPlanes = function getAllPlanes(callback) {
        $http.get('/planeList').
            then(function successResponse(response) {
                callback(response.data);
                $rootScope.$broadcast('planesDataPassed');
            }, function errorResponse(response) {
                $log.error('database not connected');
            });
    };
}]);