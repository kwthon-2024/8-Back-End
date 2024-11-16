package kwthon.kwclub.com.example.demo.handler;

import kwthon.kwclub.com.example.demo.entity.ChatMessage;
import kwthon.kwclub.com.example.demo.repository.ChatMessageRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("New connection established: " + session.getId());

        // 기존 메시지 불러오기
        List<ChatMessage> chatHistory = chatMessageRepository.findAll();
        for (ChatMessage chatMessage : chatHistory) {
            String formattedTime = chatMessage.getTimestamp().format(DateTimeFormatter.ofPattern("H:mm"));
            String fullMessage = chatMessage.getSender() + " : " + chatMessage.getContent() + " " + formattedTime;
            session.sendMessage(new TextMessage(fullMessage));
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Message received: " + message.getPayload());

        try {
            ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);
            chatMessage.setTimestamp(LocalDateTime.now());

            chatMessageRepository.save(chatMessage);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
            String formattedTime = chatMessage.getTimestamp().format(formatter);

            for (WebSocketSession webSocketSession : sessions) {
                if (webSocketSession.isOpen()) {
                    String fullMessage = chatMessage.getSender() + " : " + chatMessage.getContent() + " " + formattedTime;
                    webSocketSession.sendMessage(new TextMessage(fullMessage));
                }
            }
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Connection closed: " + session.getId() + ", Status: " + status);
    }
}
