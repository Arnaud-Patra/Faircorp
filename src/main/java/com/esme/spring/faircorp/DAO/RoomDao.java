package com.esme.spring.faircorp.DAO;

import com.esme.spring.faircorp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDao extends JpaRepository<Room, Long> {
    Room findByName(String name);
}
