import { AuthGuard } from './guards/auth.guard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './public/register/register.component';
import { LoginComponent } from './public/login/login.component';
import { LoanDetailComponent } from './restricted/loan/loan-detail/loan-detail.component';
import { LoanComponent } from './restricted/loan/loan.component';
import { HomeComponent } from './restricted/home/home.component';
import { NotFoundComponent } from './public/not-found/not-found.component';

const routes: Routes = [
  { path: 'signin', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user', canActivateChild: [AuthGuard] , children: 
    [
      { path: 'home', component: HomeComponent },
      { path: 'loan', component: LoanComponent },
      { path: 'loan/detail', component: LoanDetailComponent }
    ]
  },
  { path: '', redirectTo: 'signin', pathMatch: 'full' },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
