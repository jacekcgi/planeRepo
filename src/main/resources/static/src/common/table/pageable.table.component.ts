import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Column, PageRequest, SearchRequest, Sort } from 'common/table';

@Component({
  selector: 'ap-pageabletable',
  template: `
    <ap-table [columns]="columns" [data]="data" [sort]="pageRequest.sort" (onSort)="onSort($event)"></ap-table>
    
    <!-- fixme: replace with some component mby? -->
    <div class="container">      
        <div class="dropup pull-left">
            <button class="btn btn-sm btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                {{pageRequest.size}}
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li *ngFor="let size of pageSizes" class="dropdown-item" (click)="onSize(size)">{{size}}</li>
            </ul>
        </div>
        
        <div class="pull-right">
            <ap-pagination [rows]="rows" [size]="pageRequest.size" [current]="pageRequest.page" (onPage)="onPage($event)"></ap-pagination>
        </div>
    </div>
  `
})
export class PageableTable {
    @Input() columns: Array<Column>;
    @Input() data: Array<any>;
    @Input() pageRequest: PageRequest;
    @Input() rows: number;
    @Input() pageSizes: Array<number> = [5, 10, 25, 50];

    @Output() onChange: EventEmitter<PageRequest> = new EventEmitter();

    ngOnInit() {
        let index = this.pageSizes.findIndex((value) => {return this.pageRequest.size === value});
        if (index < 0) {
            console.warn('Size of page have to be one of defined sizes');
            this.pageRequest.size = this.pageSizes[0];
        }
    }  

    onSort(sort: Sort) {
      this.pageRequest.sort = sort;
      this.pageRequest = this.pageRequest;
      this.onChange.emit(this.pageRequest);
    }

    onPage(selection: number) {
        this.pageRequest.page = selection;
        this.onChange.emit(this.pageRequest);
    }

    onSize(size: number) {
        this.pageRequest.size = size;
        this.onChange.emit(this.pageRequest);
    }
}