import { Component, Input } from "@angular/core";
import { FlightDetails } from "app/domain";
import { Column, PageRequest, SearchRequest, Sort } from 'common/table';
import { AbstractControl, FormGroup, FormBuilder } from '@angular/forms';
import { AirportService } from 'app/services'

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
    { title: "airport.city", property: "city", sortable: true }
   ];

    data: [{}];
    
    searchRequest: SearchRequest = {
    pageRequest: { sort: { orders: [{ field: "name", ascending: true }] }, page: 0, size: 5 },
    filter: { name: "" }
  };

   filterForm: FormGroup;
  
   rows: number;
  
  

   constructor(private fb: FormBuilder,private airportService: AirportService) {
           this.toggled="toggled"; 
           
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