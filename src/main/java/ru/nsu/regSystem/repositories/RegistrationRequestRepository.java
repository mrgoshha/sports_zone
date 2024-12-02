package ru.nsu.regSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.regSystem.entities.RegistrationRequest;

import java.util.List;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Long> {

    List<RegistrationRequest> findByUserIdAndEventId(Long userId, Long eventId);
}
