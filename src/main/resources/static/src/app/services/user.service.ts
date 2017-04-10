import { ActionService } from 'app/services/action.service';
import { Injectable, Inject, } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AxiosResponse } from "axios";
import { Subject } from 'rxjs/Subject';


@Injectable()
export class UserService {
    private refreshTableData = new Subject<any>();

    refreshTableData$ = this.refreshTableData.asObservable();

    constructor(private actions: ActionService) {
    }

// sever communication
    findUsers(request: any) {
        return this.actions.post("/find/users", request);
    }

    getUser(sid: string) {
        return this.actions.get("/get/user/" + sid);
    }

    save(userForm: FormGroup) {
        return this.actions.postForm("/save/user", userForm.value, userForm);
    }

    update(userForm: FormGroup) {
        return this.actions.putForm("/update/user", userForm.value, userForm);
    }

    delete(sid: string) {
        return this.actions.delete("/delete/user/" + sid);
    }


// components comunication
    refreshTable() {
        this.refreshTableData.next();
    }
}
