package com.chatop.services;

import org.springframework.http.ResponseEntity;
import com.chatop.model.dto.MessageDto;

public class DBMessagesService {

    public ResponseEntity<?> postMessage(MessageDto entity) {
        return ResponseEntity.ok().body(entity);
    }
    
}
