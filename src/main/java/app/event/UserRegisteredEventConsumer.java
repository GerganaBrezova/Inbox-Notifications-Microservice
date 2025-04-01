package app.event;

import app.event.payload.UserRegisteredEvent;
import app.model.InboxMessage;
import app.service.InboxMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRegisteredEventConsumer {

    private final InboxMessageService inboxMessageService;

    @Autowired
    public UserRegisteredEventConsumer(InboxMessageService inboxMessageService) {
        this.inboxMessageService = inboxMessageService;
    }

    @KafkaListener(topics = "user-registered-event.v1", groupId = "Inbox-Notifications-Microservice")
    public void consumeEvent(UserRegisteredEvent userRegisteredEvent) {

        log.info("Successfully consumed user-registered-event for user %s.".formatted(userRegisteredEvent.getUserId()));

        InboxMessage message = InboxMessage.builder()
                .userId(userRegisteredEvent.getUserId())
                .header("Welcome!")
                .content("Thank you for joining our team! You have received 100 points for signing up! Earn more by completing workouts.")
                .sentOn(userRegisteredEvent.getCreatedOn())
                .build();

        inboxMessageService.saveMessage(message);
    }

}
