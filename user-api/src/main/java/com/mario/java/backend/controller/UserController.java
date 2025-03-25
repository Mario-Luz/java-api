package com.mario.java.backend.controller;

import com.mario.java.backend.DTO.UserDTO;
import com.mario.java.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/page")
    public Page<UserDTO> getAllUsersPaginated(Pageable pageable) {
        return userService.getAllPage(pageable);
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

    @GetMapping("/cpf/{cpf}")
    public UserDTO getUserByCpfAndKey(
            @PathVariable String cpf,
            @RequestParam(name = "key", required = true) String key) {
        return userService.findByCpf(cpf, key);
    }

    @GetMapping("/search")
    public List<UserDTO> searchUsersByName(@RequestParam String name) {
        return userService.queryByName(name);
    }

    @PutMapping("/{userId}")
    public UserDTO updateUser(
            @PathVariable Long userId,
            @RequestBody UserDTO userDTO) {
        return userService.editUser(userId, userDTO);
    }
}