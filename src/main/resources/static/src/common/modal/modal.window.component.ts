import { Component, Input, Output, EventEmitter, ContentChildren, QueryList } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { InputComponent } from 'common/input'


@Component({
    selector: 'ap-modal',
    template: `
        <div class="modal fade" [id]="uid" role="dialog" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                     <ap-form (onSubmit)="submit($event)">
                        <div class="modal-header">
                            <h5 class="modal-title">{{title | translate}}</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <ng-content></ng-content>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">{{saveProperty | translate}}</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">{{closeProperty | translate}}</button>
                        </div>
                    </ap-form>
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

    @Output() onSubmit: EventEmitter<any> = new EventEmitter();

    @ContentChildren(InputComponent) contents : QueryList<InputComponent>; 

    constructor(private fb: FormBuilder) {

    }

    ngOnInit() {

    }

    ngAfterContentInit() {
        // console.log(this.contents);
    }

    submit(event: any) {
        // this.onSubmit.emit(this.formGroup.value);
        console.log(event);
        // $('#' + this.uid).modal('hide');
    }

    show() {
        $('#' + this.uid).modal('show');
    }

    dismiss() {
         $('#' + this.uid).modal('hide');
    }

    toggle() {
        $('#' + this.uid).modal('toggle');
    }


}