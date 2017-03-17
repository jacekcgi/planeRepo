import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-table',
  template: `
    <div class="container">
        FilterToolbar
    </div>
    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th *ngFor="let column of columns" (click)="onSort(column)">
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
    <div class="container">
        Select
        <ul class="pagination pull-right">
            <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                        <span class="sr-only">Previous</span>
                </a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
    </div>
  `,
})
export class Table {
    @Input() columns: [Column];
    @Input() data: [{}];
    @Input() pagingRequest: PagingRequest;

    onSort(column: Column) {
        if (column.sortable) {
            let property = column.property;
            let sfc = this.pagingRequest.sortFilterChain;
            if (sfc.field == property) {
                sfc.ascending = !sfc.ascending;
            } else {
                sfc.ascending = true;
                sfc.field = property;
            }
        }
    }

    onPage(page: number) {

    }

    onLimit(limit: number) {

    }

    valueOf(item: object, columnProperty : string) {
        return item[columnProperty];
    }

    sortClass(column: Column) {
        let sortFilterChain = this.pagingRequest.sortFilterChain;
        let clazz = '';
        if (column.sortable && column.sortable == true) {
            clazz += 'fa fa-sort'
            if (sortFilterChain && sortFilterChain.field == column.property)
            {
                clazz += sortFilterChain.ascending ? "-asc" : "-desc";
            } else {
                clazz = 'text-grey ' + clazz;
            }
        }
        return clazz;
    }
}

export interface TableConfig {
    limit : number,
    columns: [Column]
}

export interface Column {
    title: string,
    property: string,
    sortable?: boolean,
}

export interface SortFilterChain {
    field: string,
    ascending: boolean
}

export interface PagingRequest {
    sortFilterChain: SortFilterChain,
    offset: number,
    limit: number
}

export interface SearchRequest {   
    pagingRequest: PagingRequest,
    filter: object
}