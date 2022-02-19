import { Injectable, NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from 'rxjs';

import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

   constructor() {} 

   intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      var idtoken = localStorage.getItem('idtoken');

      if (idtoken) {
         const dupReq = req.clone({
            headers: req.headers.set('Authorization', `Bearer ${idtoken}`)
         });

         return next.handle(dupReq);
      } else {
         return next.handle(req);
      }
   }

}

@NgModule({
   providers: [{
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
   }]
})

export class Interceptor { }
