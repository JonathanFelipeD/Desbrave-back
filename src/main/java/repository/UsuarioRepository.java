package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    
    
}
