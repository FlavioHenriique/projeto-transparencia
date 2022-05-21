package br.edu.ifpb.transparencia.service;

import br.edu.ifpb.transparencia.model.Usuario;
import br.edu.ifpb.transparencia.repository.UsuarioRepository;
import br.edu.ifpb.transparencia.util.Md5Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private static final String EMAIL_UTILIZADO = "E-mail já utilizado";
    private static String EMAIL_NAO_ENCONTRADO = "Usuário não encontrado com este e-mail";
    private static final String NAO_AUTENTICADO = "E-mail ou senha inválidos";

    public Usuario salvar(Usuario usuario) throws ValidationException {
        try {
            usuario.setSenha(Md5Converter.convertToMd5(usuario.getSenha()));
            findByEmail(usuario.getEmail());
        }catch (NoSuchElementException e){
            // Se não achar, é porque o e-mail está livre
            usuario = repository.save(usuario);
            return usuario;
        }
        throw new ValidationException(EMAIL_UTILIZADO);
    }

    public Usuario findByEmail(String email){
        Optional<Usuario> optional = repository.findByEmail(email);
        return optional.get();
    }

    public void delete(Usuario usuario) throws ValidationException {
        Usuario findByEmail = null;
        try {
             findByEmail = findByEmail(usuario.getEmail());
        }catch (Exception e){
            throw new ValidationException(EMAIL_NAO_ENCONTRADO);
        }
        repository.delete(findByEmail);
    }

    public Usuario login(String email, String password) throws ValidationException {
        Usuario usuarioDB = null;
        try {
             usuarioDB = findByEmail(email);
        }catch (NoSuchElementException ex){
            throw new ValidationException(NAO_AUTENTICADO);
        }
        String md5Password = Md5Converter.convertToMd5(password);
        if (!md5Password.equals(usuarioDB.getSenha())){
            throw new ValidationException(NAO_AUTENTICADO);
        }

        return usuarioDB;
    }

}
