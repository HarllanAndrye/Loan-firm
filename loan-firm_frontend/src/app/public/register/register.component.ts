
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { PublicService } from './../public.service';
import { RegisterUser } from './../to/register.to';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerUser: RegisterUser = {
    name: '',
    cpf: 0,
    email: '',
    password: '',
    income: 0
  };

  passwordCheck: string = '';

  constructor(
   private route: Router,
   private service: PublicService) { }

  ngOnInit(): void {
  }

  register() {
    if (this.registerUser.password === this.passwordCheck) {
      this.service.registerUser(this.registerUser).subscribe({
        next: (resp) => {
          localStorage.setItem('idtoken', resp.token);
          localStorage.setItem('user', btoa(resp.email));
          this.goTo('user/home');
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
    } else {
      Swal.fire({
        position: 'center',
        icon: 'info',
        title: 'Senhas divergentes! Verifique novamente.',
        showConfirmButton: false,
        timer: 3000
      });
    }
  }

  goTo = (rota: string) => {
    this.route.navigate([rota]);
  }

}
