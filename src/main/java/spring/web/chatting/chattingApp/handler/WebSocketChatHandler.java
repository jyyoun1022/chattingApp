package spring.web.chatting.chattingApp.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import spring.web.chatting.chattingApp.dto.ChatDTO;
import spring.web.chatting.chattingApp.service.ChatService;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {

    /**
     * 웹 소켓 클라이언트로부터 채팅 메시지를 전달 받아 채팅 메시지 객체로 변환
     * 전달받은 메시지에 담긴 채팅방 id로 발송 대상 채팅방 정보를 조회
     * 해당 채팅방에 입장해 있는 모든 클라이언트(WebSocket Session)에게 타입에 따른 메시집 발송
     */
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payLoad {} ::: ",payload);

        ChatDTO chatMessage = objectMapper.readValue(payload, ChatDTO.class);
        log.info("session {} ::: ",chatMessage.toString());



    }
}
