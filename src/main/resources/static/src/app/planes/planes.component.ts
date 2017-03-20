import { Component } from '@angular/core';
import { Column, PagingRequest, } from './table.component'
import { AbstractControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { PlaneService } from 'app/services'
import { NotificationsService } from 'angular2-notifications';

@Component({
  selector: 'page-planes',
  templateUrl: './planes.component.html',
})
export class PlanesComponent {
  columns: [Column] = [{ title: "Name", property: "name", sortable: true }, { title: "Registration", property: "registration", sortable: true }, {title: "Description", property: "description"}];
  data: [{}] = [{ name: "xxx", registration: "234" }, { name: "sss", registration: "2342" }];
  pagingRequest: PagingRequest = { sortFilterChain: { field: "name", ascending: true }, offset: 0, limit: 25 };

  planeForm: FormGroup;


  constructor(private fb: FormBuilder, private planeService: PlaneService, private ns: NotificationsService) {

  }

  onSubmit() {
    this.planeService.save(this.planeForm, this.planeForm.value).then((response) => {
      this.ns.success('Success', 'Plane has been created');
      this.planeForm.reset();
    });
  }

  ngOnInit() {
    this.planeForm = this.fb.group({
      name: ['', Validators.required],
      registration: ['', Validators.required],
      description: ['']
    })
    this.planeService.findPlanes().then((response) => {
      this.data = response;
    })
  }
}