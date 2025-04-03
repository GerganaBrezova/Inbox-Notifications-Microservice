package app.web;

import app.model.InboxMessage;
import app.service.InboxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inbox")
public class InboxMessageController {

    private final InboxMessageService inboxMessageService;

    @Autowired
    public InboxMessageController(InboxMessageService inboxMessageService) {
        this.inboxMessageService = inboxMessageService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<InboxMessage>> getInboxMessages(@PathVariable UUID userId) {

        List<InboxMessage> inboxMessages = inboxMessageService.getMessagesForUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(inboxMessages);
    }
}
