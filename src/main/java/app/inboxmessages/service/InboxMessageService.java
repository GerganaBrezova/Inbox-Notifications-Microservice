package app.inboxmessages.service;

import app.inboxmessages.repository.InboxMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InboxMessageService {

    private final InboxMessageRepository inboxMessageRepository;

    @Autowired
    public InboxMessageService(InboxMessageRepository inboxMessageRepository) {
        this.inboxMessageRepository = inboxMessageRepository;
    }
}
