import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import Swal from 'sweetalert2';

import { Login } from './../to/login.to';
import { PublicService } from './../public.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginCredentials: Login = {
    email: '',
    password: ''
  };

  constructor(
    private route: Router,
    private service: PublicService
  ) { }

  ngOnInit(): void {
  }

  login(): void {
    this.service.login(this.loginCredentials).subscribe({
      next: (resp) => {
        localStorage.setItem('idtoken', resp.token);
        localStorage.setItem('user', btoa(this.loginCredentials.email));
        this.goTo('user/home');
      },
      error: (e) => {
        if (e.status == 403) {
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'E-mail e/ou Senha inválido(s)! Tente novamente.',
            showConfirmButton: false,
            timer: 4000
          });
        } else {
          let message = e.error.message ? e.error.message : 'Ocorreu um erro! Tente novamente.';
          Swal.fire({
            position: 'center',
            icon: 'error',
            title: message,
            showConfirmButton: false,
            timer: 3000
          });
        }
      }
    });
  }

  goTo = (rota: string) => {
    this.route.navigate([rota]);
  }

}
