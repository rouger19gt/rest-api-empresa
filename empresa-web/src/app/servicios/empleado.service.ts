import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {
 //Si se usa una IP, se agrega aquí en vez de localhost
   private baseUrl = 'http://192.168.1.6:8080/rest-empresa'; // Ajusta según tu backend

  constructor(private http: HttpClient) {}

  obtenerEmpleados(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/listar-empleados`);
  }

  crearEmpleado(empleado: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/nuevo-empleado`, empleado, { observe: 'response' });
  }

  actualizarEmpleado(empleado: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/actualizar-empleado`, empleado, { observe: 'response' });
  }

  eliminarEmpleado(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/eliminar-empleado?id=${id}`, { observe: 'response' });
    }

}
