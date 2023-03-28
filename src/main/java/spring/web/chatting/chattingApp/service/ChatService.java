package spring.web.chatting.chattingApp.service;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import spring.web.chatting.chattingApp.dto.ChatRoom;

import java.util.List;

public interface ChatService {

    List<ChatRoom> findAllRoom();
    ChatRoom findRoomById(String roomId);
    ChatRoom createRoom(String name);
    <T> void sendMessage(WebSocketSession session,T message);
}
