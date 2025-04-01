package app.service;

import app.model.InboxMessage;
import app.repository.InboxMessageRepository;
import app.web.dto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class InboxMessageService {

    private final InboxMessageRepository inboxMessageRepository;

    @Autowired
    public InboxMessageService(InboxMessageRepository inboxMessageRepository) {
        this.inboxMessageRepository = inboxMessageRepository;
    }

    public InboxMessage saveMessage(MessageRequest messageRequest) {

        UUID userId = messageRequest.getUserId();

        InboxMessage inboxMessage = InboxMessage.builder()
                .userId(userId)
                .header(messageRequest.getHeader())
                .content(messageRequest.getContent())
                .sentOn(LocalDateTime.now())
                .build();

        inboxMessageRepository.save(inboxMessage);

        return inboxMessage;
    }
}
