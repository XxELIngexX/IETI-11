package org.adaschool.api.controller.user;


import jakarta.annotation.security.RolesAllowed;
import org.adaschool.api.data.user.RoleEnum;
import org.adaschool.api.data.user.UserEntity;
import org.adaschool.api.data.user.UserServiceJPA;
import org.adaschool.api.exception.UserWithEmailAlreadyRegisteredException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.adaschool.api.utils.Constants.ADMIN_ROLE;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceJPA userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserServiceJPA userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        loadSampleUsers();
    }

    public void loadSampleUsers() {
        if (passwordEncoder != null) {
            UserEntity userEntity = new UserEntity("Ada Lovelace", "ada@mail.com", passwordEncoder.encode("passw0rd"));
            userService.save(userEntity);
            UserEntity adminUserEntity = new UserEntity("Ada Admin", "admin@mail.com", passwordEncoder.encode("passw0rd"));
            adminUserEntity.addRole(RoleEnum.ADMIN);
            userService.save(adminUserEntity);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity UserEntity) {
        UserEntity usuario = userService.findByEmail(UserEntity.getEmail()).orElse(null);

        if (usuario == null) {
            usuario = userService.save(UserEntity);
        }else {
            throw new UserWithEmailAlreadyRegisteredException();
        }
        System.out.println(usuario);
        return ResponseEntity.ok(usuario);
    }

    @RolesAllowed(ADMIN_ROLE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id) {
        UserEntity user = userService.findById(id).orElse(null);
        if (user != null) {
            userService.delete(user);
        }else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(true);

    }

}
