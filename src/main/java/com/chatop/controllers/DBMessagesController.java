package com.chatop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatop.model.dto.MessageDto;
import com.chatop.services.DBMessagesService;

@RestController
@RequestMapping("/api/messages")
public class DBMessagesController {
    
    @Autowired
    private DBMessagesService dbMessagesService;

    @PostMapping("")
    public ResponseEntity<?> postMessage(@RequestBody MessageDto entity) {
        return dbMessagesService.postMessage(entity);
    }

}
