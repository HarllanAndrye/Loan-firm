import { AuthService } from './../login/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  isAuthenticated: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.authService.getIsAuthenticated().subscribe({
      next: resp => this.isAuthenticated = resp
    });
  }

  logout(): void {
    localStorage.clear();
    this.authService.setIsAuthenticated(false);
    this.router.navigate(['/signin']);
  }

}
