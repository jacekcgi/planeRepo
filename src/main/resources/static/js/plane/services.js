planes.service('PlanesDataService', ['$http', '$log','$rootScope', function ($http, $log, $rootScope) {

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

planes.service("DetailsPlaneFlightService", ["$http", "$log", function ($http, $log){

    this.flightDetails = function getFlightDetailsAboutPlane(flightId,callback) {
        $http.get('/flightDetails/'+flightId).
        then(function successResponse(response) {
            callback(response.data);
        }, function errorResponse(response) {
            $log.error('database not connected or no details entry for plane_id = '+ flightId);
        });
    };

}]);