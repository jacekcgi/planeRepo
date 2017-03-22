import { Component, Input } from '@angular/core';
import { Column } from 'common/table'

@Component({
    template: `
    <span>{{getValue()}}</span>
  `,
})
export class DefaultCellComponent {
    @Input() item: any;
    @Input() column: Column;

    getValue() {
        return this.column.property ? this.item[this.column.property] : null;
    }
}