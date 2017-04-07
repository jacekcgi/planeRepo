import { Component, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder} from '@angular/forms';
import { Column, PageRequest, SearchRequest, Sort } from 'common/table'
import { NotificationService, UserService} from 'app/services';
import { UserActionsColumnComponent } from './user.actions.column.component';

@Component({
  selector: 'page-users',
  templateUrl: './users.component.html',
})
export class UsersComponent {
  columns: [Column] = [
    { title: "user.login", property: "login", sortable: true },
    { title: "user.name", property: "name", sortable: true },
    { title: "user.surname", property: "surname", sortable: true  },
    { title: "actions", cell: UserActionsColumnComponent }
  ];
  data: [{}];
  searchRequest: SearchRequest = {
    pageRequest: { sort: { orders: [{ field: "login", ascending: true }] }, page: 0, size: 25 },
    filter: {login: '', name: ''}
  };
  rows: number;

  filterForm: FormGroup;

  constructor(private fb: FormBuilder, private ns: NotificationService, private userService: UserService) {
      this.userService.refreshTableData$.subscribe(() => {
        this.fetchData();
      })
   }

  ngOnInit() {
    this.fetchData();

    this.filterForm = this.fb.group({
      login: [''],
      name: ['']
    });
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
}