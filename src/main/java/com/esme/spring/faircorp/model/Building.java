package com.esme.spring.faircorp.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Building {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length=255)
    private String nameB;

    @OneToMany(mappedBy = "building")
    private Set<Room> rooms;

    public Building() {
    }


    public Building(String nameB) {
        this.nameB = nameB;
    }



    public String getName() {
        return nameB;
    }

    public void setName(String nameB) {
        this.nameB = nameB;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





}
