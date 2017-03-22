import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Column, Sort, Order } from 'common/table';

@Component({
  selector: 'ap-table',
  template: `
    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th *ngFor="let column of columns" (click)="sorting(column)">
                    {{column.title}}
                    <i *ngIf="column.sortable" [class]="sortClass(column)"></i>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr *ngFor="let item of data">
                <td *ngFor="let column of columns">{{valueOf(item, column.property)}}</td>
            </tr>
        </tbody>
    </table>
  `,
  host: {
    '(document:keydown)': 'onDocumentKeyDown($event)',
    '(document:keyup)': 'onDocumentKeyUp($event)'
  }
})
export class Table {
    @Input() columns: Array<Column>;
    @Input() data: Array<any>;
    @Input() sort: Sort;

    @Output() onSort: EventEmitter<Sort> = new EventEmitter();

    multiOrder: boolean = false;

    onDocumentKeyDown(event: KeyboardEvent) {
      let key = event.which || event.keyCode;
      if (key == 16) { //shift key
        event.preventDefault();
        this.multiOrder = true;
      }
    }

    onDocumentKeyUp(event: KeyboardEvent){
      let key = event.which || event.keyCode;
      if (key == 16) { //shift key
        event.preventDefault();
        this.multiOrder = false;
      }
    }

    sorting(column: Column) {
        if (column.sortable) {
            let property = column.property;
            let orders = this.sort.orders;
            let newOrders: Array<Order> = [];
            
            let foundColumn = false;
            let newOrder: Order = null;
            
            for (let order of orders) {
                if (order.field == property) {
                    newOrder = {field: property, ascending: !order.ascending};
                    foundColumn = true;
                    newOrders.push(newOrder);
                } else if (this.multiOrder) {
                   newOrders.push(order);
                }
            }
            
            if (!foundColumn) {
                newOrder = {field: property, ascending: true};
                newOrders.push(newOrder);
            }
            this.sort.orders = newOrders;
            this.onSort.emit(this.sort)
        }
    }

    valueOf(item: object, columnProperty : string) {
        return item[columnProperty];
    }

    sortClass(column: Column) {
        let orders = this.sort.orders;
        let clazz = '';
        let isUse: boolean = false;
        if (column.sortable) {
            clazz += 'fa fa-sort';
            if (orders != null && orders != undefined && orders.length > 0) {
                for (let order of orders)
                {                 
                    if (order.field == column.property) {
                        clazz += order.ascending ? "-asc" : "-desc";
                        isUse = true;
                    }                   
                }
            } 
            if (!isUse) {
                clazz = 'text-grey ' + clazz;
            }
        }
        return clazz;
    }
}

