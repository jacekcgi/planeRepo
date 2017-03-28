import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from "@angular/forms";
import { Column, SearchRequest, PageRequest, DateCellComponent } from "common/table";
import { FlightRoutesService } from "app/services";

@Component({
  selector: 'page-flight-routes',
  templateUrl: './flightroutes.component.html'
})
export class FlightRoutesComponent implements OnInit {

  columns: [Column] = [
    { title: "flightroute.startDate", property: "startDate", sortable: true, cell : DateCellComponent },
    { title: "flightroute.sourceName", property: "source.name", sortable: true },
    { title: "flightroute.destinationName", property: "destination.name", sortable: true },
    { title: "flightroute.planeName", property: "plane.name", sortable: true },
    { title: "flightroute.registration", property: "plane.registration", sortable: true }
  ];
  data: [{}];
  searchRequest: SearchRequest = {
    pageRequest: { sort: { orders: [] }, page: 0, size: 25 },
    filter: { name: "" }
  };
  rows: number;

  filterForm: FormGroup;

  constructor(private fb: FormBuilder, private flightRouteService: FlightRoutesService) { }

  ngOnInit() {
    // this.createForm();
    this.fetchData();
  }

  createForm() {
    this.filterForm = this.fb.group({
      name: ['']
    });
  }

  fetchData() {
    this.flightRouteService.findPlanes(this.searchRequest).then((response) => {
      this.rows = response.count;
      this.data = response.entities;
      this.searchRequest.pageRequest = response.pagingRequest;
    })
  }

  onChange(pageRequest: PageRequest) {
    this.searchRequest.pageRequest = pageRequest;
    this.fetchData()
  }

  onFilter(filter: any) {
    this.searchRequest.filter = filter;
    this.fetchData();
  }
}
