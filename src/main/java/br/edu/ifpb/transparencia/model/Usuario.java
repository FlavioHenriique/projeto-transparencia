package br.edu.ifpb.transparencia.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    private String id;
    private String senha;
    @Indexed(unique=true)
    private String email;
    private String nome;

    public Usuario(String nome, String senha, String email){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }
}
