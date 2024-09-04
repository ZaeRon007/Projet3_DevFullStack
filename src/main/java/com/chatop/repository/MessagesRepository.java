package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chatop.model.MessageEntity;
import java.util.List;


@Repository
public interface MessagesRepository extends CrudRepository<MessageEntity, Integer> {
    List<MessageEntity> findByRentalId(int rentalId);
}
