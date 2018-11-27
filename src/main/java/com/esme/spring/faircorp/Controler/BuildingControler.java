package com.esme.spring.faircorp.Controler;


import com.esme.spring.faircorp.DAO.BuildingDao;
import com.esme.spring.faircorp.DAO.LightDao;
import com.esme.spring.faircorp.DAO.RoomDao;
import com.esme.spring.faircorp.DTO.BuildingDto;
import com.esme.spring.faircorp.model.Building;
import com.esme.spring.faircorp.model.Light;
import com.esme.spring.faircorp.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


//TODO
//Delete building and rooms in it.
//Switch lights.


@RestController
@RequestMapping("/api/buildings")
@Transactional
public class BuildingControler {
    @Autowired
    private LightDao lightDao;
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private BuildingDao buildingDao;



    public BuildingControler(BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingDao.findAll()
                .stream()
                .map(BuildingDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public BuildingDto findById(@PathVariable Long id) {
        return buildingDao.findById(id).map(building -> new BuildingDto(building)).orElse(null);
    }

/*
    @PutMapping(path = "/{id}/switch")
    public LightDto switchStatus(@PathVariable Long id) {
        Light light = lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON);
        return new LightDto(light);
    }
*/


    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building = null;
        if (dto.getId() != null) {
            building = buildingDao.findById(dto.getId()).orElse(null);
        }
        if (building == null) {
            building = buildingDao.save(new Building(dto.getName() ));
        } else {
            building.setName(dto.getName());
            buildingDao.save(building);
        }

        return new BuildingDto(building);
    }


    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        List<Room> roomList =  roomDao.findOnRoomsAndId(id);
        for(int i = 0; i < roomList.size(); i++) {
            Room room = roomList.get(i);

            List<Light> lightList =  lightDao.findOnLightsAndId(room.getId());
            for(int j = 0; j < lightList.size(); j++) {
                Light light = lightList.get(j);
                lightDao.deleteById(light.getId());
            }

            roomDao.deleteById(room.getId());
        }


        buildingDao.deleteById(id);
    }


}
