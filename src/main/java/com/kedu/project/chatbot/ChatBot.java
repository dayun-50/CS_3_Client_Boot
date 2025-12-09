package com.kedu.project.chatbot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="chatbot_responses")
@Getter
@Setter
@NoArgsConstructor
public class ChatBot {
    @Id
    private Integer id;
    @Column(name = "trigger_text")
    private String triggerText;
    @Column(name = "response_text")
    private String responseText;
}

