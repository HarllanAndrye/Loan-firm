import { Router } from '@angular/router';
import { Component, OnInit, Input } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import Swal from 'sweetalert2';

import { RestrictedService } from './../../restricted/restricted.service';
import { Loan } from './../../restricted/to/loan.to';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {

  @Input() loan: Loan = {
    loanValue: 0,
    fisrtDateToPayment: new Date(),
    quantityOfPayment: 0
  };

  email: string = '';
  income: number = 0;

  constructor(
    public activeModal: NgbActiveModal,
    private service: RestrictedService,
    private router: Router) { }

  ngOnInit(): void {
    let storage = localStorage.getItem('user');

    if (storage) {
      this.email = atob(storage);

      this.service.getClientData(this.email).subscribe({
        next: (resp) => this.income = resp.income,
        error: (e) => {
          let message = e.error.message ? e.error.message : 'Ocorreu um erro! Tente novamente.';
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: message,
            showConfirmButton: false,
            timer: 3000
          });
        }
      });
    }
  }

  cancelLoan(loanCode: any): void {
    this.service.cancelLoan(this.email, loanCode).subscribe({
      next: (resp) => {
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: resp.message,
          showConfirmButton: false,
          timer: 3000
        });

        this.router.navigate(['user/loan/detail']).then(() => {
          window.location.reload();
        });
      },
        error: (e) => {
          let message = e.error.message ? e.error.message : 'Ocorreu um erro! Tente novamente.';
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: message,
            showConfirmButton: false,
            timer: 3000
          });
        }
    });
  }

}
