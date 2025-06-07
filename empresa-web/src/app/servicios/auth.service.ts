import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  //Si se usa una IP, se agrega aquí en vez de localhost
  private apiUrl = 'http://localhost:8080/rest-empresa/login'; // Asegurate que el back esté corriendo

  constructor(private http: HttpClient) {}

  login(data: { usuario: string; password: string }): Observable<any> {
    return this.http.post(this.apiUrl, data, { observe: 'response', responseType: 'text' as 'json' });
  }
}
