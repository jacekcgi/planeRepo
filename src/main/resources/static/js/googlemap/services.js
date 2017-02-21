map.service('lazyLoadApi', ['$window', '$q',function lazyLoadApi($window, $q) {
  function loadScript() {
    var s = document.createElement('script')
    s.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyBMM5SVaQN_uhe5GVZj41g5s0db1OBNtFE&language=en&callback=initMap'
    document.body.appendChild(s)
  }
  var deferred = $q.defer()

  $window.initMap = function() {
    deferred.resolve()
  }

  if ($window.attachEvent) {
    $window.attachEvent('onload', loadScript)
  } else {
    $window.addEventListener('load', loadScript, false)
  }

  return deferred.promise
}]);

map.service('locationService',['$http', function ($http) {
    this.currentPosition = getCurrentPosition;
    function getCurrentPosition(planeId,callback) {
    var url = '/planeLocation';
    if(planeId){
        url=url+'/'+planeId
    }
       $http.get(url).
            then(function successCallback(response) {
              callback(response.data);
            }, function errorCallback(response) {
//             callback(response.data);
            });
     }

}]);

