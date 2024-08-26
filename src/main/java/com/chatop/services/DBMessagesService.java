package com.chatop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.chatop.model.dto.MessageDto;
import com.chatop.model.responses.simpleMessage;
import com.chatop.repository.DBMessagesRepository;

@Service
public class DBMessagesService {

    @Autowired
    private DBMessagesRepository dbMessagesRepository;

    public ResponseEntity<?> postMessage(MessageDto entity) {
        dbMessagesRepository.save(entity.ToDBMessages());
        // return ResponseEntity.ok().body(entity);
        return ResponseEntity.ok().body(new simpleMessage("Message send with success"));
    }

    // public ResponseEntity<?> getMessage(String id) {
    //     return ResponseEntity.ok().body(dbMessagesRepository.findByRentalId(Integer.parseInt(id)));
    // }
    
}
