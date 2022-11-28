package com.sunbeam.daos;

import com.sunbeam.entities.DoctorVisit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IDoctorVisitDaoTest {
    @Autowired
    IDoctorVisitDao doctorVisitDao;
    @Test
    void getVisitsByPatIdAndDoctorId() {
        DoctorVisit dv=doctorVisitDao.getVisitsByPatIdAndDoctorId(46,30);
        Assertions.assertThat(dv).isNotNull();
    }
}