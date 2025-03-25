package com.mario.java.backend.DTO;


import com.mario.java.backend.model.UserModel;
import java.util.Objects;

public class DTOConverter {

    public UserModel convertToEntity(UserDTO userDTO) {
        Objects.requireNonNull(userDTO, "UserDTO cannot be null");

        return UserModel.builder()
                .id(userDTO.getId())
                .nome(userDTO.getNome())
                .endereco(userDTO.getEndereco())
                .cpf(userDTO.getCpf())
                .key(userDTO.getKey())
                .email(userDTO.getEmail())
                .telefone(userDTO.getTelefone())
                .dataCadastro(userDTO.getDataCadastro())
                .build();
    }

    public UserDTO convertToDTO(UserModel user) {
        Objects.requireNonNull(user, "User cannot be null");

        return UserDTO.builder()
                .id(user.getId())
                .nome(user.getNome())
                .endereco(user.getEndereco())
                .cpf(user.getCpf())
                .key(user.getKey())
                .email(user.getEmail())
                .telefone(user.getTelefone())
                .dataCadastro(user.getDataCadastro())
                .build();
    }
}