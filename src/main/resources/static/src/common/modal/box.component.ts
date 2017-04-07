import { Component, ViewChild } from '@angular/core';
import { BoxService } from 'common/modal/box.service';
import { TranslationService } from 'app/services';

@Component({
  selector: 'ap-box',
  template: `
    <div class="modal fade" [id]="uid">
        <div class="modal-dialog modal-sm" role="document">
            <div class="card card-inverse text-center" style="background-color: #333; border-color: #333;">
                <div class="card-block">
                    <p>{{message}}</p>
                    <button #_acceptBtn type="button" class="btn btn-sm btn-secondary pull-right">{{acceptButton | translate}}</button>
                    <button #_closeBtn type="button" class="btn btn-sm btn-secondary pull-left">{{closeButton | translate}}</button>
                </div>       
            </div>
        </div>
    </div>
  `,
})
export class BoxComponent{
    message: string = "";
    uid: string = Math.random().toString(36).substring(7);
    acceptButton: string = "acceptButton";
    closeButton: string = "closeButton";

    @ViewChild('_acceptBtn') 
    private _acceptBtn:any;
    @ViewChild('_closeBtn') 
    private _closeBtn:any;

    constructor(confirmService:BoxService, private translationService: TranslationService) {
        confirmService.prompt = this.prompt.bind(this);
    }

    prompt(message:string, accept?:string, close?: string) {
        this.message = this.translationService.get(message);
        console.log(this.message);
        this.acceptButton = accept ? accept : this.acceptButton;
        this.closeButton = close ? close : this.closeButton;

        let promise = new Promise<boolean>(resolve => {
            this._show(resolve);
        });
        return promise;
    }

    _show(resolve:(value: boolean) => any) {
        $('#' + this.uid).modal('show');
        
        let negativeOnClick = (e:any) => resolve(false);
        let positiveOnClick = (e:any) => resolve(true);

        this._closeBtn.nativeElement.onclick = (e:any) => {
            e.preventDefault();
            negativeOnClick(e);
            this._hide();
        };

        this._acceptBtn.nativeElement.onclick = (e:any) => {
            e.preventDefault();
            positiveOnClick(e);
            this._hide();
        };
    }

    _hide() {
         $('#' + this.uid).modal('hide');
    }
    
}


