package com.mario.java.backend.repository;

import com.mario.java.backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity operations.
 * Provides custom queries and extends JpaRepository for standard CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    /**
     * Finds a user by CPF and security key combination
     * @param cpf User's CPF (Cadastro de Pessoas Físicas)
     * @param key User's unique security key
     * @return Optional containing user if found
     */
    Optional<UserModel> findByCpfAndKey(String cpf, String key);

    /**
     * Searches users by name with case-insensitive partial matching
     * @param name Partial name to search for
     * @return List of matching users
     */
    List<UserModel> findByNomeContainingIgnoreCase(String name);

    /**
     * Finds a user by email address
     * @param email User's email address
     * @return Optional containing user if found
     */
    Optional<UserModel> findByEmail(String email);

    /**
     * Checks if a user exists with the given CPF
     * @param cpf CPF to check
     * @return true if a user with the CPF exists
     */
    boolean existsByCpf(String cpf);

    /**
     * Retrieves all users sorted by registration date (newest first)
     * @return List of users in descending registration order
     */
    List<UserModel> findAllByOrderByDataCadastroDesc();

    List<UserModel> queryByNomeLike(String name);
}