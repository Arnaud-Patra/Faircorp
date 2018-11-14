package com.esme.spring.faircorp.DAO;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class RoomDaoImpTest {


    @Autowired
    RoomDao
            roomDao;

    @Test
    public void shouldFindRoom(){
        Assertions.assertThat(roomDao.findByName("Room1").getFloor()).isEqualTo(1);
    }

}
