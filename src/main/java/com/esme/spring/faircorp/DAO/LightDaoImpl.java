package com.esme.spring.faircorp.DAO;

import com.esme.spring.faircorp.model.Light;
import com.esme.spring.faircorp.model.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class LightDaoImpl implements LightDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Light> findOnLights() {
        String jpql = "select lt from Light lt where lt.status = :value";
        return em.createQuery(jpql, Light.class)
                .setParameter("value", Status.ON)
                .getResultList();
    }

    @Override
    public List<Light> findOnLightsAndId(){
        String jpql = "select lt from Light lt, Room R where R.name.id = :value";
        return em.createQuery(jpql, Light.class)
                .setParameter("value", -10L)
                .getResultList();
    }

    @Override
    public List<Light> findOnLightsAndRooms(){
        String jpql = "select lt from Light lt, Room R, Building G where R.name.id = :value and R.name = G.nameB.id";
        return em.createQuery(jpql, Light.class)
                .setParameter("value", -100L)
                .getResultList();
    }

}
