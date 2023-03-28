package spring.web.chatting.chattingApp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import spring.web.chatting.chattingApp.handler.WebSocketChatHandler;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class SpringConfig implements WebSocketConfigurer {

    //webSocketHandler 에 관한 생성자 추가
    private final WebSocketChatHandler webSocketChatHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //endpoint 설정 : /ws/chat
        //이를 통해서 ws://localhost:8080/ws/chat으로 욫어이 들어오면 websocket 통신을 진행
        registry.addHandler(webSocketChatHandler,"/ws/chat").setAllowedOrigins("*");

    }
}
