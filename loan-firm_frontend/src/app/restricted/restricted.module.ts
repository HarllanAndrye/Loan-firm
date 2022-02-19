import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeComponent } from './home/home.component';
import { ClientComponent } from './client/client.component';
import { LoanComponent } from './loan/loan.component';
import { LoanDetailComponent } from './loan/loan-detail/loan-detail.component';


@NgModule({
  declarations: [
    LoanComponent,
    ClientComponent,
    HomeComponent,
    LoanDetailComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    HttpClientModule
  ]
})

export class RestrictedModule { }
