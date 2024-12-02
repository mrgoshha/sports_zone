package ru.nsu.regSystem.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.nsu.regSystem.entities.SportsEvent;
import ru.nsu.regSystem.entities.User;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleEmailService {
    private static final String CRON = "0 0 * * * *";
    private static final long sendReminder = 5;

    private final SportsEventService eventService;
    private final EmailSenderService senderService;

    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        LocalDateTime dateAndTime = LocalDateTime.now().plusHours(sendReminder);

        List<SportsEvent> events = eventService.getSportsEventByDate(dateAndTime);
        if (!events.isEmpty()) {
            events.forEach(event -> {
                List<User> visitors = event.getVisitors();
                if (!visitors.isEmpty()){
                    visitors.forEach(user -> {
                        String email = user.getEmail();
                        String body = "Напоминаем Вам, что вы зарегестрированны на мероприятие " + event.getName() + "! " +
                                "Которое будет проходить в " + event.getSportsComplex().getName() + " по адресу " +
                                event.getSportsComplex().getAddress() + ". Ждем Вас!";
                        String subject = "НАПОМИНАНИЕ О РЕГЕСТРАЦИИ";
                        senderService.sendEmail(email, subject, body);
                    });
                }

            });
        }
    }


}
