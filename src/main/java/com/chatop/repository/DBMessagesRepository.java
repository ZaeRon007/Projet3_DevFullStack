package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chatop.model.DBMessages;

@Repository
public interface DBMessagesRepository extends CrudRepository<DBMessages, Integer> {

}
