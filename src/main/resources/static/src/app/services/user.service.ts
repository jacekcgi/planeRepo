import { ActionService } from 'app/services/action.service';
import { Injectable, Inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AxiosResponse } from "axios";


@Injectable()
export class UserService {
    constructor( @Inject(ActionService) private actions: ActionService) {
    }

    findUsers(request: any) {
        return this.actions.post("/find/users", request);
    }

    getUser(sid:string) {
         return this.actions.get("/get/user/" + sid);
    }

    save(userForm :FormGroup) {
        return this.actions.postForm("/save/user", userForm.value, userForm);
    }
}
