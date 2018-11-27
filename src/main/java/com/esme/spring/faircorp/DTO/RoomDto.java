package com.esme.spring.faircorp.DTO;

import com.esme.spring.faircorp.model.Room;

public class RoomDto {

    private Long id;
    private Integer floor;
    private String name;
    private Long buildingId;


    public RoomDto(){
    }

    public RoomDto(Room room){
        this.id = room.getId();
        this.floor = room.getFloor();
        this.name = room.getName();
        this.buildingId = room.getBuilding().getId();
    }

    public Long getId() {
        return id;
    }

    public Integer getFloor(){return floor;}

    public String getName(){return name;}

    public Long getBuildingId(){return buildingId;}

}
