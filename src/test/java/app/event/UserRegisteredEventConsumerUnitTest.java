package app.event;

import app.event.payload.UserRegisteredEvent;
import app.model.InboxMessage;
import app.service.InboxMessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserRegisteredEventConsumerUnitTest {

    @Mock
    private InboxMessageService inboxMessageService;

    @InjectMocks
    private UserRegisteredEventConsumer eventConsumer;

    @Test
    void testConsumeEvent() {

        UUID userId = UUID.randomUUID();
        LocalDateTime createdOn = LocalDateTime.now();
        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .userId(userId)
                .createdOn(createdOn)
                .build();

        eventConsumer.consumeEvent(event);

        ArgumentCaptor<InboxMessage> messageCaptor = ArgumentCaptor.forClass(InboxMessage.class);
        verify(inboxMessageService, times(1)).saveMessage(messageCaptor.capture());

        InboxMessage capturedMessage = messageCaptor.getValue();
        assertEquals(userId, capturedMessage.getUserId());
        assertEquals("Welcome!", capturedMessage.getHeader());
        assertEquals("Thank you for joining our team! You have received 100 points for signing up! Earn more by completing workouts.", capturedMessage.getContent());
        assertEquals(createdOn, capturedMessage.getSentOn());
    }
}
