import { Component, Input } from '@angular/core';
import { Column, DefaultCellComponent } from 'common/table'

@Component({
    template: `
        <span>{{getValue() | date:'shortDate' }}</span>
    `
})
export class DateCellComponent extends DefaultCellComponent {

}