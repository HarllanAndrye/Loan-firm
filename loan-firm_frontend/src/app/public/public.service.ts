import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from './to/user.to';
import { Login } from './to/login.to';
import { RegisterUser } from './to/register.to';

interface TokenResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class PublicService {

  URL_API: string = 'http://localhost:8081/';

  constructor(private http: HttpClient) { }

  registerUser(register: RegisterUser): Observable<User> {
    return this.http.post<User>(`${this.URL_API}user/register`, register);
  }

  login(loginCredentials: Login): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.URL_API}login`, loginCredentials);
  }

}
