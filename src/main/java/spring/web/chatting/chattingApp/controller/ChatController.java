package spring.web.chatting.chattingApp.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import spring.web.chatting.chattingApp.dto.ChatRoom;
import spring.web.chatting.chattingApp.service.ChatService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name){
        ChatRoom room = chatService.createRoom(name);
        return room;
    }

    @GetMapping
    public List<ChatRoom> findAllRooms(){
        List<ChatRoom> allRoom = chatService.findAllRoom();
        return allRoom;
    }
}