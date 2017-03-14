import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {TranslateModule, TranslateLoader } from 'ng2-translate';
import { CustomLoader } from './translate/translate.loader';

import { DashboardComponent }   from './dashboard/dashboard.component';
import { HomeComponent }  from './home/home.component';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ActionService } from './services';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    TranslateModule.forRoot({
            provide: TranslateLoader,
            useClass: CustomLoader
        })
  ],
  declarations: [
      AppComponent,
      DashboardComponent,
      HomeComponent
  ],
  providers: [ActionService],
  bootstrap: [AppComponent]
})
export class AppModule { }
