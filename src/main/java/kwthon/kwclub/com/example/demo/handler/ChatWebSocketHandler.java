//package com.example.demo.handler;
//
//import com.example.demo.entity.ChatMessage;
//import com.example.demo.repository.ChatMessageRepository;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.socket.CloseStatus;
//
//import java.time.LocalDateTime;
//import java.util.concurrent.CopyOnWriteArrayList;
//
//public class ChatWebSocketHandler extends TextWebSocketHandler {
//
//    private static final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
//
//    @Autowired
//    private ChatMessageRepository chatMessageRepository;
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        sessions.add(session);
//        System.out.println("New connection established: " + session.getId());
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        try {
//            JSONObject jsonMessage = new JSONObject(message.getPayload());
//            String sender = jsonMessage.getString("sender");
//            String content = jsonMessage.getString("content");
//            LocalDateTime timestamp = LocalDateTime.now();
//
//            // 메시지 저장
//            ChatMessage chatMessage = new ChatMessage(sender, content, timestamp);
//            chatMessageRepository.save(chatMessage);
//
//            // 모든 클라이언트에게 메시지 전송
//            JSONObject response = new JSONObject();
//            response.put("sender", sender);
//            response.put("content", content);
//            response.put("timestamp", timestamp.toString());
//
//            for (WebSocketSession webSocketSession : sessions) {
//                if (webSocketSession.isOpen()) {
//                    webSocketSession.sendMessage(new TextMessage(response.toString()));
//                }
//            }
//
//            System.out.println("Message received and broadcasted: " + response.toString());
//        } catch (Exception e) {
//            System.err.println("Error handling message: " + message.getPayload());
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        sessions.remove(session);
//        System.out.println("Connection closed: " + session.getId() + ", Status: " + status);
//    }
//}
