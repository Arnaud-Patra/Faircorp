package com.esme.spring.faircorp.DAO;

import com.esme.spring.faircorp.model.Room;

import java.util.List;

public interface RoomDaoCustom {
    List<Room> findOnRoomsAndId(Long buildingId);


}
