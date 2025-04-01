package app.web.mapper;

import app.model.InboxMessage;
import app.web.dto.MessageResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {

    public static MessageResponse fromMessage(InboxMessage inboxMessage) {

        return MessageResponse.builder()
                .content(inboxMessage.getContent())
                .header(inboxMessage.getHeader())
                .sentOn(inboxMessage.getSentOn())
                .build();
    }
}
