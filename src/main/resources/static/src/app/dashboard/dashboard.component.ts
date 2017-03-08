import { Component, OnInit } from '@angular/core';
//var appConfig = require('appconfig');

@Component({
  selector: 'page-dashboard',
  templateUrl: './dashboard.component.html',
})
export class DashboardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
//    alert('App Port:' + appConfig.serverPort + "\n App context: " + appConfig.serverContext )
  }
}
