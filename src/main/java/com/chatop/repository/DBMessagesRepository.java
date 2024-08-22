package com.chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chatop.model.DBMessages;
import java.util.List;


@Repository
public interface DBMessagesRepository extends CrudRepository<DBMessages, Integer> {
    List<DBMessages> findByRentalId(int rentalId);
}
