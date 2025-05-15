package dev.gabrieltoledo.picpaychallenge.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.gabrieltoledo.picpaychallenge.domain.user.User;
import dev.gabrieltoledo.picpaychallenge.service.dto.Notification;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
    
    private final RestTemplate restTemplate;

    public void sendNotification(User user, String message) {
        Notification notificationRequest = new Notification(user.getEmail(), message);

        ResponseEntity<String> response  = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to send notification");
        }
    }
}
