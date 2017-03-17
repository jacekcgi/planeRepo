map.service('airportService', ['$http',function airportService($http) {

this.airportsList = function getAirports(callback) {
        $http.get('/findAirports').
        then(function successResponse(response) {
            callback(response.data);
        }, function errorResponse(response) {
            $log.error('database problem');
        });
    };


  }]);


