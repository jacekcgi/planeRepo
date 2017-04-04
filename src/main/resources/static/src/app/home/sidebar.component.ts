import { Component, Input } from "@angular/core";
import { FlightDetails } from "app/domain";
import { Column, PageRequest, SearchRequest, Sort } from 'common/table';
import { AbstractControl, FormGroup, FormBuilder } from '@angular/forms';
import { AirportService,FlightRoutesService } from 'app/services'
import { SidebarActionsColumnComponent } from './sidebar.actions.column.component';
import { Subscription } from "rxjs/Rx";

@Component({
    selector: 'ap-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
   @Input() toggled: string;   
   @Input() flightDetails: FlightDetails;
  
    columns: [Column] = [
    { title: "airport.name", property: "name", sortable: true },
    { title: "airport.country", property: "country", sortable: true },
    { title: "airport.city", property: "city", sortable: true },
    { title: "actions", property: "name", cell: SidebarActionsColumnComponent }

   ];

    data: [{}];
    
    searchRequest: SearchRequest = {
    pageRequest: { sort: { orders: [{ field: "name", ascending: true }] }, page: 0, size: 5 },
    filter: { name: "" }
  };

   filterForm: FormGroup;
  
   rows: number;
  
  subscription: Subscription;
  

   constructor(private fb: FormBuilder,private airportService: AirportService,private flightRoutesService: FlightRoutesService) {
           this.toggled="toggled"; 
           this.subscription = airportService.destinationChange$.subscribe(airportSid => {
           let flightRoute:any = {"sid" :this.flightDetails.flightRouteSid, "destination": airportSid}
           flightRoutesService.updateDestination(flightRoute).then((response) => {
             if(response) {
                this.flightDetails.destinationCity = response.destination.city;
                this.flightDetails.flightDistance = response.flightDistance;
             }
            
           });;
        })
           
    }


  ngOnInit() {
    this.createForm();
  }   

   createForm() {
     this.filterForm = this.fb.group({
      name: [''],
      city: [''],
      country: ['']
    });
  }

  fetchData() {
     this.airportService.findAirports(this.searchRequest).then((response) => {
      this.rows = response.count;
      this.data = response.entities;
      this.searchRequest.pageRequest = response.pagingRequest;
    });
  }
  onFilter(filter: any) {
    this.searchRequest.filter = filter;
    this.fetchData();
  }

  onChange(pageRequest: PageRequest) {
    this.searchRequest.pageRequest = pageRequest;
    this.fetchData()
  }

}