import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Column, Sort, Order } from 'common/table';

const SHIFT_KEY_CODE = 16;

@Component({
  selector: 'ap-table',
  template: `
    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th *ngFor="let column of columns" (click)="sorting(column)">
                    {{column.title | translate}}
                    <i *ngIf="column.sortable" [class]="sortClass(column)"></i>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr *ngFor="let item of data">
                <td *ngFor="let column of columns"><ap-cell [item]="item" [column]="column"></ap-cell></td>
            </tr>
        </tbody>
    </table>
  `,
  host: {
    '(document:keydown)': 'onDocumentKey($event, true)',
    '(document:keyup)': 'onDocumentKey($event, false)'
  }
})
export class Table {
    @Input() columns: Array<Column>;
    @Input() data: Array<any>;
    @Input() sort: Sort;

    @Output() onSort: EventEmitter<Sort> = new EventEmitter();

    multiOrder: boolean = false;

    onDocumentKey(event: KeyboardEvent, keydown: boolean) {
      let key = event.which || event.keyCode;
      if (key == SHIFT_KEY_CODE) {
        event.preventDefault();
        this.multiOrder = keydown;
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

