import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  loginSuccess: boolean | null = null;

  constructor(private loginService : LoginService) {}

  onLogin(): void {
    this.loginService.login(this.username, this.password).subscribe(
      (success) => {
        this.loginSuccess = success;
      },
      (error) => {
        console.error('Login error:', error);
        this.loginSuccess = false;
      }
    );
  }
}

