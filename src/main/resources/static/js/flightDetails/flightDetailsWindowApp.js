var myApp = angular.module('FlightDetailsApp', []);

myApp.controller('flightDetailsController', ['$scope', '$window', function($scope, $window) {
    $scope.flightDetails = $window.flightDetails;
}]);