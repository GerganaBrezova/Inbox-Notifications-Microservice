package app.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageResponse {

    @NotBlank
    private String header;

    @NotBlank
    private String content;

    @NotNull
    private LocalDateTime sentOn;
}
