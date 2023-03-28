package spring.web.chatting.chattingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatDTO {

    public enum MessageType {
        ENTER, TALK
    }
    private MessageType type;//message type
    private String roomId;
    private String sender;
    private String message;
    private String time;
}
