package com.sunbeam.services;

import com.sunbeam.daos.IMedicineDao;
import com.sunbeam.daos.IPatientDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PatientServicesTest {
    @Mock
    IPatientDao patientDao;

    PatientServices patientServices;

    @BeforeEach
    void setUp() {
        this.patientServices=new PatientServices(this.patientDao);
    }

    @Test
    void getAllPatients() {
        patientServices.getAllPatients();
        Mockito.verify(patientDao).findAll();
    }

}