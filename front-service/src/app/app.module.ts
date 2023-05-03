import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // import FormsModule
import { HttpClientModule } from '@angular/common/http'; // import HttpClientModule

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistartionFormComponent } from './registartion-form/registartion-form.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistartionFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, // add FormsModule
    HttpClientModule // add HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
