import { OnInit, OnChanges, SimpleChanges, Component, ElementRef, ViewChild, AfterViewInit } from "@angular/core";
declare var google: any;

let $ = require('jquery');

@Component({
    selector: 'map',
    templateUrl: './map.component.html'
})

export class MapComponent implements OnInit, OnChanges, AfterViewInit {
    ngAfterViewInit(): void {
        this.initMap();
    }

    @ViewChild('map') mapDiv: any;

    ngOnChanges(changes: SimpleChanges): void {

    }

    ngOnInit(): void {
        // this.loadScript()

        console.log(this.mapDiv.nativeElement)
        console.log(document.getElementById('map'))
    }

    initMap() {
        console.log('test');
        var uluru = { lat: 54, lng: 17 };
        var map = new google.maps.Map(this.mapDiv.nativeElement, {
            zoom: 4,
            center: uluru
        });
        var marker = new google.maps.Marker({
            position: uluru,
            map: map
        });
    }
    // var location = new google.maps.LatLng(54, 17);

    // var mapOptions = {
    //   zoom: 10,
    //   center: location
    // };

    //     new google.maps.Map(document.getElementById('map'), mapOptions);
    // }


}