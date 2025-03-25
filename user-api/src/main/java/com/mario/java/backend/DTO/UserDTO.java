package com.mario.java.backend.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String cpf;
    private String key;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
}




