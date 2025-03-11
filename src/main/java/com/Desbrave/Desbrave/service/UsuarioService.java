package com.Desbrave.Desbrave.service;

import com.Desbrave.Desbrave.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Desbrave.Desbrave.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;




@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public Usuario criar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario usuario){
        if(usuarioRepository.existsById(id)){
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public Boolean deletar(Long id){
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
