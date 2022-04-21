package br.edu.ifpb.transparencia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResultHandler {

    private String message;
    private int status;
}
