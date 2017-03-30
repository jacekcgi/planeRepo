import { Component, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder} from '@angular/forms';
import { Column, PageRequest, SearchRequest, Sort } from 'common/table'
import { NotificationService, UserService} from 'app/services';
//import { ActionsColumnComponent } from './actions.column.component';

@Component({
  selector: 'page-users',
  templateUrl: './users.component.html',
})
export class UsersComponent {
  columns: [Column] = [
    { title: "user.login", property: "login", sortable: true },
    { title: "user.name", property: "name", sortable: true },
    { title: "user.surname", property: "surname", sortable: true  },
    //{ title: "actions", cell: ActionsColumnComponent }
  ];
  data: [{}];
  searchRequest: SearchRequest = {
    pageRequest: { sort: { orders: [{ field: "name", ascending: true }] }, page: 0, size: 25 },
    filter: {}
  };
  rows: number;

  // filterForm: FormGroup;

  constructor(private fb: FormBuilder, private ns: NotificationService, private userService: UserService) { }

  ngOnInit() {
    this.fetchData();
  }


  fetchData() {
    this.userService.findUsers(this.searchRequest).then((response) => {
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
    // this.planeService.save(event, event.value).then((response) => {
    //   this.ns.success('airplane.successCreated');
    //   this.modal.dismiss();
    // })
  }
}