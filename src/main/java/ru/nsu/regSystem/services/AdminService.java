package ru.nsu.regSystem.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.regSystem.entities.RegistrationRequest;
import ru.nsu.regSystem.entities.SportsEvent;
import ru.nsu.regSystem.entities.User;
import ru.nsu.regSystem.repositories.RegistrationRequestRepository;
import ru.nsu.regSystem.repositories.SportsEventsRepository;


import org.springframework.data.domain.Page;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class AdminService {
    private final RegistrationRequestRepository requestRepository;
    private final SportsEventsRepository eventsRepository;
    private final UserService userService;
    private final EmailSenderService senderService;


    @Cacheable("request")
    public Page<RegistrationRequest> getAllRequest(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    @Cacheable("request")
    public RegistrationRequest getRequestById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public List<RegistrationRequest> getRequestByUserAndEvent(Long userId, Long eventId) {
        return requestRepository.findByUserIdAndEventId(userId, eventId);
    }

    @CachePut("request")
    public void saveRequest(User user, SportsEvent event) {
        log.info("save new {}", user.getId() + " " + event.getId());
        RegistrationRequest request = new RegistrationRequest();
        request.setUser(user);
        request.setStatus(false);
        request.setEvent(event);
        requestRepository.save(request);
    }

    @CacheEvict("request")
    public void deleteRequestEvent(Long id) {
        requestRepository.deleteById(id);
    }

    @CachePut("request")
    public boolean registrationUserEvent(Long id) {
        RegistrationRequest request = this.getRequestById(id);
        User user = request.getUser();
        SportsEvent event = request.getEvent();

        // проверка на то что пользователь уже зарегистрирован
        if(user.getEvents().contains(event))
            return false;
        else{
            int count = event.getCountSeats() + 1;
            event.setCountSeats(count);
            event.getVisitors().add(user);
            user.getEvents().add(event);
            request.setStatus(true);
            requestRepository.save(request);
            eventsRepository.save(event);
            userService.saveUser(user);

            String email = user.getEmail();
            String body = "Вы успешно зарегестрированны на мероприятие " + event.getName() + "! " +
                    "Которое будет проходить в " + event.getSportsComplex().getName() + " по адресу " +
                    event.getSportsComplex().getAddress() + ". Мы Вас ждем!)";
            String subject = "РЕГЕСТРАЦИЯ";
            senderService.sendEmail(email, subject, body);
            return true;
        }
    }
}
