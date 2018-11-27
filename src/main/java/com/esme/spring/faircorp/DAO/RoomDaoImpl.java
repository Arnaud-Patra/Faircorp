package com.esme.spring.faircorp.DAO;

import com.esme.spring.faircorp.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoomDaoImpl implements RoomDaoCustom {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Room> findOnRoomsAndId(Long buildingId){
        String jpql = "select room from Room room, Building B where B.nameB.id = :value";
        return em.createQuery(jpql, Room.class)
                .setParameter("value", buildingId)
                .getResultList();


    }


}
