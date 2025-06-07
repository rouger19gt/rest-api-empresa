package com.empresa.servicio;

import com.empresa.dto.UsuarioDTO;
import com.empresa.excepciones.RecursoSinEncontrarException;
import com.empresa.modelo.Usuario;
import com.empresa.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    /*
     * mapea de un UsuarioDTO a Empleado. Esto es para que la entidad no esté expuesta directamente
     * y sea  travé de un dto
     *
     * */
    private Usuario mapearEntidad(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioDTO.getUsuario());
        usuario.setPassword(usuarioDTO.getPassword());

        return  usuario;
    }

    /*
     * En este método hacemos lo mismo, solo que a la inversa, de una entidad al DTO
     *
     * */
    private UsuarioDTO mapearDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuario(usuario.getUsuario());
        usuarioDTO.setPassword(usuario.getPassword());

        return  usuarioDTO;
    }

    public UsuarioDTO obtenerUsuario(String username){
        Usuario usuario = usuarioRepositorio.findById(username)
                .orElseThrow(() -> new RecursoSinEncontrarException("Usuario no existe"));
        return  mapearDTO(usuario);
    }

    /*
    * Obtenemos el usuario primero y comparamos si la contraseñas enviadas coincide con la obtenida
    *
    * */
    public UsuarioDTO obtenerAutenticacion(String username, String password) throws  Exception{
        UsuarioDTO usuarioDTO = obtenerUsuario(username);
        if(!usuarioDTO.getPassword().equals(password))throw  new Exception("Credencial incorrecta");

        return usuarioDTO;
    }

}
