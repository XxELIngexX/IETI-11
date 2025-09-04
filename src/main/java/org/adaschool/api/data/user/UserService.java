package org.adaschool.api.data.user;

import org.adaschool.api.data.user.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(String id);

    UserEntity save(UserEntity user);

    void delete(UserEntity user);

}

