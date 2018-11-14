package com.esme.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

@Entity // (1)
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    // (3)
    private Integer floor;

    @Column(nullable = false, length=255)
    private String name;

    @OneToMany(mappedBy = "room")
    private Set<Light> lights;


    public Room() {
    }

    public Room(Integer floor, String name) {
        this.floor = floor;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
