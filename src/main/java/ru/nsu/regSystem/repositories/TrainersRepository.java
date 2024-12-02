package ru.nsu.regSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.regSystem.entities.Trainers;


public interface TrainersRepository extends JpaRepository<Trainers, Integer> {
}
