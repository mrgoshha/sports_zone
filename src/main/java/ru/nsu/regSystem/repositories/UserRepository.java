package ru.nsu.regSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.regSystem.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
