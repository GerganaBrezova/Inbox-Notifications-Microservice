package app.event;

import app.event.payload.UserCompletedWorkoutEvent;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserCompletedWorkoutEventConsumerUnitTest {

    @Mock
    private InboxMessageService inboxMessageService;

    @InjectMocks
    private UserCompletedWorkoutEventConsumer eventConsumer;

    @Test
    void testConsumeEvent() {

        UUID userId = UUID.randomUUID();
        LocalDateTime completedOn = LocalDateTime.now();
        UserCompletedWorkoutEvent event = UserCompletedWorkoutEvent.builder()
                .userId(userId)
                .completedOn(completedOn)
                .build();

        eventConsumer.consumeEvent(event);

        ArgumentCaptor<InboxMessage> messageCaptor = ArgumentCaptor.forClass(InboxMessage.class);
        verify(inboxMessageService, times(1)).saveMessage(messageCaptor.capture());

        InboxMessage capturedMessage = messageCaptor.getValue();
        assertEquals(userId, capturedMessage.getUserId());
        assertEquals("Well done!", capturedMessage.getHeader());
        assertEquals("You have just received 5 points for completing a workout!", capturedMessage.getContent());
        assertEquals(completedOn, capturedMessage.getSentOn());
    }
}
