package ru.nsu.regSystem.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.regSystem.entities.Role;
import ru.nsu.regSystem.entities.User;
import ru.nsu.regSystem.repositories.UserRepository;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService senderService;

    private String tempPassword;
    private User tempUser;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null)
            return false;//пользователь уже существует
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_ADMIN);
        log.info("create new User with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
            userRepository.save(user);
        }
    }

    public boolean sendTempPass(String email) {
        tempUser = userRepository.findByEmail(email);
        if (tempUser == null)
            return false;
        else {
            tempPassword = RandomStringUtils.randomAscii(8);
            senderService.sendEmail(email, "Восстановление пароля", tempPassword);
            return true;
        }
    }


    public void updatePassword(String newPassword) {
        tempUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(tempUser);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


    public String getTempPassword() {
        return tempPassword;
    }

}
