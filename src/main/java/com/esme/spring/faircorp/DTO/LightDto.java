package com.esme.spring.faircorp.DTO;

import com.esme.spring.faircorp.model.Light;
import com.esme.spring.faircorp.model.Room;
import com.esme.spring.faircorp.model.Status;

public class LightDto {

    private final Long id;
    private final Integer level;
    private final Status status;

    public LightDto(Long id, Integer level, Status status){
        this.id = id;
        this.level = level;
        this.status = status;

    }

    public LightDto(Light light) {
        this.id = light.getId();
        this.level = light.getLevel();
        this.status = light.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }

    public Long getRoomId() {
        return ;
    }
}