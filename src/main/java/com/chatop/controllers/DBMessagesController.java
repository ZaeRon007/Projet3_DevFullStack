package com.chatop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.dto.MessageDto;
import com.chatop.services.DBMessagesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Message Controller", description = "Gesture component for messages")
@RestController
@RequestMapping("/api/messages")
public class DBMessagesController {
    
    @Autowired
    private DBMessagesService dbMessagesService;


    @Operation(
        summary = "send a message to the rental owner",
        description = "send a message by specifying the rental_id, user_id and text message"
        // tags = {"post","message"}
    )
    @PostMapping("")
    public ResponseEntity<?> postMessage(@RequestBody MessageDto entity) {
        return dbMessagesService.postMessage(entity);
    }

}
