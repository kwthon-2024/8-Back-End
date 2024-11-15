package kwthon.kwclub.com.example.demo.repository;

import kwthon.kwclub.com.example.demo.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    
}
