import { ActionService } from 'app/services/action.service';
import { Injectable, Inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AxiosResponse } from "axios";

@Injectable()
export class PlaneService {
    constructor( @Inject(ActionService) private actions: ActionService) {
    }

    findPlanes() {
        return this.actions.get("/planeList");
    }

    findPlanesIDs() {
        return this.actions.get("/planeIdList");
    }

    findAllPlanesLocation(lastUpdate: string) {
        return this.actions.get(_.filter(["/allPlanesLocation/", lastUpdate]).join(""));
    }

    save(form: FormGroup, data: object) {
        return this.actions.postForm("/plane", data, form);
    }

    calculateDistance(actualTime: number, incomingTime: number, velocity: number) {
        var flightTime = (actualTime - incomingTime) / 3600000
        return velocity * flightTime;
    }

    calculateDestinationPoint(latitude: number, longitude: number, bearing: number, distance: number) {
        var earthRadius = 6371;
        latitude = this.toRadians(latitude);
        longitude = this.toRadians(longitude);
        bearing = this.toRadians(bearing);
        var angularDistance = distance / earthRadius;
        var latitudeEnd = Math.asin(Math.sin(latitude) * Math.cos(angularDistance) +

            Math.cos(latitude) * Math.sin(angularDistance) * Math.cos(bearing));
        var longitudeEnd = longitude + Math.atan2(Math.sin(bearing) * Math.sin(angularDistance) * Math.cos(latitude),
            Math.cos(angularDistance) - Math.sin(latitude) * Math.sin(latitudeEnd));

        var finalBearing = this.getFinalBearing(latitudeEnd, longitudeEnd, latitude, longitude);
        var point = { latitude: this.toDegrees(latitudeEnd), longitude: this.toDegrees(longitudeEnd), course: finalBearing };

        return point;
    }


    getFinalBearing(srcLat: number, srcLon: number, destLat: number, destLon: number) {
        var longDiff = destLon - srcLon;
        var y = Math.sin(longDiff) * Math.cos(destLat);
        var x = Math.cos(srcLat) * Math.sin(destLat) - Math.sin(srcLat) * Math.cos(destLat) * Math.cos(longDiff);
        var bearing = (this.toDegrees(Math.atan2(y, x)) + 360) % 360;
        return (bearing + 180) % 360;
    }
    toRadians(degree: number) {
        return degree * (Math.PI / 180);
    }

    toDegrees(radian: number) {
        return radian * (180 / Math.PI);
    }
}