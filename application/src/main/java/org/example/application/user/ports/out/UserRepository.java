package org.example.application.user.ports.out;

import org.example.domain.user.User;
import org.example.domain.user.UserId;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(UserId userId);
    boolean existsById(UserId userId);
}
