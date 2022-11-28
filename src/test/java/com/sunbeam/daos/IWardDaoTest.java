package com.sunbeam.daos;

import com.sunbeam.entities.Ward;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IWardDaoTest {
    @Autowired
    IWardDao wardDao;
    @Test
    void getWardIdByTypeOfWard() {
        int id=wardDao.getWardIdByTypeOfWard("A");
        Assertions.assertThat(id).isEqualTo(19);
    }



    @Test
    void findByType() {
        Ward w=wardDao.findByType("A");
        Assertions.assertThat(w).isNotNull();
    }
}