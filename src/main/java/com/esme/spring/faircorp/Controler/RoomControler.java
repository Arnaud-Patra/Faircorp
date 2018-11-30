package com.esme.spring.faircorp.Controler;


import com.esme.spring.faircorp.DAO.BuildingDao;
import com.esme.spring.faircorp.DAO.LightDao;
import com.esme.spring.faircorp.DAO.RoomDao;
import com.esme.spring.faircorp.DTO.RoomDto;
import com.esme.spring.faircorp.model.Light;
import com.esme.spring.faircorp.model.Room;
import com.esme.spring.faircorp.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/* TODO :

/api/rooms/{room_id}/switchLight switch the room lights
*/
@RestController
@CrossOrigin
@RequestMapping("/api/rooms")
@Transactional
public class RoomControler {

    @Autowired
    private RoomDao roomDao;
    @Autowired
    private BuildingDao buildingDao;
    @Autowired
    private LightDao lightDao;


    public RoomControler(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(room -> new RoomDto(room)).orElse(null);
    }

    @PutMapping(path = "/{id}/switch")
    public List<Light>  switchStatus(@PathVariable Long id) {

        List<Light> lightList =  lightDao.findByRoomId(id);
        for (Light light : lightList) {
            light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON);
        }
        return lightList;
    }



    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        Room room = null;
        if (dto.getId() != null) {
            room = roomDao.findById(dto.getId()).orElse(null);
        }
        if (room == null) {
            room = roomDao.save(new Room(dto.getFloor(), dto.getName(), buildingDao.getOne(dto.getBuildingId())));
        } else {
            room.setFloor(dto.getFloor());
            room.setName(dto.getName());
            roomDao.save(room);
        }

        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        List<Light> lightList =  lightDao.findByRoomId(id);
        for (Light light : lightList) {
            lightDao.deleteById(light.getId());
        }
        roomDao.deleteById(id);
    }

}




