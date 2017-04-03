import { Component, Input, Output, EventEmitter, ContentChildren, QueryList } from '@angular/core';
import { FormGroup, FormBuilder, AbstractControl } from '@angular/forms';


@Component({
    selector: 'ap-modal',
    template: `
        <div class="modal fade" [id]="uid" role="dialog" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                     <form [formGroup]="formGroup" (ngSubmit)="submit($event)">
                        <div class="modal-header">
                            <h5 class="modal-title">{{title | translate}}</h5>
                            <button type="button" class="close" data-dismiss="modal">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <ng-content></ng-content>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" [disabled]="!formGroup.valid"  class="btn btn-primary">{{saveProperty | translate}}</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">{{closeProperty | translate}}</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    `,
})
export class Modal {
    @Input() uid: string;
    @Input() title: string;
    @Input() saveProperty: string = "saveButton";
    @Input() closeProperty: string = "closeButton";
    @Input() formGroup: FormGroup = null;

    @Output() onSubmit: EventEmitter<FormGroup> = new EventEmitter(false);

    constructor(private fb: FormBuilder) {

    }

    ngOnInit() {
        if (this.formGroup == null) {
            this.formGroup = this.fb.group({});
        }
    }

    submit(event: any) {
        if (!this.formGroup.valid) {
            this.forceValid(this.formGroup.controls);
        }
        if (this.formGroup.valid) {
            this.onSubmit.emit(this.formGroup);
        }
    }

    forceValid(controls: {[key: string]: AbstractControl}) {
        _.forEach(controls, (value:AbstractControl, key: string) => {
            if (value instanceof FormGroup) {
                this.forceValid(value.controls);
            } else {
                value.markAsTouched();
            }
        });
    }

    show() {
        this.formGroup.reset();
        $('#' + this.uid).modal('show');
    }

    dismiss() {
         $('#' + this.uid).modal('hide');
    }

    toggle() {
        $('#' + this.uid).modal('toggle');
    }
}