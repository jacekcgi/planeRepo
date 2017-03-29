
import { Injectable, Inject } from "@angular/core";
import { ActionService } from "app/services";

@Injectable()
export class FlightRoutesService {

    constructor(@Inject(ActionService) private actions: ActionService) {
    }

    findPlanes(request: any) {
        return this.actions.post("/find/flightRoutes", request);
    }

}