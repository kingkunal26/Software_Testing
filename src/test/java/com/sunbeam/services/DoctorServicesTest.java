package com.sunbeam.services;

import com.sunbeam.daos.IDoctorDao;
import com.sunbeam.dtos.DoctorDataBackinBean;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DoctorServicesTest {
    @Mock
    IDoctorDao doctorDao;

    DoctorServices doctorServices;

    @BeforeEach
    void setUp() {
        this.doctorServices=new DoctorServices(this.doctorDao);
    }

    @Test
    void getAllDoctors() {
        doctorServices.getAllDoctors();
        Mockito.verify(doctorDao).findAll();
    }
}