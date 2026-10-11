import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class RegisterComponent {
  name = '';
  email = '';
  password = '';
  confirmPassword = '';

  message = '';
  isError = false;

  constructor(private userService: UserService) {}

  onRegister() {
    if (this.password !== this.confirmPassword) {
      this.message = 'Passwords do not match';
      this.isError = true;
      return;
    }

    const user = {
      name: this.name,
      email: this.email,
      password: this.password,
    };

    this.userService.register(user).subscribe({
      next: (response) => {
        this.message = response;
        this.isError = false;
      },
      error: (error) => {
        this.message = error.error || 'Registration failed. Check the backend.';
        this.isError = true;
        console.error('Registration error:', error);
      },
    });
  }
}
