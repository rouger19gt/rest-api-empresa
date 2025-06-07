package com.empresa.servicio;

import com.empresa.dto.EmpleadoDTO;
import com.empresa.modelo.Empleado;
import com.empresa.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoServicio {
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    /*
    * mapea de un EmpleadoDTO a Empleado. Esto es para que la entidad no esté expuesta directamente
    * y sea  travé de un dto
    *
    * */
    private Empleado mapearEntidad(EmpleadoDTO empleadoDTO){
        Empleado empleado = new Empleado();
        empleado.setId(empleadoDTO.getId());
        empleado.setNombre(empleadoDTO.getNombre());
        empleado.setApellidos(empleadoDTO.getApellidos());
        empleado.setGenero(empleadoDTO.getGenero());
        empleado.setFechaNacimiento(empleadoDTO.getFechaNacimiento());
        empleado.setNumeroIdentificacion(empleadoDTO.getNumeroIdentificacion());

        return empleado;
    }

    /*
    * En este método hacemos lo mismo, solo que a la inversa, de una entidad al DTO
    *
    * */
    private EmpleadoDTO mapearDTO(Empleado empleado){
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setId(empleado.getId());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setApellidos(empleado.getApellidos());
        empleadoDTO.setGenero(empleado.getGenero());
        empleadoDTO.setFechaNacimiento(empleado.getFechaNacimiento());
        empleadoDTO.setNumeroIdentificacion(empleado.getNumeroIdentificacion());

        return empleadoDTO;
    }

    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleadoDTO){
        Empleado emp = mapearEntidad(empleadoDTO);
        return mapearDTO(empleadoRepositorio.save(emp));
    }

    public List<EmpleadoDTO> listarEmpleados(){
        return empleadoRepositorio.findAll().stream().map(this::mapearDTO).collect(Collectors.toList());
    }

    public EmpleadoDTO actualizarEmpleado(EmpleadoDTO empleadoDTO){
        Empleado empleado = mapearEntidad(empleadoDTO);
        return mapearDTO(empleadoRepositorio.save(empleado));
    }

    public void eliminarEmpleado(Integer id){
        empleadoRepositorio.deleteById(id);
    }
}
