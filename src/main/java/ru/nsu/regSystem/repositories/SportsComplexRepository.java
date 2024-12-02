package ru.nsu.regSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nsu.regSystem.entities.SportsComplex;

import java.util.List;



public interface SportsComplexRepository extends JpaRepository<SportsComplex, Long> {

    @Query("select sc, SUM(se.countSeats) from SportsComplex sc join sc.sportsEvent se " +
            "where (se.startDateAndTime = :startDateAndTime or :startDateAndTime is null or :startDateAndTime = '') and (sc = :complex or :complex is null) group by sc ")
    List<Object[]> getAttendance(@Param("startDateAndTime") String startDateAndTime,
                                 @Param("complex") SportsComplex complex);

}
