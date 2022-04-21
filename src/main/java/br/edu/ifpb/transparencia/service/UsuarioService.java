package br.edu.ifpb.transparencia.service;

import br.edu.ifpb.transparencia.model.Usuario;
import br.edu.ifpb.transparencia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) throws ValidationException {
        try {
            findByEmail(usuario.getEmail());
        }catch (NoSuchElementException e){
            // Se não achar, é porque o e-mail está livre
            usuario = repository.save(usuario);
            return usuario;
        }
        throw new ValidationException("E-mail já utilizado");
    }

    public Usuario findByEmail(String email){
        Optional<Usuario> optional = repository.findByEmail(email);
        return optional.get();
    }
}
