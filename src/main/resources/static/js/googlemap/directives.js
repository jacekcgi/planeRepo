map.directive('googleMap',['$interval','lazyLoadApi','locationService', function($interval,lazyLoadApi,locationService) {

  return {
    restrict: 'CA', // restrict by class name
    scope: {
      lat: '@', // latitude
      long: '@', // longitude
      zoom: '@',
      plane: '@'
    },
    link: function(scope, element, attrs) {
      var location = null;
      var map = null;
      var mapOptions = null;
      // Check if latitude and longitude are specified
      if (angular.isDefined(scope.lat) && angular.isDefined(scope.long)) {
        // Loads google map script
        lazyLoadApi.then( initializeMap )
      }

      // Initialize the map
      function initializeMap() {
        location = new google.maps.LatLng(scope.lat, scope.long);

        mapOptions = {
          zoom: parseInt(scope.zoom),
          center: location
        };

        map = new google.maps.Map(element[0], mapOptions);
        var plane = "M482,363.333c2.667,0.666,5,0.332,7-1c2-1.334,3-3.334,3-6v-36c0-6.668-3-12-9-16l-187-111    c-6-3.334-9-8.668-9-16v-125c0-6.667-1.333-13-4-19c-7.333-18.667-17.667-29-31-31c-2-0.667-4-1-6-1s-4.333,0.333-7,1    c-0.667,0-1.5,0.167-2.5,0.5s-2.167,0.833-3.5,1.5c-4.667,1.333-9,4.333-13,9s-7,9-9,13l-3,7c-2,6-3,12.333-3,19v125    c0,7.333-3,12.667-9,16l-187,111c-6,4-9,9.332-9,16v36c0,2.666,1,4.666,3,6c2,1.332,4.333,1.666,7,1l185-60    c2.667-1.334,5-1.168,7,0.5c2,1.666,3,3.832,3,6.5v97c0,7.334-2.667,13-8,17l-25,18c-5.333,4-8,9.666-8,17v24c0,2.666,1,4.666,3,6    s4.333,1.666,7,1l62-18c6.667-2,13.333-2,20,0l62,18c2.667,0.666,5,0.334,7-1s3-3.334,3-6v-24c0-7.334-2.667-13-8-17l-25-18    c-5.333-4-8-9.666-8-17v-97c0-3.334,1-5.668,3-7c2-1.334,4.333-1.334,7,0L482,363.333z"

        var icon = {path: plane,
                                                fillOpacity: 1,
                                                fillColor: '#ffda44',
                                                strokeWeight: 1,
                                                scale: 0.05
                                                }
       var markers = [];
       var lastUpdate;
       var storedLocationData;
       var positions = function() {
           locationService.currentPosition(scope.plane,lastUpdate,function(serverTime,data){
                  if(data) {
                    lastUpdate = serverTime
                    storedLocationData = data;
                  } else {
                    data = storedLocationData;
                  }
                   angular.forEach(data, function(value, key){
                            var distance=locationService.distance(serverTime,value.incomingTime,value.velocity);
                            var destPoint = locationService.destinationPoint(value.gpsLatitude,value.gpsLongitude,value.course,distance);
                            var planeSid = value.plane.sid;
                            var latlng = new google.maps.LatLng(destPoint.latitude.toString(),destPoint.longitude.toString());
                            icon["rotation"]=destPoint.course;
                            if(markers[planeSid]) {
                                var marker = markers[planeSid];
                                marker.setPosition(latlng);
                                marker.setIcon(icon);
                             } else {
                            var marker =  new google.maps.Marker({
                                        position: latlng,
                                        title:value.gpsLatitude + " "+value.gpsLongitude,
                                        icon: icon,
                                        map: map
                                      });
                                markers[planeSid] = marker;
                                }
                        });

               })
       };
       positions();
       $interval(positions,2000);
      }
    }
  };
}]);