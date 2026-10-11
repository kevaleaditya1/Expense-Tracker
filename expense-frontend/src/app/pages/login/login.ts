import { Component } from '@angular/core';
import{FormsModule} from '@angular/forms';
import {RouterLink} from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class LoginComponent {
  email = '';
  password = '';

  message = '';
  isError = false;
  constructor(private userService: UserService) {}
  onLogin() {
    this.userService
      .login({
        email: this.email,
        password: this.password,
      })
      .subscribe({
        next: (response) => {
          this.message = response.message;
          this.isError = false;

          // Keep the token available for authenticated API requests.
          sessionStorage.setItem('jwtToken', response.token);
        },
        error: (error) => {
          this.message = error.error || 'Login failed';
          this.isError = true;
        },
      });
  }
}

