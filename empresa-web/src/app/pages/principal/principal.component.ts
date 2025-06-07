import { Component, OnInit } from '@angular/core';
import { EmpleadoService } from '../../servicios/empleado.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 

@Component({
  selector: 'app-principal',
  imports: [CommonModule, FormsModule],
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css'
})
export class PrincipalComponent implements OnInit{
    empleados: any[] = [];

  // Modelo para el nuevo empleado
  nuevoEmpleado = {
    nombre: '',
    apellidos: '',
    genero: '',
    fechaNacimiento: '',
    numeroIdentificacion: ''
  };

  constructor(private empleadoService: EmpleadoService) {}

  ngOnInit(): void {
    this.cargarEmpleados();
  }

  cargarEmpleados() {
    this.empleadoService.obtenerEmpleados().subscribe({
      next: (data) => this.empleados = data,
      error: (err) => console.error('Error al obtener empleados:', err)
    });
  }

  agregarEmpleado() {
    // Validación simple (puedes mejorarla)
    if (!this.nuevoEmpleado.nombre || !this.nuevoEmpleado.numeroIdentificacion) {
      alert('Nombre y Número de Identificación son obligatorios');
      return;
    }

    this.empleadoService.crearEmpleado(this.nuevoEmpleado).subscribe({
      next: () => {
        alert('Empleado creado con éxito');
        this.nuevoEmpleado = { nombre: '', apellidos: '', genero: '', fechaNacimiento: '', numeroIdentificacion: '' }; // limpiar formulario
        this.cargarEmpleados(); // refrescar lista
      },
      error: (err) => {
        console.error('Error al crear empleado:', err);
        alert('Error al crear empleado');
      }
    });
  }

  empleadoActualizar = {
  id: 0,
  nombre: '',
  apellidos: '',
  genero: '',
  fechaNacimiento: '',
  numeroIdentificacion: ''
};

actualizarEmpleado() {
  if (!this.empleadoActualizar.id) {
    alert('Debe indicar el ID del empleado a actualizar');
    return;
  }

  this.empleadoService.actualizarEmpleado(this.empleadoActualizar).subscribe({
    next: () => {
      alert('Empleado actualizado con éxito');
      this.empleadoActualizar = { id: 0, nombre: '', apellidos: '', genero: '', fechaNacimiento: '', numeroIdentificacion: '' };
      this.cargarEmpleados();
    },
    error: (err) => {
      console.error('Error al actualizar empleado:', err);
      alert('Error al actualizar empleado');
    }
    });
  }

  eliminarEmpleado(id: number) {
  if(confirm('¿Está seguro que desea eliminar este empleado?')) {
    this.empleadoService.eliminarEmpleado(id).subscribe({
      next: () => {
        alert('Empleado eliminado con éxito');
        this.cargarEmpleados(); // recarga lista
      },
      error: (err) => {
        console.error('Error al eliminar empleado:', err);
        alert('No se pudo eliminar el empleado');
      }
    });
  }
}

}
