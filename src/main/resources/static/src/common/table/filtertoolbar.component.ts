import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'ap-filtertoolbar',
  template: `
    <div class="card-block">
        <form [formGroup]="formGroup" (ngSubmit)="onSubmit()" class="form-inline">
            <ng-content></ng-content>

            <div class="pull-right">
                <button type="submit" [disabled]="!formGroup.valid" class="btn btn-sm btn-primary">{{ 'searchButton' | translate }}</button>
            </div>
        </form>
    </div>
  `
})
export class FilterToolbar {
    @Input() formGroup: FormGroup;
    @Output() onFilter: EventEmitter<any> = new EventEmitter();
    // uid :string = Math.random().toString(16).slice(2);

    onSubmit() {
        this.onFilter.emit(this.formGroup.value);
    }
}