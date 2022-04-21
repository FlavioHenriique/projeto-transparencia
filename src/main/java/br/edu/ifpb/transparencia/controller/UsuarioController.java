package br.edu.ifpb.transparencia.controller;

import br.edu.ifpb.transparencia.model.ResultHandler;
import br.edu.ifpb.transparencia.model.Usuario;
import br.edu.ifpb.transparencia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Usuario usuario) {
        try{
            usuario = service.salvar(usuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ResultHandler(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/findByEmail")
    public Usuario findByEmail(@RequestParam("email") String email){
        return service.findByEmail(email);
    }
}
