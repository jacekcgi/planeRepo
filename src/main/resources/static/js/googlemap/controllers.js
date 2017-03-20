map.controller('airportsController', ['$scope','$http', function($scope, $http) {

        $scope.loadAirports = function() {
                      $http.get("/findAirports")
                                 .then(function successCallback(response) {
                                         $scope.airportList = response.data;
                                 }, function errorCallback(response) {

                                 });
                 }
        $scope.sendPlaneToAirport = function(latitude,longitude) {
                     var wrapper = document.getElementById('wrapper');
                     if(wrapper) {
                        var sid = wrapper.getAttribute('plane')
                        if(sid) {
                            $http.get("/sendPlaneToPosition/"+sid+"/"+latitude+"/"+longitude+"/")
                                   .then(function successCallback(response) {
                                      console.log(response);
                                   }, function errorCallback(response) {
                                   });
                        }
                     }

                     console.log(sid+ " "+latitude+" "+longitude);
                }
}]);

