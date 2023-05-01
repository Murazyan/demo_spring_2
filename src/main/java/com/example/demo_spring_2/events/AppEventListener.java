package com.example.demo_spring_2.events;

import com.example.demo_spring_2.models.User;
import com.example.demo_spring_2.service.impl.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
@RequiredArgsConstructor
public class AppEventListener {

    private final EmailService emailService;
    @Value("${server.port}")
    private String port;

    @EventListener
    public void handeUserRegisterEvent(UserRegisteredEvent event) {
        sendVerificationEmail(event.getUser());
    }


    @EventListener
    public void handeUserRegisterEvent(UserAddEvent event) {
        sendStudentPassword(event.getUser().getEmail(), event.getPassword());
    }

    @SneakyThrows
    private void sendVerificationEmail(User user) {

        String link = "http://"+InetAddress.getLocalHost().getHostAddress()+":"+port+"/user/verify?id="+user.getId()
                +"&verificationCode="+user.getVerificationCode();

        String body = String.format("Welcome our application %s: Click %s to verify your account", user.getName(), link);
        emailService.sendEmail(user.getEmail(), "Account Verification", body);
    }

    @SneakyThrows
    private void sendStudentPassword(String email, String password) {
        String body = "Welcome our application. Your password: "+password;
        emailService.sendEmail(email, "Account credentials", body);
    }

}
