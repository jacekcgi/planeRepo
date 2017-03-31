import { Component, Input } from '@angular/core';
import { DefaultCellComponent } from 'common/table'
import { BoxService } from 'common/modal/box.service';

@Component({
  template: `
    <a href="#" routerLink="/menu/user" routerLinkActive="active" [queryParams]="params"><i class="fa fa-edit"></i></a>
    <a href="#" (click)="onDelete($event);false;" style="color: red;"><i class="fa fa-times"></i></a>
  `,
})
export class UserActionsColumnComponent extends DefaultCellComponent {
    params: object;

    constructor(private boxService: BoxService) {
        super();
    }

    onDelete() {
        this.boxService.prompt("Not implemented yet!!!").then((result) => {
            if(result) {
                console.log('Ok now implement delete!!!');
            }
        });
    }

    ngOnInit() {  
      this.params = { sid: this.item["sid"] }; 
    }

}