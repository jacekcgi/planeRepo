import { Component, Input } from '@angular/core';
import { DefaultCellComponent } from 'common/table'

@Component({
  template: `
    <a href="#" (click)="onEdit();false;"><i class="fa fa-edit"></i></a>
  `,
})
export class ActionsColumnComponent extends DefaultCellComponent {
    
    onEdit(){
        alert("Not implemented yet!!!");
    }

}