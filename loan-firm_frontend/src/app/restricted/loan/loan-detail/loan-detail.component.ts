import { Component, OnInit } from '@angular/core';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import Swal from 'sweetalert2';

import { ModalComponent } from './../../../shared/modal/modal.component';
import { Loan } from './../../to/loan.to';
import { RestrictedService } from './../../restricted.service';

@Component({
  selector: 'app-loan-detail',
  templateUrl: './loan-detail.component.html',
  styleUrls: ['./loan-detail.component.css']
})
export class LoanDetailComponent implements OnInit {

  loans: Loan[] = [];

  constructor(
    private service: RestrictedService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.listLoans();
  }

  listLoans(): void {
    let storage = localStorage.getItem('user');

    if (storage) {
      let email = atob(storage);

      this.service.listLoans(email).subscribe({
        next: (resp) => this.loans = resp,
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

  openModal(loan: any): void {
    if (loan) {
      const modalRef = this.modalService.open(ModalComponent);
      modalRef.componentInstance.loan = loan;
    }
  }

}
