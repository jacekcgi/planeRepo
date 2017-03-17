import { Component } from '@angular/core';
import { Column, PagingRequest, } from './table.component'

@Component({
  selector: 'page-planes',
  templateUrl: './planes.component.html',
})
export class PlanesComponent {
    columns: [Column] = [{title: "Name", property: "name", sortable: true}, {title: "Registration", property: "registration", sortable: true}];
    data: [{}] = [{name: "Dupa", registration: "234"}, {name: "Dupa2", registration: "2342"}];
    pagingRequest: PagingRequest = { sortFilterChain: {field: "name", ascending: true }, offset: 0, limit: 25 };  
}