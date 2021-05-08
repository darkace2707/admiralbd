package ru.admiralnsk.admiralbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.admiralnsk.admiralbd.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
