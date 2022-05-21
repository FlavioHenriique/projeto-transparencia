package br.edu.ifpb.transparencia.controller;

import br.edu.ifpb.transparencia.model.ResultHandler;
import br.edu.ifpb.transparencia.model.Usuario;
import br.edu.ifpb.transparencia.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping
    public ResponseEntity salvar(@RequestBody Usuario usuario) throws NoSuchAlgorithmException {
        LOGGER.info("request salvar, body = " + usuario.toString());
        try{
            usuario = service.salvar(usuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return responseBadRequest(e.getMessage());
        }
    }

    @GetMapping("/findByEmail")
    public Usuario findByEmail(@RequestParam("email") String email){
        LOGGER.info("request findBYEmail, email = " + email);
        return service.findByEmail(email);
    }

    @DeleteMapping
    public ResponseEntity<ResultHandler> delete(@RequestBody() Usuario usuario){
        LOGGER.info("request delete, body = " + usuario.toString());
        try{
            service.delete(usuario);
        } catch (Exception e) {
            return responseBadRequest(e.getMessage());
        }
        return ResponseEntity.ok(new ResultHandler("Deletado com sucesso", HttpStatus.OK.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario){
        LOGGER.info("request login, body = "+ usuario.toString());
        Usuario usuarioLogado = null;
        try {
             usuarioLogado = service.login(usuario.getEmail(), usuario.getSenha());
        } catch (ValidationException e) {
            return responseBadRequest(e.getMessage());
        }
        return ResponseEntity.ok(usuarioLogado);
    }

    private ResponseEntity responseBadRequest(String message){
        return ResponseEntity.badRequest().body(new ResultHandler(message, HttpStatus.BAD_REQUEST.value()));
    }
}
