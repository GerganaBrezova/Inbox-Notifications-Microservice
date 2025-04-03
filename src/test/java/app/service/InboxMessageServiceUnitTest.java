package app.service;

import app.model.InboxMessage;
import app.repository.InboxMessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InboxMessageServiceUnitTest {

    @Mock
    private InboxMessageRepository inboxMessageRepository;

    @InjectMocks
    private InboxMessageService inboxMessageService;

    @Test
    void whenGetMessagesForUser_thenReturnListOfUserMessages() {

        UUID userId = UUID.randomUUID();

        InboxMessage firstInboxMessage = InboxMessage.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .header("Welcome!")
                .content("Some content.")
                .sentOn(LocalDateTime.now())
                .build();

        InboxMessage secondInboxMessage = InboxMessage.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .header("Welcome!")
                .content("Some content.")
                .sentOn(LocalDateTime.now())
                .build();

        List<InboxMessage> expectedInboxMessages = new ArrayList<>(List.of(firstInboxMessage, secondInboxMessage));

        when(inboxMessageRepository.findByUserId(userId)).thenReturn(expectedInboxMessages);

        List<InboxMessage> actualMessages = inboxMessageService.getMessagesForUser(userId);

        assertTrue(expectedInboxMessages.containsAll(actualMessages));
        assertTrue(actualMessages.containsAll(expectedInboxMessages));
        assertEquals(expectedInboxMessages.size(), actualMessages.size());
    }

    @Test
    void whenSaveMessage_thenMessageIsSaved() {

        UUID userId = UUID.randomUUID();

        InboxMessage expectedInboxMessage = InboxMessage.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .header("Welcome!")
                .content("Some content.")
                .sentOn(LocalDateTime.now())
                .build();

        inboxMessageService.saveMessage(expectedInboxMessage);

        ArgumentCaptor<InboxMessage> inboxMessageCaptor = ArgumentCaptor.forClass(InboxMessage.class);
        verify(inboxMessageRepository).save(inboxMessageCaptor.capture());

        InboxMessage savedMessage = inboxMessageCaptor.getValue();

        assertEquals(expectedInboxMessage.getId(), savedMessage.getId());
        verify(inboxMessageRepository, times(1)).save(expectedInboxMessage);
    }
}
