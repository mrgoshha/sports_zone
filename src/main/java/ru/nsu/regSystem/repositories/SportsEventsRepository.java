package ru.nsu.regSystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nsu.regSystem.entities.SportsComplex;
import ru.nsu.regSystem.entities.SportsEvent;
import ru.nsu.regSystem.entities.Trainers;


import java.util.List;


public interface SportsEventsRepository extends JpaRepository<SportsEvent, Long> {

        @Query("select se from SportsEvent se where " +
                "(:name is null or se.name = :name or :name = '') and " +
                " (:complex is null or se.sportsComplex = :complex) and " +
                "(:trainer is null or se.trainer = :trainer) and "+
                "(:price is null or se.price = :price)")
        Page<SportsEvent> filerEvent(@Param("name") String name,
                                     @Param("complex") SportsComplex complex,
                                     @Param("trainer") Trainers trainer,
                                     @Param("price") Integer price, Pageable pageable);

        @Query("select se from SportsEvent se where (se.startDateAndTime = :dateTime)")
        List<SportsEvent> findEventByDate(@Param("dateTime") String dateTime);
}
