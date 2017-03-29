import { Type } from '@angular/core';
import { DefaultCellComponent } from './default.cell.component';

export * from './filtertoolbar.component';
export * from './pagination.component';
export * from './table.component';
export * from './pageable.table.component';
export * from './cell.component';
export * from './default.cell.component';
export * from './date.cell.component'

export interface TableConfig {
    limit : number,
    columns: Array<Column>
}

export interface Column {
    title: string,
    property?: string,
    sortable?: boolean,
    cell?: Type<DefaultCellComponent>
}

export interface Order {
    field: string,
    ascending: boolean
}

export interface Sort {
    orders: Array<Order>
}

export interface PageRequest {
    sort: Sort,
    page: number,
    size: number
}

export interface SearchRequest {   
    pageRequest: PageRequest,
    filter: object
}