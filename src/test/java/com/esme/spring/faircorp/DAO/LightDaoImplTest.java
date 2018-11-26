package com.esme.spring.faircorp.DAO;

import com.esme.spring.faircorp.model.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;


@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class LightDaoImplTest {

    @Autowired
    LightDao
            lightDao;

    @Test
    public void shouldFindOnLights() {
        assertThat(lightDao.findOnLights())
                .hasSize(1)
                .extracting("id", "status")
                .containsExactly(tuple(-1L, Status.ON));
    }

    @Test
    public void shouldFindOnLightsAndId() {
        assertThat(lightDao.findOnLightsAndId())
                .extracting("id")
                .containsExactly(-2L,-1L);
    }

    @Test
    public void shouldFindOnLightsAndRoom() {
        assertThat(lightDao.findOnLightsAndRooms())
                .extracting("id")
                .containsExactly();
    }

}