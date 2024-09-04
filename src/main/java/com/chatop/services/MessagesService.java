package com.chatop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatop.model.dto.MessageDto;
import com.chatop.repository.MessagesRepository;


@Service
public class MessagesService {

    @Autowired
    private MessagesRepository dbMessagesRepository;
    
    /**
     * save a message to database 
     * @param entity a MessageDto entity
     */
    public void postMessage(MessageDto entity) {
        dbMessagesRepository.save(entity.ToMessageEntity());        
    }
}
