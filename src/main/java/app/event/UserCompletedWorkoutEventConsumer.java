package app.event;

import app.event.payload.UserCompletedWorkoutEvent;
import app.event.payload.UserRegisteredEvent;
import app.model.InboxMessage;
import app.service.InboxMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserCompletedWorkoutEventConsumer {

    private final InboxMessageService inboxMessageService;

    @Autowired
    public UserCompletedWorkoutEventConsumer(InboxMessageService inboxMessageService) {
        this.inboxMessageService = inboxMessageService;
    }

    @KafkaListener(topics = "user-completed-workout-event.v1", groupId = "Inbox-Notifications-Microservice")
    public void consumeEvent(UserCompletedWorkoutEvent userCompletedWorkoutEvent) {

        log.info("Successfully consumed user-completed-workout-event for user %s.".formatted(userCompletedWorkoutEvent.getUserId()));

        InboxMessage message = InboxMessage.builder()
                .userId(userCompletedWorkoutEvent.getUserId())
                .header("Well done!")
                .content("You have just received 5 points for completing a workout!")
                .sentOn(userCompletedWorkoutEvent.getCompletedOn())
                .build();

        inboxMessageService.saveMessage(message);
    }
}
