import { NgModule, LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { registerLocaleData } from '@angular/common';
import localePt from '@angular/common/locales/pt';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AuthGuard } from './guards/auth.guard';
import { Interceptor } from './shared/interceptor/token-interceptor.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PublicModule } from './public/public.module';
import { RestrictedModule } from './restricted/restricted.module';
import { ModalComponent } from './shared/modal/modal.component';
import { AuthService } from './public/login/auth.service';

registerLocaleData(localePt);

@NgModule({
  declarations: [
    AppComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PublicModule,
    RestrictedModule,
    NgbModule,
    Interceptor
  ],
  providers: [
    AuthGuard, 
    AuthService,
    {provide: LOCALE_ID, useValue: 'pt-BR'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
