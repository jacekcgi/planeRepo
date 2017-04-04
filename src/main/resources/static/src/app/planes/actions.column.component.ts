import { Component, Input } from '@angular/core';
import { DefaultCellComponent } from 'common/table'

@Component({
  template: `
    <a href="#" routerLink="/menu/plane" routerLinkActive="active" [queryParams]="params"><i class="fa fa-edit"></i></a>
  `,
})
export class ActionsColumnComponent extends DefaultCellComponent {
    params: object;

    onEdit(){
        alert("Not implemented yet!!!");
    }

    ngOnInit() {  
      this.params = { sid: this.item["sid"] }; 
    }
    
}