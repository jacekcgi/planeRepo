import { ActionService } from 'app/services/action.service';
import { Injectable, Inject } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable()
export class PlaneService {
    constructor(@Inject(ActionService) private actions: ActionService){
    }

    findPlanes() {
        return this.actions.get("/planeList");
    }

    findPlanesIDs() {
        return this.actions.get("/planeIdList");
    }

    save(data : object) {
        return this.actions.post("/plane", data);
    }

    save2(form : FormGroup, data : object) {
        return this.actions.postForm("/plane", data, form);
    }
}