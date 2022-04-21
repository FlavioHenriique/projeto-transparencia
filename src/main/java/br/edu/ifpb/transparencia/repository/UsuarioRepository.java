package br.edu.ifpb.transparencia.repository;

import br.edu.ifpb.transparencia.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    public Optional<Usuario> findByEmail(String email);
}
