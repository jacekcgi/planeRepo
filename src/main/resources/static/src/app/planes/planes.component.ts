import { Component, ViewChild } from '@angular/core';
import { Column, PageRequest, SearchRequest, Sort } from 'common/table'
import { AbstractControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { PlaneService } from 'app/services'
import { NotificationService } from 'app/services';
import { ActionsColumnComponent } from './actions.column.component';
import { Modal } from 'common/modal/modal.window.component';

@Component({
  selector: 'page-planes',
  templateUrl: './planes.component.html',
})
export class PlanesComponent {
  columns: [Column] = [
    { title: "airplane.name", property: "name", sortable: true },
    { title: "airplane.registration", property: "registration", sortable: true },
    { title: "airplane.description", property: "description" },
    { title: "actions", property: "name", cell: ActionsColumnComponent }
  ];
  data: [{}];
  searchRequest: SearchRequest = {
    pageRequest: { sort: { orders: [{ field: "name", ascending: true }] }, page: 0, size: 25 },
    filter: { name: "" }
  };
  rows: number;

  filterForm: FormGroup;
  modalForm: FormGroup;

  @ViewChild('test') modal: Modal;

  showModal() {
    this.modal.show();
    //$('#test').modal('show');
  }

  constructor(private fb: FormBuilder, private planeService: PlaneService, private ns: NotificationService) { }

  ngOnInit() {
    this.createForm();
    this.fetchData();
  }

  createForm() {
    this.filterForm = this.fb.group({
      name: ['']
    });
    this.modalForm = this.fb.group({
      sid: [''],
      name: ['', Validators.required],
      registration: ['', Validators.required],
      description: ['']
    });
  }

  fetchData() {
    this.planeService.findPlanes(this.searchRequest).then((response) => {
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

  onSubmit(event: any) {
    this.planeService.save(event, event.value).then((response) => {
      this.ns.success('airplane.successCreated');
      this.modal.dismiss();
    })
  }
}