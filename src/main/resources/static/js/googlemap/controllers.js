map.controller('airports', ['$scope','ShowHideWindow', function ($scope,ShowHideWindow) {

$scope.bahama = "Test czy dziala";

// $scope.$on('AirportCommandWindow', function () {
//
////            var isNotEmpty = PlanesDataService.values;
////            if (isNotEmpty) {
////                $scope.planeList = PlanesDataService.values;
////            };
//
//            $scope.hideOrNot = ShowHideWindow.showAirports;
//
//            console.log("Jestem w Controlerze w zdarzeniu czy sie odpalilo: "+ $scope.hideOrNot )
//
//
//
//        });

$scope.flightView = {
  visibility : false
}

$scope.closeFlightView = function () {
  $scope.flightView.visibility = false;
}

$scope.flightDetails

//$scope.hideOrNot = ShowHideWindow.showAirports;
console.log("Wiadomosc z kontrolera: "+ $scope.flightView.visibility );
}]);


