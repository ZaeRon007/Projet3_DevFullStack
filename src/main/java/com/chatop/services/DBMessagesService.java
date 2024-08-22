package com.chatop.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.chatop.model.dto.MessageDto;

@Service
public class DBMessagesService {

    public DBMessagesService(){

    }

    public ResponseEntity<?> postMessage(MessageDto entity) {
        return ResponseEntity.ok().body(entity);
    }
    
}
