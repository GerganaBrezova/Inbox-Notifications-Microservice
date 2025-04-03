package app.web;

import app.model.InboxMessage;
import app.service.InboxMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InboxMessageController.class)
public class InboxMessageControllerApiTest {

    @MockitoBean
    private InboxMessageService inboxMessageService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRequestToInbox_happyPath() throws Exception {

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

        List<InboxMessage> inboxMessages = new ArrayList<>(List.of(firstInboxMessage, secondInboxMessage));

        when(inboxMessageService.getMessagesForUser(userId)).thenReturn(inboxMessages);

        MockHttpServletRequestBuilder request = get("/api/v1/inbox/{userId}", userId);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].userId").isNotEmpty())
                .andExpect(jsonPath("$[0].header").isNotEmpty())
                .andExpect(jsonPath("$[0].content").isNotEmpty())
                .andExpect(jsonPath("$[0].sentOn").isNotEmpty());

        //"$[0].id" → instruct jsonPath, that we search 'id' in the first object of the list ($[0]).
    }
}
