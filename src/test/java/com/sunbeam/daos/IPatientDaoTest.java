package com.sunbeam.daos;

import com.sunbeam.entities.Patient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IPatientDaoTest {
    @Autowired
    IPatientDao patientDao;
    @Test
    void existsByBedAllotedAndWardId() {
        Boolean b=patientDao.existsByBedAllotedAndWardId(19,38);
    }

    @Test
    void findByUserId() {
        Patient p=patientDao.findByUserId(143);
        Assertions.assertThat(p).isNotNull();
    }
}