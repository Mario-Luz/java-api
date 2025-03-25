package com.mario.java.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.mario.java.backend.DTO.DTOConverter;
import com.mario.java.backend.exception.ResourceNotFoundException;
import com.mario.java.backend.DTO.UserDTO;
import com.mario.java.backend.model.UserModel;
import com.mario.java.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final DTOConverter dtoConverter;

    public List<UserDTO> getAllUsers() {
        log.debug("Fetching all users");
        return userRepository.findAll().stream()
                .map(dtoConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Page<UserDTO> getAllUsersPaginated(Pageable pageable) {
        log.debug("Fetching paginated users with page: {}", pageable);
        return userRepository.findAll(pageable)
                .map((java.util.function.Function<? super UserModel, ? extends UserDTO>) dtoConverter::convertToDTO);
    }

    public UserDTO getUserById(Long userId) {
        log.debug("Fetching user by ID: {}", userId);
        return (UserDTO) userRepository.findById(userId)
                .map(dtoConverter::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        log.debug("Creating new user");
        userDTO.setKey(UUID.randomUUID().toString());
        userDTO.setDataCadastro(LocalDateTime.now());

        UserModel user = dtoConverter.convertToEntity(userDTO);
        return dtoConverter.convertToDTO(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long userId) {
        log.debug("Deleting user with ID: {}", userId);
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
    }

    public UserDTO getUserByCpfAndKey(String cpf, String key) {
        log.debug("Fetching user by CPF: {} and key", cpf);
        return userRepository.findByCpfAndKey(cpf, key)
                .map(dtoConverter::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User", "cpf", cpf));
    }

    public List<UserDTO> searchUsersByName(String name) {
        log.debug("Searching users by name: {}", name);
        return userRepository.findByNomeContainingIgnoreCase(name).stream()
                .map(dtoConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        log.debug("Updating user with ID: {}", userId);
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        updateUserFields(user, userDTO);
        return dtoConverter.convertToDTO(userRepository.save(user));
    }

    private void updateUserFields(UserModel user, UserDTO userDTO) {
        if (StringUtils.hasText(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (StringUtils.hasText(userDTO.getTelefone())) {
            user.setTelefone(userDTO.getTelefone());
        }
        if (StringUtils.hasText(userDTO.getEndereco())) {
            user.setEndereco(userDTO.getEndereco());
        }
        if (StringUtils.hasText(userDTO.getNome())) {
            user.setNome(userDTO.getNome());
        }
    }
}