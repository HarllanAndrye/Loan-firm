
import { Component, OnInit } from '@angular/core';

import Swal from 'sweetalert2';
import { RestrictedService } from './../restricted.service';
import { Loan } from './../to/loan.to';

@Component({
  selector: 'app-loan',
  templateUrl: './loan.component.html',
  styleUrls: ['./loan.component.css']
})
export class LoanComponent implements OnInit {

  loan: Loan = {
    loanValue: 0,
    fisrtDateToPayment: new Date(),
    quantityOfPayment: 1
  }

  constructor(private service: RestrictedService) { }

  ngOnInit(): void {
  }

  requestLoan(): void {
    let storage = localStorage.getItem('user');

    if (storage) {
      let email = atob(storage);
      
      this.service.sendRequestLoan(this.loan, email).subscribe({
        next: (resp) => {
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: resp.message,
            showConfirmButton: false,
            timer: 3000
          });

          this.loan = {
            loanValue: 0,
            fisrtDateToPayment: new Date(),
            quantityOfPayment: 1
          };
        },
        error: (e) => {
          let message = e.error.message ? e.error.message : 'Ocorreu um erro! Tente novamente.';

          Swal.fire({
            position: 'center',
            icon: 'error',
            title: message,
            showConfirmButton: false,
            timer: 4000
          });
        }
      });
    }
  }

}
