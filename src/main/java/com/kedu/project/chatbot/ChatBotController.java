package com.kedu.project.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping("/chatBoot")
@RestController
public class ChatBotController {
    @Autowired
    private ChatBotService chatBotService;    

    // @GetMapping("/butList")
    // public ResponseEntity<ChatBotDTO> butList() {
    //     return ResponseEntity.ok(chatBotService.butList);
    // }
    
}
