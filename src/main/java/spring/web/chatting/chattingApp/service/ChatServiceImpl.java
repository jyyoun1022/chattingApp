package spring.web.chatting.chattingApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import spring.web.chatting.chattingApp.dto.ChatRoom;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@Data
@Service
public class ChatServiceImpl implements ChatService{

    private final ObjectMapper objectMapper;
    private Map<String,ChatRoom> chatRooms;

    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    @Override
    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    @Override
    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    @Override
    public ChatRoom createRoom(String name) {
        String roomId = UUID.randomUUID().toString();

        ChatRoom room = ChatRoom.builder()
                .roomId(roomId)
                .name(name)
                .build();

        chatRooms.put(roomId,room);
        return room;
    }

    @Override
    public <T> void sendMessage(WebSocketSession session, T message) {
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e){
            log.error(e.getMessage(),e);
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }
}
