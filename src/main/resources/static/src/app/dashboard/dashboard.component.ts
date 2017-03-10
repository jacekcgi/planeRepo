import { Component, OnInit } from '@angular/core';
import  { AppConfig } from 'config';

@Component({
  selector: 'page-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    console.log(AppConfig);
  }
}
