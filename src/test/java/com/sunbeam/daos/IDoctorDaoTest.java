package com.sunbeam.daos;

import com.sunbeam.entities.Doctor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IDoctorDaoTest {
    @Autowired
    IDoctorDao doctorDao;
    @Test
    void getDoctorIdByFirstNameAndLastName() {
        int i=doctorDao.getDoctorIdByFirstNameAndLastName("King","Kunal");

        Assertions.assertThat(i).isEqualTo(41);
    }
}