import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Loan } from './to/loan.to';
import { Client } from './to/client.to';

interface MessageResponse {
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class RestrictedService {

  URL_API: string = 'http://localhost:8081/';

  constructor(private http: HttpClient) { }

  sendRequestLoan(loan: Loan, email: string): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(`${this.URL_API}loan/${email}`, loan);
  }

  listLoans(email: string): Observable<Array<Loan>> {
    return this.http.get<Array<Loan>>(`${this.URL_API}loan/client/${email}`);
  }

  getClientData(email: string): Observable<Client> {
    return this.http.get<Client>(`${this.URL_API}client/${email}`);
  }

  cancelLoan(email: string, loanCode: number): Observable<MessageResponse> {
    return this.http.put<MessageResponse>(`${this.URL_API}loan/cancel/${email}/${loanCode}`, {});
  }

}
