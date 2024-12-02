package ru.nsu.regSystem.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.regSystem.entities.SportsComplex;
import ru.nsu.regSystem.entities.SportsEvent;
import ru.nsu.regSystem.entities.Trainers;
import ru.nsu.regSystem.entities.User;
import ru.nsu.regSystem.repositories.SportsEventsRepository;
import ru.nsu.regSystem.repositories.TrainersRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class SportsEventService {
    private final SportsEventsRepository sportsEventsRepository;
    private final TrainersRepository trainersRepository;
    private final EmailSenderService senderService;
    private final AdminService adminService;

    @Cacheable("events")
    public List<SportsEvent> getSportsEvent() {
        return sportsEventsRepository.findAll();
    }


    @Caching(
            cacheable = {
                    @Cacheable(value = "event", key = "#id")
            },
            evict = {
                    @CacheEvict(value = "events", allEntries = true)
            })
    public SportsEvent getSportsEventById(Long id) {
        return sportsEventsRepository.findById(id).orElse(null);
    }

    public List<SportsEvent> getSportsEventByDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = dateTime.format(formatter);
        return sportsEventsRepository.findEventByDate(formattedString);
    }

    public Page<SportsEvent> filterEvent(String name, SportsComplex complex, Trainers trainer, Integer price, Pageable pageable) {
        return sportsEventsRepository.filerEvent(name, complex, trainer, price, pageable);
    }

    @Cacheable("trainers")
    public List<Trainers> getTrainers() {
        return trainersRepository.findAll();
    }

    @Caching(
            cacheable = {
                    @Cacheable("event")
            },
            evict = {
                    @CacheEvict(value = "events", allEntries = true)
            })
    public void saveEvent(SportsEvent complex) {
        log.info("save new {}", complex);
        sportsEventsRepository.save(complex);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "event", key = "#sportsEvent.id"),
                    @CacheEvict(value = "events", allEntries = true)
            })
    public void updateEvent(SportsEvent sportsEvent) {
        sportsEventsRepository.save(sportsEvent);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "event", key = "#id"),
                    @CacheEvict(value = "events", allEntries = true)
            })
    public void deleteEvent(Long id) {
        sportsEventsRepository.deleteById(id);
    }

    public boolean registrationUser(User user, Long eventId) {
        SportsEvent event = sportsEventsRepository.findById(eventId).orElse(null);
        if (event == null || user == null)
            return false;
        if (event.getSportsComplex().getCountSeats().equals(event.getCountSeats()) || // все места заняты
                !adminService.getRequestByUserAndEvent(user.getId(), event.getId()).isEmpty()) // проверка повторной заявки
        {
            return false;
        } else {
            adminService.saveRequest(user, event);
            String email = user.getEmail();
            String body = "Вы подали заявку на регестрацию на мероприятие " + event.getName() + "! " +
                    "Которое будет проходить в " + event.getSportsComplex().getName() + " по адресу " +
                    event.getSportsComplex().getAddress() + ".";
            String subject = "РЕГЕСТРАЦИЯ";
            senderService.sendEmail(email, subject, body);
            return true;
        }
    }
}
