package com.esme.spring.faircorp.DAO;

import com.esme.spring.faircorp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDao extends JpaRepository<Room, Long>, RoomDaoCustom  {
    Room findByName(String name);
    List<Room> findByBuildingId(Long id);
}
