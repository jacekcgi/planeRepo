import { Component, Input } from "@angular/core";
import { FlightDetails } from "app/domain";


@Component({
    selector: 'ap-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.css']
})

export class SidebarComponent {
   @Input() toggled: string;
   @Input() flightDetails: FlightDetails;
     @Input() planeName: string;
   
    constructor() {
           this.toggled="toggled"; 
    }

     
}