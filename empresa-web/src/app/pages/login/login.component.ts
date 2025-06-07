import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms'; 
import { AuthService } from '../../servicios/auth.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-login',
  standalone: true, 
  imports: [FormsModule, CommonModule], 
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  usuario: string = '';
  password: string = '';
  error: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ingresar() {
    this.authService.login({ usuario: this.usuario, password: this.password }).subscribe({
      next: (res) => {
        this.router.navigate(['/principal']);
      },
      error: (err) => {
        this.error = 'Credenciales incorrectas';
      }
    });
  }
}
