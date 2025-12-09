package com.kedu.project.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ChatBotRepository extends JpaRepository<ChatBot, Integer>{
    List<ChatBot> findByTriggerText(String triggerText);
}
