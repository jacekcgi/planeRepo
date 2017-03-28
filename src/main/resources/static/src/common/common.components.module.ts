import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule, TranslateLoader } from 'ng2-translate';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CustomLoader } from 'app/translate/translate.loader';

import { InputComponent } from 'common/input';
import { Table, PageableTable, Pagination, FilterToolbar, CellComponent, DefaultCellComponent, DateCellComponent } from 'common/table';
import { ErrorMessagesComponent } from 'common/validations';
import { LanguageComponent } from 'common/languages'

import { NotificationService } from 'app/services';

import { FormatTimePipe } from 'common/pipe';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        TranslateModule.forRoot({
            provide: TranslateLoader,
            useClass: CustomLoader
        })
    ],
    declarations: [
        InputComponent,
        ErrorMessagesComponent,
        Table,
        PageableTable,
        Pagination,
        FilterToolbar,
        CellComponent,
        DefaultCellComponent,
        DateCellComponent,
        LanguageComponent,
        FormatTimePipe
    ],
    entryComponents: [DefaultCellComponent, DateCellComponent],
    providers: [

    ],
    exports: [
        InputComponent,
        ErrorMessagesComponent,
        Table,
        PageableTable,
        Pagination,
        FilterToolbar,
        CellComponent,
        DefaultCellComponent,
        DateCellComponent,
        LanguageComponent,
        FormatTimePipe
    ]
})
export class CommonComponentsModule {
    // public static forRoot(): ModuleWithProviders {
    //     return {
    //         ngModule: CommonComponentsModule,
    //         providers: [
    //             NotificationService
    //         ]
    //     };
    // }
}
