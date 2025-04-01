package app.event;

import app.event.payload.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRegisteredEventConsumer {

    @KafkaListener(topics = "user-registered-event.v1", groupId = "Inbox-Notifications-Microservice")
    public void consumeEvent(UserRegisteredEvent userRegisteredEvent) {

        log.info("Successfully consumed user-registered-event for user %s.".formatted(userRegisteredEvent.getUserId()));
    }

}
