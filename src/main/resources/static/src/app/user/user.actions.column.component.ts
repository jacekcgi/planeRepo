import { Component, Input, EventEmitter, Output  } from '@angular/core';
import { DefaultCellComponent } from 'common/table'
import { BoxService } from 'common/modal/box.service';
import { NotificationService, UserService } from 'app/services';


@Component({
    template: `
    <div class="text-center">
        <a href="#" routerLink="/menu/user" routerLinkActive="active" [queryParams]="params"><i class="fa fa-edit"></i></a>
        <a href="#" (click)="onDelete($event);false;" style="color: red;"><i class="fa fa-times"></i></a>
    </div>
  `,
})
export class UserActionsColumnComponent extends DefaultCellComponent {
    params: any;

    @Output() onRefreshTable = new EventEmitter<boolean>();

    constructor(private boxService: BoxService, private userService: UserService, private ns: NotificationService) {
        super();
    }

    
    onDelete() {
        this.boxService.prompt("user.deletePrompt").then((result: boolean) => {
            if (result) {
                let sid = this.item["sid"];
                this.userService.delete(sid).then((response) => {
                    this.ns.success('user.deleteSuccess');
                });
                this.userService.refreshTable();
            }
        });
    }

    ngOnInit() {
        this.params = { sid: this.item["sid"] };
    }

}