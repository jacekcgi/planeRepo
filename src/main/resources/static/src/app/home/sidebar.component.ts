import { Component, Input } from "@angular/core";
import { FlightDetails } from "app/domain";
import { Column, PageRequest, SearchRequest, Sort } from 'common/table'


@Component({
    selector: 'ap-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
   @Input() toggled: string;   
   @Input() flightDetails: FlightDetails;

   constructor() {
           this.toggled="toggled"; 
           
    }

rows:number = 1;
     
}