package com.empresa.controlador;

import com.empresa.dto.EmpleadoDTO;
import com.empresa.servicio.EmpleadoServicio;
import com.empresa.servicio.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.*;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("rest-empresa")
@CrossOrigin
public class RestEmpresa {
    private static final Logger logger = LoggerFactory.getLogger(RestEmpresa.class);
    @Autowired private UsuarioServicio usuarioServicio;
    @Autowired private EmpleadoServicio empleadoServicio;

    @GetMapping("ping")
    public String ping(){
        return "Hola mundo";
    }

    /*
    * Método de autenticación. Se envía a través de un json los datos y se hacen las validaciones
    *
    * */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody String data){
        try{
            JsonReader jsonReader = Json.createReader(new StringReader(data));
            JsonObject jsonObject = jsonReader.readObject();

            String username = jsonObject.getString("usuario");
            String password = jsonObject.getString("password");
            usuarioServicio.obtenerAutenticacion(username, password);

            System.out.println(username);
            return ResponseEntity.status(HttpStatus.OK).body(username);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/nuevo-empleado")
    public ResponseEntity nuevoEmpleado(@RequestBody String data){
        try{
            JsonReader jsonReader = Json.createReader(new StringReader(data));
            JsonObject jsonObject = jsonReader.readObject();

            EmpleadoDTO empleadoDTO = new EmpleadoDTO();
            empleadoDTO.setNombre(jsonObject.getString("nombre"));
            empleadoDTO.setApellidos(jsonObject.getString("apellidos"));
            empleadoDTO.setGenero(jsonObject.getString("genero"));
            String fechaNacimiento = jsonObject.getString("fechaNacimiento");
            empleadoDTO.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
            empleadoDTO.setNumeroIdentificacion(jsonObject.getString("numeroIdentificacion"));

            empleadoServicio.crearEmpleado(empleadoDTO);

            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error, no se pudo crear el empleado");
        }
    }

    @PutMapping("/actualizar-empleado")
    public ResponseEntity actualizarEmpleado(@RequestBody String data){
        try{
            JsonReader jsonReader = Json.createReader(new StringReader(data));
            JsonObject jsonObject = jsonReader.readObject();

            EmpleadoDTO empleadoDTO = new EmpleadoDTO();
            empleadoDTO.setId(jsonObject.getInt("id"));
            empleadoDTO.setNombre(jsonObject.getString("nombre"));
            empleadoDTO.setApellidos(jsonObject.getString("apellidos"));
            empleadoDTO.setGenero(jsonObject.getString("genero"));
            String fechaNacimiento = jsonObject.getString("fechaNacimiento");
            empleadoDTO.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
            empleadoDTO.setNumeroIdentificacion(jsonObject.getString("numeroIdentificacion"));

            empleadoServicio.actualizarEmpleado(empleadoDTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error, no se pudo actualizar el empleado")   ;
        }
    }

    @DeleteMapping("/eliminar-empleado")
    public ResponseEntity eliminarEmpleado(@RequestParam Integer id){
        try{
            empleadoServicio.eliminarEmpleado(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error, no se pudo eliminar el empleado");
        }
    }

    @GetMapping("/listar-empleados")
    public List<EmpleadoDTO> listarEmpleados(){
        return empleadoServicio.listarEmpleados();
    }
}
