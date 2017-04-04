import { Component, Output, EventEmitter } from '@angular/core';
import { DefaultCellComponent } from 'common/table';
import { AirportService } from 'app/services';
@Component({
  template: `
    <button class="btn btn-link btn-sm" (click)="sendAirportSid()"><i class="fa fa-edit"></i></button>
  `,
  styles: [`
    .btn-link  {
      cursor: pointer;
    }
  `]
})
export class SidebarActionsColumnComponent extends DefaultCellComponent {
    

    constructor(private airportService: AirportService) {
        super();     
    }

    sendAirportSid() {
           this.airportService.onDestinationChange(this.item["sid"]);
             
    }
}