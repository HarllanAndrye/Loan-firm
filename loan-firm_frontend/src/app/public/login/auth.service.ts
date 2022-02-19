import { Observable } from 'rxjs';
import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated: EventEmitter<boolean> = new EventEmitter();

  constructor() { }

  setIsAuthenticated(isAuthenticated : boolean): void {
    this.isAuthenticated.next(isAuthenticated);
  }

  getIsAuthenticated(): Observable<boolean> {
    return this.isAuthenticated.asObservable();
  }

}
