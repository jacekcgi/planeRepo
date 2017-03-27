import { ActionService } from 'app/services/action.service';
import { Injectable, Inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AxiosResponse } from "axios";
import { FlightDetailsDto } from "app/domain";

declare var google: any;

@Injectable()
export class PlaneService {
    constructor( @Inject(ActionService) private actions: ActionService) {
    }

    findPlanes(request: any) {
        return this.actions.post("/find/planes", request);
    }

    findPlanesIDs() {
        return this.actions.get("/planeIdList");
    }

    findAllPlanesLocation() {
        return this.actions.get("/allPlanesLocation/");
    }

    save(form: FormGroup, data: object) {
        return this.actions.postForm("/plane", data, form);
    }

    calculateDistance(lastUpdate: Date, velocity: number) {
        var elapsedTime = lastUpdate ? (new Date().getTime() - lastUpdate.getTime()) / 3600000 : 0; // in hours
        return velocity * elapsedTime * 1000; //im meters
    }

    calculateDestinationPoint(flightData: FlightDetailsDto, distance: number) {

        var from = new google.maps.LatLng(flightData.currentLatitude, flightData.currentLongitude);
        var dest = new google.maps.LatLng(flightData.destinationLatitude, flightData.destinationLongitude);

        var finalBearing = google.maps.geometry.spherical.computeHeading(from, dest);
        var latlng = google.maps.geometry.spherical.computeOffset(from, distance, finalBearing);
        var point = { latlng: latlng, course: finalBearing };

        return point;
    }

}
