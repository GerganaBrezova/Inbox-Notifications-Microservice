package app.service;

import app.model.InboxMessage;
import app.repository.InboxMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InboxMessageService {

    private final InboxMessageRepository inboxMessageRepository;

    @Autowired
    public InboxMessageService(InboxMessageRepository inboxMessageRepository) {
        this.inboxMessageRepository = inboxMessageRepository;
    }

    public List<InboxMessage> getMessagesForUser(UUID userId) {
        return inboxMessageRepository.findByUserId(userId);
    }

    public void saveMessage(InboxMessage inboxMessage) {
        inboxMessageRepository.save(inboxMessage);
    }
}
