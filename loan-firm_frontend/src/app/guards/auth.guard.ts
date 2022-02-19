import { AuthService } from './../public/login/auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateChild, Router, RouterStateSnapshot } from '@angular/router';
import { catchError, map, Observable, of } from 'rxjs';

@Injectable()
export class AuthGuard implements CanActivateChild {

  constructor(
    private authService: AuthService,
    private router: Router,
    private http: HttpClient
  ) { }

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

    return this.verifyAuthorization().pipe(
      map(e => {
        if (e) {
          this.authService.setIsAuthenticated(true);
          return true;
        } else {
          this.authService.setIsAuthenticated(false);
          return false;
        }
      }),
      catchError((err) => {
        this.authService.setIsAuthenticated(false);
        this.router.navigate(['/signin']);
        return of(false);
      })
    );

  }

  private verifyAuthorization(): Observable<boolean> {
    return this.http.get<boolean>('http://localhost:8081/user/authorized').pipe(
      map(resp => resp)
    );
  }

}
