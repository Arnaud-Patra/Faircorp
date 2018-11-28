package com.esme.spring.faircorp.Controler;


import com.esme.spring.faircorp.DAO.BuildingDao;
import com.esme.spring.faircorp.DAO.LightDao;
import com.esme.spring.faircorp.DAO.RoomDao;
import com.esme.spring.faircorp.DTO.BuildingDto;
import com.esme.spring.faircorp.model.Building;
import com.esme.spring.faircorp.model.Light;
import com.esme.spring.faircorp.model.Room;
import com.esme.spring.faircorp.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


//TODO
//Delete building and rooms in it.
//Switch lights.

@CrossOrigin
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


    @PutMapping(path = "/{id}/switch")
    public List<Light>  switchStatus(@PathVariable Long id) {
        List<Light> TotalLights = new LinkedList<Light>();;
        List<Room> roomList =  roomDao.findByBuildingId(id);
        for (Room room : roomList) {
            List<Light> lightList = lightDao.findByRoomId(room.getId());
            for (Light light : lightList) {
                light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON);
                TotalLights.add(light);
            }

        }
        return TotalLights;
    }

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
        for (Room room : roomList) {
            List<Light> lightList = lightDao.findOnLightsAndId(room.getId());
            for (Light light : lightList) {
                lightDao.deleteById(light.getId());
            }
            roomDao.deleteById(room.getId());
        }
        buildingDao.deleteById(id);
    }


}
