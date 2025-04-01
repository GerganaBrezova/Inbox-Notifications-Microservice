package app.web;

import app.model.InboxMessage;
import app.service.InboxMessageService;
import app.web.dto.MessageRequest;
import app.web.dto.MessageResponse;
import app.web.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class InboxMessageController {

    private final InboxMessageService inboxMessageService;

    @Autowired
    public InboxMessageController(InboxMessageService inboxMessageService) {
        this.inboxMessageService = inboxMessageService;
    }

    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest messageRequest) {

        InboxMessage inboxMessage = inboxMessageService.saveMessage(messageRequest);

        MessageResponse response = DtoMapper.fromMessage(inboxMessage);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
