
import { Injectable, Inject } from "@angular/core";
import { ActionService } from "app/services";
import { FormGroup } from "@angular/forms";

@Injectable()
export class FlightRoutesService {

    constructor(@Inject(ActionService) private actions: ActionService) {
    }

    findPlanes(request: any) {
        return this.actions.post("/find/flightRoutes", request);
    }

    save(form: FormGroup, data: object) {
        return this.actions.postForm("/flightRoute/create", data, form);
    }

}