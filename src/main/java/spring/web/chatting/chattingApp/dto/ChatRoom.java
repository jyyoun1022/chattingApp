package spring.web.chatting.chattingApp.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;
import spring.web.chatting.chattingApp.service.ChatService;

import java.util.HashSet;
import java.util.Set;

@Data
public class ChatRoom {

    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleAction(WebSocketSession session, ChatDTO message, ChatService service){
        // message 에 담긴 타입을 확인한다.
        // 이때 message에서 getType으로 가져온 내용이
        // ChatDTO의 열거형인 MessageType 안에 있는 Enter과 동일한 값이라면
        if(message.getType().equals(ChatDTO.MessageType.ENTER)) {
            // sessions 에는 넘어온 session을 담고
            sessions.add(session);

            //message 에는입장하였다는 메시지를 띄운다.
            message.setMessage(message.getSender() + " 님이 입장하셨습니다.");
            sendMessage(message,service);
        } else if (message.getType().equals(ChatDTO.MessageType.TALK)) {
            message.setMessage(message.getMessage());
            sendMessage(message,service);
        }
    }
    public <T> void sendMessage(T message, ChatService service){
        sessions.parallelStream().forEach(session -> service.sendMessage(session,message));
    }
}
